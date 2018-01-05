package com.example.dev_stack_generator.platform.generator.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class Method {

	private String name;
	private Class<?> returnType;
	private List<Class<?>> returnSubTypeList = new ArrayList<>();
	private List<Modifier> modifierList = new ArrayList<>();
	private List<Parameter> parameterList = new ArrayList<>();

	public Method() {}

	public Method(Builder builder) {
		this.name = builder.name;
		this.returnType = builder.returnType;
		this.returnSubTypeList = builder.returnSubTypeList;
		this.modifierList = builder.modifierList;
		this.parameterList = builder.parameterList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public List<Modifier> getModifierList() {
		return modifierList;
	}

	public void setModifierList(List<Modifier> modifierList) {
		this.modifierList = modifierList;
	}

	public List<Class<?>> getReturnSubTypeList() {
		return returnSubTypeList;
	}

	public void setReturnSubTypeList(List<Class<?>> returnSubTypeList) {
		this.returnSubTypeList = returnSubTypeList;
	}

	public List<Parameter> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
	}

	@Override
	public String toString() {
		return "Method [name=" + name + ", returnType=" + returnType + ", modifierList=" + modifierList
				+ ", returnSubTypeList=" + returnSubTypeList + ", parameterList=" + parameterList + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		
		private String name;
		private Class<?> returnType;
		private List<Class<?>> returnSubTypeList = new ArrayList<>();
		private List<Modifier> modifierList = new ArrayList<>();
		private List<Parameter> parameterList = new ArrayList<>();
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder returnType(Class<?> returnType) {
			this.returnType = returnType;
			return this;
		}

		public Builder returnSubTypeList(List<Class<?>> returnSubTypeList) {
			this.returnSubTypeList = returnSubTypeList;
			return this;
		}
		
		public Builder addReturnSubTypeList(Class<?>... returnSubTypeList) {
			if (returnSubTypeList != null) {
				if (this.returnSubTypeList == null) {
					this.returnSubTypeList = new ArrayList<>();
				}
				for (Class<?> clazz : returnSubTypeList) {
					this.returnSubTypeList.add(clazz);
				}
			}
			return this;
		}

		public Builder modifierList(List<Modifier> modifierList) {
			this.modifierList = modifierList;
			return this;
		}
		
		public Builder addModifierList(Modifier... modifierList) {
			if (modifierList != null) {
				if (this.modifierList == null) {
					this.modifierList = new ArrayList<>();
				}
				for (Modifier modifier : modifierList) {
					this.modifierList.add(modifier);
				}
			}
			return this;
		}

		public Builder parameterList(List<Modifier> parameterList) {
			this.modifierList = parameterList;
			return this;
		}

		public Builder addParameterList(Parameter... parameterList) {
			if (parameterList != null) {
				if (this.parameterList == null) {
					this.parameterList = new ArrayList<>();
				}
				for (Parameter param : parameterList) {
					this.parameterList.add(param);
				}
			}
			return this;
		}
		
		public Method build() {
			return new Method(this);
		}
	}
}
