package com.example.dev_stack_generator.platform.generator.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class TypeUtils {

	public static TypeName getTypeName(Class<?> type, List<Class<?>> subTypeList) {
		Assert.notNull(type, "type must not be null");
		
		TypeName typeName = null;
		if (subTypeList != null && !subTypeList.isEmpty()) {
			Type[] typeNameList = new Type[subTypeList.size()];
			for (int i = 0; i < subTypeList.size(); i++) {
				typeNameList[i] = subTypeList.get(i);
			}
			typeName = ParameterizedTypeName.get(type, typeNameList) ;
			
		} else {
			typeName = TypeName.get(type);
		}
		
		return typeName;
	}
	
	public static Modifier[] toArray(List<Modifier> modifierList) {
		Modifier[] array = null;
		if (modifierList != null) {
			array = new Modifier[modifierList.size()];
			for (int i = 0; i < modifierList.size(); i++) {
				array[i] = modifierList.get(i);
			}
		}
		return array;
	}
	
	public static List<Modifier> toList(Modifier... modifiers) {
		List<Modifier> list = null;
		if (modifiers != null) {
			list = new ArrayList<>();
			for (Modifier modifier : modifiers) {
				list.add(modifier);
			}
		}
		return list;
	}
}
