package com.example.dev_stack_generator.platform.generator.pojo;

import com.example.dev_stack_generator.platform.generator.pojo.model.Constraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.squareup.javapoet.AnnotationSpec;

public interface ConstraintGenerator<T extends Constraint> {

	AnnotationSpec generateConstraintAnnotationSpec(Field field, T constraint);
}
