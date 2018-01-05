package com.example.dev_stack_generator.platform.generator.pojo.constraint.generator;

import javax.validation.constraints.NotNull;

import com.example.dev_stack_generator.platform.generator.pojo.ConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.NotNullConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.squareup.javapoet.AnnotationSpec;

public class NotNullConstraintGenerator implements ConstraintGenerator<NotNullConstraint> {

	@Override
	public AnnotationSpec generateConstraintAnnotationSpec(Field field, NotNullConstraint constraint) {
		return AnnotationSpec.builder(NotNull.class).build();
	}
}
