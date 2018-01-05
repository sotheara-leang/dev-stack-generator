package com.example.dev_stack_generator.platform.generator.dao;

import java.util.List;

import javax.lang.model.element.Modifier;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.dao.model.Method;
import com.example.dev_stack_generator.platform.generator.dao.model.Parameter;
import com.example.dev_stack_generator.platform.generator.util.TypeUtils;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

public class MethodGenerator {

	public MethodSpec generateMethodSpec(Method method) {
		Assert.notNull(method, "method must not be null");
		
		List<Modifier> methodModifierList = method.getModifierList();
		if (methodModifierList == null || methodModifierList.isEmpty()) {
			methodModifierList = TypeUtils.toList(Modifier.PUBLIC, Modifier.ABSTRACT);
		}
		
		Builder methodSpectBuilder = MethodSpec.methodBuilder(method.getName())
				 .addModifiers(TypeUtils.toArray(methodModifierList));
		
		// return type
		TypeName typeName = TypeUtils.getTypeName(method.getReturnType(), method.getReturnSubTypeList());
		methodSpectBuilder.returns(typeName);
		
		// generate parameters
		List<Parameter> parameterList = method.getParameterList();
		if (parameterList != null && !parameterList.isEmpty()) {
			for (Parameter parameter : parameterList) {
				TypeName paramTypeName = TypeUtils.getTypeName(parameter.getType(), parameter.getSubTypeList());
				
				com.squareup.javapoet.ParameterSpec.Builder paramterSpecBuilder = ParameterSpec.builder(paramTypeName, parameter.getName());
				
				String value = parameter.getAlias() != null ? parameter.getAlias() : parameter.getName();
				if (parameterList.size() > 1 || value != null) {
					AnnotationSpec paramAnnotation = AnnotationSpec.builder(Param.class)
							.addMember("value", "$S", value)
							.build();
					
					paramterSpecBuilder.addAnnotation(paramAnnotation);
				}
				methodSpectBuilder.addParameter(paramterSpecBuilder.build());
			}
		}
		
		return methodSpectBuilder.build();
	}
}
