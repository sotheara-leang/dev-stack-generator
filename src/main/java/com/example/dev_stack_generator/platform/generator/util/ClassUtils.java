package com.example.dev_stack_generator.platform.generator.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.util.Assert;

public class ClassUtils {
	
	public static Class<?> getInterfaceGenericType(Class<?> clazz, int index) {
		Assert.notNull(clazz, "clazz is indefined");
		
		Type type = clazz.getGenericInterfaces()[0];
		
		ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        
        return (Class<?>) typeArguments[index];
	}
	
	public static Class<?> getGenericType(Class<?> clazz, int index) {
		Assert.notNull(clazz, "clazz is indefined");
		
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        
        return (Class<?>) typeArguments[index];
	}
}
