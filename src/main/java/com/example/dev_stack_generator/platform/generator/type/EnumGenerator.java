package com.example.dev_stack_generator.platform.generator.type;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.example.dev_stack_generator.platform.generator.type.model.Enum;
import com.example.dev_stack_generator.platform.generator.type.model.EnumValue;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class EnumGenerator {
	
	public static final String FIELD_VALUE_NAME = "value";
	public static final String FIELD_DESCRIPTION_NAME = "description";
	
	public <T> void generateFile(Enum<T> enumType) throws IOException {
		JavaFile enumFile = generate(enumType);
		enumFile.writeTo(Paths.get(enumType.getSourceFolder()));
	}
	
	public <T> JavaFile generate(Enum<T> enumType) {
		Assert.notNull(enumType, "enumType must not be null");

		String name = Utils.formatClassName(enumType.getName());
		Modifier modifier = enumType.getModifier() == null ? Modifier.PUBLIC : enumType.getModifier();
		
		com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder = TypeSpec.enumBuilder(name)
				.addModifiers(modifier);
		
		List<EnumValue<T>> valueSet = enumType.getValueSet();
		if (valueSet != null && !valueSet.isEmpty()) {
			if (enumType.isHasValue() && enumType.isHasDescription()) {
				Class<T> valueClass = enumType.getValueClass();
				
				// value set
				for (EnumValue<T> value : valueSet) {
					String valueFormat = String.class.isAssignableFrom(valueClass) ? "$S" : "$L";
					TypeSpec valueTypeSpec = TypeSpec
							.anonymousClassBuilder(valueFormat + ", $S", value.getValue(), value.getDescription())
							.build();
					typeSpecBuilder.addEnumConstant(value.getName(), valueTypeSpec);
				}
				
				// field
				FieldSpec valueFieldSpec = generateValueFieldSpec(valueClass);
				typeSpecBuilder.addField(valueFieldSpec);
				
				FieldSpec descriptionFieldSpec = generateDescriptionFieldSpec();
				typeSpecBuilder.addField(descriptionFieldSpec);
				
				// constructor
				MethodSpec constructorSpec = MethodSpec.constructorBuilder()
					.addParameter(valueClass, FIELD_VALUE_NAME)
					.addParameter(String.class, FIELD_DESCRIPTION_NAME)
					.addStatement("this.$N = $N", FIELD_VALUE_NAME, FIELD_VALUE_NAME)
					.addStatement("this.$N = $N", FIELD_DESCRIPTION_NAME, FIELD_DESCRIPTION_NAME)
		        	.build();
				
				typeSpecBuilder.addMethod(constructorSpec);
				
				// value getter
				MethodSpec valueGetterSpec = generateValueGetterSpec(valueClass);
				typeSpecBuilder.addMethod(valueGetterSpec);
				
				// description getter
				MethodSpec descriptionGetterSpec = generateDescriptionGetterSpec();
				typeSpecBuilder.addMethod(descriptionGetterSpec);
				
			} else if (enumType.isHasValue()) {
				Class<T> valueClass = enumType.getValueClass();
				
				// value set
				for (EnumValue<T> value : valueSet) {
					TypeSpec valueTypeSpec = TypeSpec.anonymousClassBuilder("$L", value.getValue()).build();
					typeSpecBuilder.addEnumConstant(value.getName(), valueTypeSpec);
				}
				
				// field
				FieldSpec valueFieldSpec = generateValueFieldSpec(valueClass);
				typeSpecBuilder.addField(valueFieldSpec);
				
				// constructor
				MethodSpec constructorSpec = MethodSpec.constructorBuilder()
					.addParameter(valueClass, FIELD_VALUE_NAME)
					.addStatement("this.$N = $N", FIELD_VALUE_NAME, FIELD_VALUE_NAME)
		        	.build();
				
				typeSpecBuilder.addMethod(constructorSpec);
				
				// value getter
				MethodSpec valueGetterSpec = generateValueGetterSpec(valueClass);
				typeSpecBuilder.addMethod(valueGetterSpec);
				
			} else if (enumType.isHasDescription()) {
				// value set
				for (EnumValue<T> value : valueSet) {
					TypeSpec descriptionTypeSpec = TypeSpec.anonymousClassBuilder("$S", value.getDescription()).build();
					typeSpecBuilder.addEnumConstant(value.getName(), descriptionTypeSpec);
				}
				
				// field
				FieldSpec descriptionFieldSpec = generateDescriptionFieldSpec();
				typeSpecBuilder.addField(descriptionFieldSpec);
				
				// constructor
				MethodSpec constructorSpec = MethodSpec.constructorBuilder()
					.addParameter(String.class, FIELD_DESCRIPTION_NAME)
					.addStatement("this.$N = $N", FIELD_DESCRIPTION_NAME, FIELD_DESCRIPTION_NAME)
		        	.build();
				
				typeSpecBuilder.addMethod(constructorSpec);
				
				// description getter
				MethodSpec descriptionGetterSpec = generateDescriptionGetterSpec();
				typeSpecBuilder.addMethod(descriptionGetterSpec);
				
			} else {
				for (EnumValue<T> value : valueSet) {
					typeSpecBuilder.addEnumConstant(value.getName());
				}
			}
		}
		
		return JavaFile.builder(enumType.getClassPackage(), typeSpecBuilder.build()).build();
	}
	
	protected <T> FieldSpec generateValueFieldSpec(Class<T> valueClass) {
		return FieldSpec.builder(valueClass, FIELD_VALUE_NAME)
				.addModifiers(Modifier.PRIVATE, Modifier.FINAL)
				.build();
	}
	
	protected <T> MethodSpec generateValueGetterSpec(Class<T> valueClass) {
		return MethodSpec.methodBuilder(Utils.getGetterName(FIELD_VALUE_NAME))
				.addModifiers(Modifier.PUBLIC)
				.returns(valueClass)
				.addStatement("return $N", FIELD_VALUE_NAME)
	        	.build();
	}
	
	protected <T> FieldSpec generateDescriptionFieldSpec() {
		return FieldSpec.builder(String.class, FIELD_DESCRIPTION_NAME)
				.addModifiers(Modifier.PRIVATE, Modifier.FINAL)
				.build();
	}
	
	protected MethodSpec generateDescriptionGetterSpec() {
		return MethodSpec.methodBuilder(Utils.getGetterName(FIELD_DESCRIPTION_NAME))
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addStatement("return $N", FIELD_DESCRIPTION_NAME)
	        	.build();
	}
}
