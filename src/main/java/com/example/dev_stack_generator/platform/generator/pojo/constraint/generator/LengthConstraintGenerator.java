package com.example.dev_stack_generator.platform.generator.pojo.constraint.generator;

import org.hibernate.validator.constraints.Length;

import com.example.dev_stack_generator.platform.generator.pojo.ConstraintGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.LengthConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.squareup.javapoet.AnnotationSpec;

public class LengthConstraintGenerator implements ConstraintGenerator<LengthConstraint> {

	@Override
	public AnnotationSpec generateConstraintAnnotationSpec(Field field, LengthConstraint constraint) {
		if (constraint != null) {
			return AnnotationSpec.builder(Length.class)
					.addMember("min", "$L", constraint.getMin())
					.addMember("max", "$L", constraint.getMax())
					.build();
		}
		return null;
	}
}
