package com.example.dev_stack_generator.platform.generator.pojo.model;

import java.util.Arrays;

public class FieldInitializer {

	private String format;
	private Object[] arguments;
	
	public FieldInitializer() {}
	
	public FieldInitializer(String format, Object... arguments) {
		this.format = format;
		this.arguments = arguments;
	}
	
	public FieldInitializer(Builder builder) {
		this.format = builder.format;
		this.arguments = builder.arguments;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "FieldInitializer [format=" + format + ", arguments=" + Arrays.toString(arguments) + "]";
	}
	
	public static Builder builder () {
		return new Builder();
	}
	
	public static final class Builder {

		private String format;
		private Object[] arguments;

		public Builder format(String format, Object... arguments) {
			this.format = format;
			this.arguments = arguments;
			return this;
		}

		public FieldInitializer build() {
			return new FieldInitializer(this);
		}
	}
}
