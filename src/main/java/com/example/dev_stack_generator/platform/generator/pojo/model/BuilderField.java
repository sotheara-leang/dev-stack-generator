package com.example.dev_stack_generator.platform.generator.pojo.model;

import java.util.ArrayList;
import java.util.List;

public class BuilderField {

	private String name;
	private Class<?> type;
	private List<Class<?>> subTypeList = new ArrayList<>();
	private FieldInitializer fieldInitializer;

	public BuilderField() {}
	
	public BuilderField(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.subTypeList = builder.subTypeList;
		this.fieldInitializer = builder.fieldInitializer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public List<Class<?>> getSubTypeList() {
		return subTypeList;
	}

	public void setSubTypeList(List<Class<?>> subTypeList) {
		this.subTypeList = subTypeList;
	}

	public FieldInitializer getFieldInitializer() {
		return fieldInitializer;
	}

	public void setFieldInitializer( FieldInitializer fieldInitializer ) {
		this.fieldInitializer = fieldInitializer;
	}
	
	@Override
	public String toString() {
		return "BuilderField [name=" + name + ", type=" + type + ", subTypeList=" + subTypeList + ", fieldInitializer="
				+ fieldInitializer + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String name;
		private Class<?> type;
		private List<Class<?>> subTypeList = new ArrayList<>();
		private FieldInitializer fieldInitializer;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(Class<?> type) {
			this.type = type;
			return this;
		}

		public Builder subTypeList(List<Class<?>> subTypeList) {
			this.subTypeList = subTypeList;
			return this;
		}

		public Builder addSubTypeList(Class<?>... subTypeList) {
			if (subTypeList != null) {
				if (this.subTypeList == null) {
					this.subTypeList = new ArrayList<>();
				}
				for (Class<?> subType : subTypeList) {
					this.subTypeList.add(subType);
				}
			}
			return this;
		}
		
		public Builder fieldInitializer( FieldInitializer fieldInitializer ) {
			this.fieldInitializer = fieldInitializer;
			return this;
		}

		public BuilderField build() {
			return new BuilderField(this);
		}
	}
}
