package com.example.dev_stack_generator.platform.generator.type.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class Enum<T> {

	private String sourceFolder;
	private String classPackage;
	private String name;
	private Class<T> valueClass;
	private Modifier modifier;
	private boolean hasValue;
	private boolean hasDescription;
	private List<EnumValue<T>> valueSet = new ArrayList<>();

	public Enum() {}
	
	public Enum(Builder<T> builder) {
		this.sourceFolder = builder.sourceFolder;
		this.classPackage = builder.classPackage;
		this.name = builder.name;
		this.valueClass = builder.valueClass;
		this.modifier = builder.modifier;
		this.hasValue = builder.hasValue;
		this.hasDescription = builder.hasDescription;
		this.valueSet = builder.valueSet;
	}

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<T> getValueClass() {
		return valueClass;
	}

	public void setValueClass(Class<T> valueClass) {
		this.valueClass = valueClass;
	}

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public boolean isHasValue() {
		return hasValue;
	}

	public void setHasValue(boolean hasValue) {
		this.hasValue = hasValue;
	}

	public boolean isHasDescription() {
		return hasDescription;
	}

	public void setHasDescription(boolean hasDescription) {
		this.hasDescription = hasDescription;
	}

	public List<EnumValue<T>> getValueSet() {
		return valueSet;
	}

	public void setValueSet(List<EnumValue<T>> valueSet) {
		this.valueSet = valueSet;
	}

	@Override
	public String toString() {
		return "Enum [sourceFolder=" + sourceFolder + ", classPackage=" + classPackage + ", name=" + name
				+ ", valueClass=" + valueClass + ", modifier=" + modifier + ", hasValue=" + hasValue
				+ ", hasDescription=" + hasDescription + ", valueSet=" + valueSet + "]";
	}
	
	public static <T> Builder<T> builder () {
		return new Builder<T>();
	}

	public static final class Builder<T> {

		private String sourceFolder;
		private String classPackage;
		private String name;
		private Class<T> valueClass;
		private Modifier modifier;
		private boolean hasValue;
		private boolean hasDescription;
		private List<EnumValue<T>> valueSet = new ArrayList<>();
		
		public Builder<T> sourceFolder(String sourceFolder) {
			this.sourceFolder = sourceFolder;
			return this;
		}

		public Builder<T> classPackage(String classPackage) {
			this.classPackage = classPackage;
			return this;
		}

		public Builder<T> name(String name) {
			this.name = name;
			return this;
		}

		public Builder<T> valueClass(Class<T> valueClass) {
			this.valueClass = valueClass;
			return this;
		}

		public Builder<T> modifier(Modifier modifier) {
			this.modifier = modifier;
			return this;
		}

		public Builder<T> hasValue(boolean hasValue) {
			this.hasValue = hasValue;
			return this;
		}

		public Builder<T> hasDescription(boolean hasDescription) {
			this.hasDescription = hasDescription;
			return this;
		}

		public Builder<T> valueSet(List<EnumValue<T>> valueSet) {
			this.valueSet = valueSet;
			return this;
		}

		@SuppressWarnings("unchecked")
		public Builder<T> addValueSet(EnumValue<T>... valueSet) {
			if (valueSet != null) {
				if (this.valueSet == null) {
					this.valueSet = new ArrayList<>();
				}
				for (EnumValue<T> value : valueSet) {
					this.valueSet.add(value);
				}
			}
			return this;
		}

		public Enum<T> build() {
			return new Enum<T>(this);
		}
	}
}
