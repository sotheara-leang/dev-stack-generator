package com.example.dev_stack_generator.platform.generator.pojo;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Paths;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.model.ClassBuilder;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.example.dev_stack_generator.platform.generator.pojo.model.Pojo;
import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.example.dev_stack_generator.platform.generator.util.TypeUtils;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
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
		
		boolean isAbstractPojo = false;
		for (Modifier modifier : classModifierList) {
			if (modifier == Modifier.ABSTRACT) {
				isAbstractPojo = true;
				break;
			}
		}
		
		// Super class
		Class<?> superClass = pojo.getSuperClass();
		if (superClass != null) {
			initSuperClassSpec( typeSpecBuilder, superClass, isAbstractPojo);
		}
		
		// Interface classes
		List<Class<?>> interfaceClassList = pojo.getInterfaceClassList();
		if (interfaceClassList != null) {
			for (Class<?> interfaceClass : interfaceClassList) {
				initInterfaceSpec(typeSpecBuilder, interfaceClass, isAbstractPojo);
			}
		}
				
		// Generate equals
		if (pojo.isHasEquals()) {
			MethodSpec equalsSpec = generateEqualsSpec();
			typeSpecBuilder.addMethod(equalsSpec);
		}
		
		// Generate hash code
		if (pojo.isHasHashCode()) {
			MethodSpec hashCodeSpec = generateHashCodeSpec();
			typeSpecBuilder.addMethod(hashCodeSpec);
		}
		
		// Generate toString
		if (pojo.isHasToString()) {
			MethodSpec toStringMethodSpec = generateToStringSpec(pojo);
			typeSpecBuilder.addMethod(toStringMethodSpec);
		}
		
		if (!isAbstractPojo && pojo.isHasBuilder()) {
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
	
	protected void initSuperClassSpec(com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder, Class<?> superClass, boolean isAbstractPojo) {
		int interfaceModifiers = superClass.getModifiers();
		if (!java.lang.reflect.Modifier.isInterface(interfaceModifiers)) {
			return;
		}
		
		typeSpecBuilder.superclass( superClass );
		
		if (!isAbstractPojo) {
			Method[] methods = superClass.getMethods();
			if (methods != null) {
				for (int i = 0 ; i < methods.length; i++) {
					Method method = methods[i];
					MethodSpec methodSpec = toBasicAbstractMethodSpec( method );
					typeSpecBuilder.addMethod( methodSpec );
				}
			}
		}
	}
	
	protected void initInterfaceSpec(com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder, Class<?> interfaceClass, boolean isAbstractPojo) {
		int interfaceModifiers = interfaceClass.getModifiers();
		if (!java.lang.reflect.Modifier.isInterface(interfaceModifiers)) {
			return;
		}
		
		if (Serializable.class.isAssignableFrom( interfaceClass )) {
			typeSpecBuilder.addSuperinterface( interfaceClass );
			typeSpecBuilder.addField( fieldGenerator.generateSerialVersionUID() );
			
		} else {
			typeSpecBuilder.addSuperinterface( interfaceClass );
			
			if (!isAbstractPojo) {
				Method[] methods = interfaceClass.getMethods();
				if (methods != null) {
					for (int i = 0 ; i < methods.length; i++) {
						Method method = methods[i];
						MethodSpec methodSpec = toBasicAbstractMethodSpec( method );
						typeSpecBuilder.addMethod( methodSpec );
					}
				}
			}
		}
	}
	
	protected MethodSpec toBasicAbstractMethodSpec(Method method) {
		Assert.notNull(method, "method must be not null");
		
		Builder methodBuilder = MethodSpec.methodBuilder( method.getName() )
				.addAnnotation( Override.class );

		// method return type		
		Class<?> returnType = method.getReturnType();
		methodBuilder.returns( returnType );
		
		// method modifier
		int methodModifiers = method.getModifiers();
		if (java.lang.reflect.Modifier.isPublic( methodModifiers )) {
			methodBuilder.addModifiers( javax.lang.model.element.Modifier.PUBLIC );
		}
		
		// method parameters
		Parameter[] parameters = method.getParameters();
		if (parameters != null && parameters.length > 0) {
			for (Parameter parameter : parameters) {
				String paramName = parameter.getName();
				Class<?> paramType = parameter.getType();
				methodBuilder.addParameter( paramType, paramName);
			}
		}
		
		// prepare basic implementation code block
		if (!void.class.isAssignableFrom( returnType )) {
			com.squareup.javapoet.CodeBlock.Builder codeBlock = CodeBlock.builder();
			codeBlock.addStatement( "return $L", Utils.getDefaultValue( returnType ) );
			
			methodBuilder.addCode( codeBlock.build() );
		}
		
		return methodBuilder.build();
	}
}
