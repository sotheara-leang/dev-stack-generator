package com.example.dev_stack_generator.platform.generator.pojo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.model.ClassBuilder;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.example.dev_stack_generator.platform.generator.pojo.model.Pojo;
import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.example.dev_stack_generator.platform.generator.util.TypeUtils;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class PojoGenerator {
	
	protected FieldGenerator fieldGenerator;
	protected ClassBuilderGenerator classBuilderGenerator;
	
	public PojoGenerator() {
		this(new FieldGenerator());
	}
	
	public PojoGenerator(FieldGenerator fieldGenerator) {
		this(fieldGenerator, new ClassBuilderGenerator());
	}
	
	public PojoGenerator(FieldGenerator fieldGenerator, ClassBuilderGenerator classBuilderGenerator) {
		this.fieldGenerator = fieldGenerator;
		this.classBuilderGenerator = classBuilderGenerator;
	}
	
	public void generateFile(Pojo pojo) throws IOException {
		JavaFile pojoFile = generate(pojo);
		pojoFile.writeTo(Paths.get(pojo.getSourceFolder()));
	}
	
	public JavaFile generate(Pojo pojo) {
		Assert.notNull(pojo, "pojo must not be null");
		
		List<Modifier> classModifierList = pojo.getClassModifierList();
		if (classModifierList == null || classModifierList.isEmpty()) {
			classModifierList = TypeUtils.toList(Modifier.PUBLIC);
		}
		
		String className = Utils.formatClassName(pojo.getClassName());
		
		com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
				.addModifiers(TypeUtils.toArray(classModifierList));
		
		List<Field> fieldList = pojo.getFieldList();
		if (fieldList != null) {
			for (Field field : fieldList) {
				// Generate field
				FieldSpec fieldSpec = fieldGenerator.generateFieldSpec(field);
				typeSpecBuilder.addField(fieldSpec);
				
				// Generator getter
				MethodSpec getterMethodSpec = fieldGenerator.generateGetterSpec(field);
				typeSpecBuilder.addMethod(getterMethodSpec);
				
				// Generate setter
				MethodSpec setterMethodSpec = fieldGenerator.generateSetterSpec(field);
				typeSpecBuilder.addMethod(setterMethodSpec);
			}
		}
				
		// Generate equals
		MethodSpec equalsSpec = generateEqualsSpec();
		typeSpecBuilder.addMethod(equalsSpec);
		
		// Generate hash code
		MethodSpec hashCodeSpec = generateHashCodeSpec();
		typeSpecBuilder.addMethod(hashCodeSpec);
		
		// Generate toString
		MethodSpec toStringMethodSpec = generateToStringSpec(pojo);
		typeSpecBuilder.addMethod(toStringMethodSpec);
		
		boolean hasBuilder = pojo.isHasBuilder();
		if (hasBuilder) {
			// Generate builder method
			MethodSpec builderMethodSpec = classBuilderGenerator.generateBuilderMethodSpec();
			typeSpecBuilder.addMethod(builderMethodSpec);
			
			// Generate builder class
			ClassBuilder innerClassBuilder = Utils.toClassBuilder(pojo);
			TypeSpec innerClassBuilderTypeSpec = classBuilderGenerator.generateInnerClassBuilderTypeSpec(innerClassBuilder);
			typeSpecBuilder.addType(innerClassBuilderTypeSpec);
		}
		
		return JavaFile.builder(pojo.getClassPackage(), typeSpecBuilder.build()).build();
	}
	
	protected MethodSpec generateEqualsSpec() {
		return MethodSpec.methodBuilder("equals")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.addParameter(Object.class, "other")
				.returns(boolean.class)
				.addStatement("if (this == other) return true")
				.addStatement("if (other == null) return false")
				.addStatement("if (getClass() != other.getClass()) return false")
				.addStatement("return toString().equals(other.toString())")
				.build();
	}
	
	protected MethodSpec generateHashCodeSpec() {
		return MethodSpec.methodBuilder("hashCode")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(int.class)
				.addStatement("return toString().hashCode()")
				.build();
	}

	protected MethodSpec generateToStringSpec(Pojo pojo) {
		Assert.notNull(pojo, "pojo must be not null");
		
		String toStringStmt = Utils.getToStringStmt(pojo) ;
		return MethodSpec.methodBuilder("toString")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addCode("return \"" + toStringStmt + "\";\n")
				.build();
	}
}
