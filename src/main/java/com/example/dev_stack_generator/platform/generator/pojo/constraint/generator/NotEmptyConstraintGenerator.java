package com.example.dev_stack_generator.platform.generator.pojo.constraint.generator;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.dev_stack_generator.platform.generator.pojo.ConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.NotEmptyConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.squareup.javapoet.AnnotationSpec;

public class NotEmptyConstraintGenerator implements ConstraintGenerator<NotEmptyConstraint> {

	@Override
	public AnnotationSpec generateConstraintAnnotationSpec(Field field, NotEmptyConstraint constraint) {
		return AnnotationSpec.builder(NotEmpty.class).build();
	}
}
