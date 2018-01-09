package com.example.dev_stack_generator.platform.generator.pojo.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.model.BuilderField;
import com.example.dev_stack_generator.platform.generator.pojo.model.ClassBuilder;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.example.dev_stack_generator.platform.generator.pojo.model.Pojo;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

public class Utils {
	
	public static String getInstanceName(String className) {
		String instanceName = "";
		if (className != null && className.length() > 0) {
			instanceName = className.substring(0, 1).toLowerCase() + className.substring(1);
		}
		return instanceName;
	}
	
	public static String formatClassName(String className) {
		String clazzName = "";
		if (className != null && className.length() > 0) {
			clazzName = className.substring(0, 1).toUpperCase() + className.substring(1);
		}
		return clazzName;
	}
	
	public static String getSetterName(String fieldName) {
		String setterName = "";
		if (fieldName != null && fieldName.length() > 0) {
			setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		return setterName;
	}

	public static String getGetterName(String fieldName) {
		String getterName = "";
		if (fieldName != null && fieldName.length() > 0) {
			getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		}
		return getterName;
	}

	public static String getToStringStmt(Pojo pojo) {
		Assert.notNull(pojo, "pojo must not be null");
		
		StringBuilder body = new StringBuilder();
		List<Field> fieldList = pojo.getFieldList();
		if (fieldList != null && !fieldList.isEmpty()) {
			for (int i = 0; i < fieldList.size(); i++) {
				Field field = fieldList.get(i);
				String fName = field.getName();

				body.append(String.format("%s=\" + this.%s + \"", fName, fName));

				if (i < fieldList.size() - 1) {
					body.append(", ");
				}
			}
		}

		return String.format("%s [%s]", pojo.getClassName(), body.toString());
	}
	
	public static ClassBuilder toClassBuilder(Pojo pojo) {
		Assert.notNull(pojo, "pojo must not be null");
		
		ClassBuilder innerClassBuilder = new ClassBuilder();
		innerClassBuilder.setTargetClassPackage(pojo.getClassPackage());
		innerClassBuilder.setTargetClassName(pojo.getClassName());
		
		List<BuilderField> builderFieldList = innerClassBuilder.getFieldList();
		
		List<Field> fieldList = pojo.getFieldList();
		if (fieldList != null) {
			for (Field field : fieldList) {
				BuilderField builderField = new BuilderField();
				builderField.setName(field.getName());
				builderField.setType(field.getType());
				builderField.setSubTypeList(field.getSubTypeList());
				
				builderFieldList.add(builderField);
			}
		}
		
		return innerClassBuilder;
	}
	
	public static MethodSpec toMethodSpec(Method method) {
		Assert.notNull(method, "method must not be null");
		
		String name = method.getName();
		Builder methodBuilder = MethodSpec.methodBuilder( name );
		
		int modifiers = method.getModifiers();
		if (modifiers == Modifier.PUBLIC) {
			methodBuilder.addModifiers( javax.lang.model.element.Modifier.PUBLIC );
		} else if (modifiers == Modifier.PROTECTED) {
			methodBuilder.addModifiers( javax.lang.model.element.Modifier.PROTECTED );
		}
		
		Class<?> returnType = method.getReturnType();
		methodBuilder.returns( returnType);
				
		Parameter[] parameters = method.getParameters();
		if (parameters != null && parameters.length > 0) {
			for (Parameter parameter : parameters) {
				String paramName = parameter.getName();
				Class<?> paramType = parameter.getType();
				methodBuilder.addParameter( paramType, paramName);
			}
		}
		
		return methodBuilder.build();
	}
	
	public static Object getDefaultValue( Class<?> clazz ) {
		if ( clazz.equals( boolean.class ) ) {
			return false;
		} else if ( clazz.equals( byte.class ) 
				|| clazz.equals( short.class )
				|| clazz.equals( int.class )
				|| clazz.equals( long.class )
				|| clazz.equals( float.class )
				|| clazz.equals( double.class )) {
			return 0;
		} else {
			return null;
		}
	}
}
