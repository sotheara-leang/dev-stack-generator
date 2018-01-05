package com.example.dev_stack_generator.platform.generator.pojo;

import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.model.BuilderField;
import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.example.dev_stack_generator.platform.generator.pojo.model.ClassBuilder;
import com.example.dev_stack_generator.platform.generator.util.TypeUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class ClassBuilderGenerator {

	public static final String CLASS_NAME = "Builder";
	public static final String METHOD_NAME = "build";
	public static final String STATIC_METHOD_NAME = "builder";
	
	public MethodSpec generateBuilderMethodSpec() {
		ClassName builderClassName = ClassName.get("", CLASS_NAME);
		
		return MethodSpec.methodBuilder(STATIC_METHOD_NAME)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(builderClassName)
				.addStatement("return new Builder()")
				.build();
	}
	
	public TypeSpec generateInnerClassBuilderTypeSpec(ClassBuilder classBuilder) {
		Assert.notNull(classBuilder, "classBuilder must not be null");
		
		com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(CLASS_NAME)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL);
		
		ClassName className = ClassName.get(classBuilder.getTargetClassPackage(), classBuilder.getTargetClassName());
		ClassName builderClassName = ClassName.get("", CLASS_NAME);
		
		List<BuilderField> fieldList = classBuilder.getFieldList();
		if (fieldList != null) {
			for (BuilderField field: fieldList) {
				String fieldName = field.getName();
				TypeName typeName = TypeUtils.getTypeName(field.getType(), field.getSubTypeList());
				
				// Generate field
				FieldSpec fieldSpec = FieldSpec.builder(typeName, field.getName(), Modifier.PRIVATE).build();
				typeSpecBuilder.addField(fieldSpec);
				
				// Generate setter
				com.squareup.javapoet.MethodSpec.Builder setterSpecBuilder = MethodSpec.methodBuilder(fieldName)
				    .addModifiers(Modifier.PUBLIC)
				    .returns(builderClassName)
				    .addParameter(typeName, fieldName);
				
			 	setterSpecBuilder.addStatement("this.$N = $N", fieldName, fieldName);
			 	setterSpecBuilder.addStatement("return this");
				
				typeSpecBuilder.addMethod(setterSpecBuilder.build());
			}
		}
		
		// Generate build method
		String instanceName = Utils.getInstanceName(classBuilder.getTargetClassName());
		
		com.squareup.javapoet.MethodSpec.Builder buildMethodSpecBuilder = MethodSpec.methodBuilder(METHOD_NAME)
			    .addModifiers(Modifier.PUBLIC)
			    .returns(className)
			    .addStatement(String.format("$T %s = new $T()", instanceName), className, className);
			    
		for (BuilderField field: fieldList) {
			String fieldName = field.getName();
			String setterName = Utils.getSetterName(fieldName);
			
			buildMethodSpecBuilder.addStatement(String.format("%s." + setterName + "($N)", instanceName), fieldName);
		}
		buildMethodSpecBuilder.addStatement("return $L", instanceName);
		
		typeSpecBuilder.addMethod(buildMethodSpecBuilder.build());
		
		return typeSpecBuilder.build();
	}
}
