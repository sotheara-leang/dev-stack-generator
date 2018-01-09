package com.example.dev_stack_generator.platform.generator.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;


public class ClassUtils {
	
	public static Class<?> getInterfaceGenericType(Class<?> clazz, int index) {
		Assert.notNull(clazz, "clazz must not be null");
		
		Type type = clazz.getGenericInterfaces()[0];
		
		ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        
        return (Class<?>) typeArguments[index];
	}
	
	public static Class<?> getGenericType(Class<?> clazz, int index) {
		Assert.notNull(clazz, "clazz must be not null");
		
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        
        return (Class<?>) typeArguments[index];
	}
	
	@SuppressWarnings( { "unchecked" } )
	public static List<Method> getAllAbstractMethods(Class<?> clazz) {
		Assert.notNull(clazz, "clazz must not be null");
		
		List<Method> methods = new ArrayList<>();
		
		List<Class<?>> allSuperclasses = org.apache.commons.lang.ClassUtils.getAllSuperclasses( clazz );
		for (Class<?> superClass : allSuperclasses) {
			Method[] declaredMethods = superClass.getDeclaredMethods();
			for (Method method : declaredMethods) {
				int modifiers = method.getModifiers();
				if (modifiers == Modifier.ABSTRACT
						&& (modifiers == Modifier.PUBLIC || modifiers == Modifier.PROTECTED)) {
					methods.add( method );
				}
			}
		}
		
		return methods;
	}
}
