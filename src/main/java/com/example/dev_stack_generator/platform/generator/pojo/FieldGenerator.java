package com.example.dev_stack_generator.platform.generator.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.pojo.constraint.generator.LengthConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.generator.NotEmptyConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.generator.NotNullConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.model.Constraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.example.dev_stack_generator.platform.generator.pojo.model.FieldInitializer;
import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.example.dev_stack_generator.platform.generator.util.ClassUtils;
import com.example.dev_stack_generator.platform.generator.util.TypeUtils;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.FieldSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

public class FieldGenerator {

	protected Map<Class<?>, ConstraintGenerator<?>> constraintGeneratorMap = new HashMap<>();
	
	public FieldGenerator() {
		this(null);
	}
	
	public FieldGenerator(List<ConstraintGenerator<?>> constraintGeneratorList) {
		List<ConstraintGenerator<?>> targetGeneratorList = defaultConstraintGeneratorList();
		if (constraintGeneratorList != null) {
			targetGeneratorList.addAll(constraintGeneratorList);
		}
		
		for (ConstraintGenerator<?> generator : targetGeneratorList) {
			Class<?> constraint = ClassUtils.getInterfaceGenericType(generator.getClass(), 0);
			constraintGeneratorMap.put(constraint, generator);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FieldSpec generateFieldSpec(Field field) {
		Assert.notNull(field, "field must be not null");
		
		com.squareup.javapoet.FieldSpec.Builder fieldSpecBuilder = null;
		
		Modifier fieldModifier = field.getModifier() == null ? Modifier.PRIVATE : field.getModifier();
		
		TypeName typeName = TypeUtils.getTypeName(field.getType(), field.getSubTypeList());
		fieldSpecBuilder = FieldSpec.builder(typeName, field.getName(), fieldModifier);
		
		FieldInitializer fieldInitializer = field.getFieldInitializer();
		if (fieldInitializer != null) {
			fieldSpecBuilder.initializer(fieldInitializer.getFormat(), fieldInitializer.getArguments());
		}
		
		// Generate constraint list
		List<Constraint> constraintList = field.getConstraintList();
		if (constraintList != null) {
			for (Constraint constraint : constraintList) {
				Class<?> constraintClass = constraint.getClass();
				
				ConstraintGenerator constraintGenerator = (ConstraintGenerator) constraintGeneratorMap.get(constraintClass);
				if (constraintGenerator != null) {
					AnnotationSpec constraintAnnotationSpec = constraintGenerator.generateConstraintAnnotationSpec(field, constraint);
					if (constraintAnnotationSpec != null) {
						fieldSpecBuilder.addAnnotation(constraintAnnotationSpec);
					}
				}
			}
		}
		
		return fieldSpecBuilder.build();
	}
	
	protected MethodSpec generateGetterSpec(Field field) {
		Assert.notNull(field, "field must be not null");
		
		TypeName typeName = TypeUtils.getTypeName(field.getType(), field.getSubTypeList());
		Modifier getterModifier = field.getGetterModifier() == null ? Modifier.PUBLIC : field.getGetterModifier();
		
		String getterName = Utils.getGetterName(field.getName());
		return MethodSpec.methodBuilder(getterName)
			    .addModifiers(getterModifier)
			    .returns(typeName)
			    .addStatement("return this.$N", field.getName())
			    .build();
	}
	
	protected MethodSpec generateSetterSpec(Field field) {
		Assert.notNull(field, "field must be not null");
		
		String fieldName = field.getName();
		TypeName typeName = TypeUtils.getTypeName(field.getType(), field.getSubTypeList());
		Modifier setterModifier = field.getSetterModifier() == null ? Modifier.PUBLIC : field.getSetterModifier();
		
		String setterName = Utils.getSetterName(fieldName);
		return MethodSpec.methodBuilder(setterName)
			    .addModifiers(setterModifier)
			    .returns(void.class)
			    .addParameter(typeName, fieldName)
			    .addStatement("this.$N = $N", fieldName, fieldName)
			    .build();
	}
	
	protected List<ConstraintGenerator<? extends Constraint>> defaultConstraintGeneratorList() {
		List<ConstraintGenerator<? extends Constraint>> generatorList = new ArrayList<>();

		generatorList.add(new NotNullConstraintGenerator());
		generatorList.add(new NotEmptyConstraintGenerator());
		generatorList.add(new LengthConstraintGenerator());

		return generatorList;
	}
	
	protected FieldSpec generateSerialVersionUID() {
		Builder fieldSpecBuilder = FieldSpec.builder(long.class, 
				"serialVersionUID", 
				Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL);
		
		long randomRang = 1000000000000000L;
		double randomValue = Math.ceil( (Math.random() * randomRang));
		
		fieldSpecBuilder.initializer( "$LL", (long ) randomValue );
		
		return fieldSpecBuilder.build();
	}
}
