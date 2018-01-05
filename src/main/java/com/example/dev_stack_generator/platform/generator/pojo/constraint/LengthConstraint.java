package com.example.dev_stack_generator.platform.generator.pojo.constraint;

import com.example.dev_stack_generator.platform.generator.pojo.model.Constraint;

public class LengthConstraint implements Constraint {

	private int min = 0;
	private int max = Integer.MAX_VALUE;

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "LengthConstraint [min=" + min + ", max=" + max + "]";
	}
}
