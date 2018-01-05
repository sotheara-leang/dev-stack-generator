package com.example.dev_stack_generator.platform.generator.pojo.model;

import java.util.ArrayList;
import java.util.List;

public class ClassBuilder {

	private String targetClassPackage;
	private String targetClassName;
	private List<BuilderField> fieldList = new ArrayList<BuilderField>();

	public ClassBuilder() {}

	public ClassBuilder(Builder builder) {
		this.targetClassPackage = builder.targetClassPackage;
		this.targetClassName = builder.targetClassName;
		this.fieldList = builder.fieldList;
	}

	public String getTargetClassPackage() {
		return targetClassPackage;
	}

	public void setTargetClassPackage(String targetClassPackage) {
		this.targetClassPackage = targetClassPackage;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public List<BuilderField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<BuilderField> fieldList) {
		this.fieldList = fieldList;
	}

	@Override
	public String toString() {
		return "InnerClassBuilder [targetClassPackage=" + targetClassPackage + ", targetClassName=" + targetClassName
				+ ", fieldList=" + fieldList + "]";
	}

	public static ClassBuilder builder() {
		return new ClassBuilder();
	}

	public static final class Builder {

		private String targetClassPackage;
		private String targetClassName;
		private List<BuilderField> fieldList = new ArrayList<BuilderField>();

		public Builder targetClassPackage(String targetClassPackage) {
			this.targetClassPackage = targetClassPackage;
			return this;
		}

		public Builder targetClassName(String targetClassName) {
			this.targetClassName = targetClassName;
			return this;
		}

		public Builder fieldList(List<BuilderField> fieldList) {
			this.fieldList = fieldList;
			return this;
		}
		
		public Builder addFieldList(BuilderField... fieldList) {
			if (fieldList != null) {
				if (this.fieldList == null) {
					this.fieldList = new ArrayList<>();
				}
				for (BuilderField field : fieldList) {
					this.fieldList.add(field);
				}
			}
			return this;
		}

		public ClassBuilder build() {
			return new ClassBuilder(this);
		}
	}
}
