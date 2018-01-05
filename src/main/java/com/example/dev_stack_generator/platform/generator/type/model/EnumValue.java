package com.example.dev_stack_generator.platform.generator.type.model;

public class EnumValue<T> {

	private String name;
	private T value;
	private String description;
	
	public EnumValue() {}

	public EnumValue(Builder<T> builder) {
		this.name = builder.name;
		this.value = builder.value;
		this.description = builder.description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "EnumValue [name=" + name + ", value=" + value + ", description=" + description + "]";
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static final class Builder<T> {

		private String name;
		private T value;
		private String description;

		public Builder<T> name(String name) {
			this.name = name;
			return this;
		}

		public Builder<T> value(T value) {
			this.value = value;
			return this;
		}

		public Builder<T> description(String description) {
			this.description = description;
			return this;
		}

		public EnumValue<T> build() {
			return new EnumValue<T>(this);
		}
	}
}
