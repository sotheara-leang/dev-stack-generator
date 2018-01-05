package com.example.dev_stack_generator.platform.generator.pojo.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class Field {

	private String name;
	private Class<?> type;
	private List<Class<?>> subTypeList = new ArrayList<>();
	private Modifier modifier;
	private Modifier getterModifier;
	private Modifier setterModifier;
	private FieldInitializer fieldInitializer;
	private List<Constraint> constraintList = new ArrayList<>();

	public Field() {}
	
	private Field(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.subTypeList = builder.subTypeList;
		this.modifier = builder.modifier;
		this.getterModifier = builder.getterModifier;
		this.setterModifier = builder.setterModifier;
		this.fieldInitializer = builder.fieldInitializer;
		this.constraintList = builder.constraintList;
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

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public Modifier getGetterModifier() {
		return getterModifier;
	}

	public void setGetterModifier(Modifier getterModifier) {
		this.getterModifier = getterModifier;
	}

	public Modifier getSetterModifier() {
		return setterModifier;
	}

	public void setSetterModifier(Modifier setterModifier) {
		this.setterModifier = setterModifier;
	}

	public FieldInitializer getFieldInitializer() {
		return fieldInitializer;
	}

	public void setFieldInitializer(FieldInitializer fieldInitializer) {
		this.fieldInitializer = fieldInitializer;
	}

	public List<Constraint> getConstraintList() {
		return constraintList;
	}

	public void setConstraintList(List<Constraint> constraintList) {
		this.constraintList = constraintList;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", subTypeList=" + subTypeList + ", modifier=" + modifier
				+ ", getterModifier=" + getterModifier + ", setterModifier=" + setterModifier + ", fieldInitializer="
				+ fieldInitializer + ", constraintList=" + constraintList + "]";
	}
	
	public static Builder builder () {
		return new Builder();
	}

	public static final class Builder {
		private String name;
		private Class<?> type;
		private List<Class<?>> subTypeList = new ArrayList<>();
		private Modifier modifier;
		private Modifier getterModifier;
		private Modifier setterModifier;
		private FieldInitializer fieldInitializer;
		private List<Constraint> constraintList = new ArrayList<>();
		
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
		
		public Builder modifier(Modifier modifier) {
			this.modifier = modifier;
			return this;
		}
		
		public Builder getterModifier(Modifier getterModifier) {
			this.getterModifier = getterModifier;
			return this;
		}
		
		public Builder setterModifier(Modifier setterModifier) {
			this.setterModifier = setterModifier;
			return this;
		}
		
		public Builder fieldInitializer(FieldInitializer fieldInitializer) {
			this.fieldInitializer = fieldInitializer;
			return this;
		}
		
		public Builder constraintList(List<Constraint> constraintList) {
			this.constraintList = constraintList;
			return this;
		}
		
		public Builder addConstraintList(Constraint... constraintList) {
			if (constraintList != null) {
				if (this.constraintList == null) {
					this.constraintList = new ArrayList<>();
				}
				for (Constraint constraint : constraintList) {
					this.constraintList.add(constraint);
				}
			}
			return this;
		}
		
		public Field build() {
			return new Field(this);
		}
	}
}
