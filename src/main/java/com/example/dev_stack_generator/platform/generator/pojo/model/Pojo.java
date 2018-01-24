package com.example.dev_stack_generator.platform.generator.pojo.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class Pojo {

	private String sourceFolder;
	private String classPackage;
	private String className;
	private List<Modifier> classModifierList = new ArrayList<>();
	private Class<?> superClass;
	private List<Class<?>> interfaceClassList = new ArrayList<>();
	private List<Field> fieldList = new ArrayList<Field>();
	private boolean hasToString;
	private boolean hasHashCode;
	private boolean hasBuilder;
	private boolean hasEquals;

	public Pojo() {}
	
	private Pojo(Builder builder) {
	    this.sourceFolder = builder.sourceFolder;
	    this.classPackage = builder.classPackage;
	    this.className = builder.className;
	    this.classModifierList = builder.classModifierList;
	    this.superClass = builder.superClass;
	    this.interfaceClassList = builder.interfaceClassList;
	    this.fieldList = builder.fieldList;
	    this.hasBuilder = builder.hasBuilder;
	    this.hasToString = builder.hasToString;
	    this.hasHashCode = builder.hasHashCode;
	    this.hasEquals = builder.hasEquals;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Modifier> getClassModifierList() {
		return classModifierList;
	}

	public void setClassModifierList(List<Modifier> classModifierList) {
		this.classModifierList = classModifierList;
	}

	public Class<?> getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class<?> superClass) {
		this.superClass = superClass;
	}

	public List<Class<?>> getInterfaceClassList() {
		return interfaceClassList;
	}

	public void setInterfaceClassList(List<Class<?>> interfaceClassList) {
		this.interfaceClassList = interfaceClassList;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}
	
	public boolean isHasBuilder() {
		return hasBuilder;
	}

	public void setHasBuilder(boolean hasBuilder) {
		this.hasBuilder = hasBuilder;
	}

	public boolean isHasToString() {
		return hasToString;
	}

	public void setHasToString( boolean hasToString ) {
		this.hasToString = hasToString;
	}

	public boolean isHasHashCode() {
		return hasHashCode;
	}

	public void setHasHashCode( boolean hasHashcode ) {
		this.hasHashCode = hasHashcode;
	}
	
	public boolean isHasEquals() {
		return hasEquals;
	}

	public void setHasEquals( boolean hasEquals ) {
		this.hasEquals = hasEquals;
	}

	@Override
	public String toString() {
		return "Pojo [sourceFolder=" + sourceFolder + ", classPackage=" + classPackage + ", className=" + className
				+ ", classModifierList=" + classModifierList + ", superClass=" + superClass + ", interfaceClassList="
				+ interfaceClassList + ", fieldList=" + fieldList + ", hasToString=" + hasToString + ", hasHashCode="
				+ hasHashCode + ", hasBuilder=" + hasBuilder + "]";
	}

	public static Builder builder () {
		return new Builder();
	}

	public static final class Builder {

		private String sourceFolder;
		private String classPackage;
		private String className;
		private List<Modifier> classModifierList = new ArrayList<>();
		private Class<?> superClass;
		private List<Class<?>> interfaceClassList = new ArrayList<>();
		private List<Field> fieldList = new ArrayList<Field>();
		private boolean hasToString;
		private boolean hasHashCode;
		private boolean hasBuilder;
		private boolean hasEquals;

		public Builder sourceFolder(String sourceFolder) {
			this.sourceFolder = sourceFolder;
			return this;
		}

		public Builder classPackage(String classPackage) {
			this.classPackage = classPackage;
			return this;
		}

		public Builder className(String className) {
			this.className = className;
			return this;
		}

		public Builder classModifierList(List<Modifier> classModifierList) {
			this.classModifierList = classModifierList;
			return this;
		}
		
		public Builder classModifierList(Modifier... classModifierList) {
			if (classModifierList != null) {
				if (this.classModifierList == null) {
					this.classModifierList = new ArrayList<>();
				}
				for (Modifier modifier : classModifierList) {
					this.classModifierList.add(modifier);
				}
			}
			return this;
		}

		public Builder superClass(Class<?> superClass) {
			this.superClass = superClass;
			return this;
		}

		public Builder interfaceClassList(List<Class<?>> interfaceClassList) {
			this.interfaceClassList = interfaceClassList;
			return this;
		}

		public Builder fieldList(List<Field> fieldList) {
			this.fieldList = fieldList;
			return this;
		}
		
		public Builder addFieldList(Field... fieldList) {
			if (fieldList != null) {
				if (this.fieldList == null) {
					this.fieldList = new ArrayList<>();
				}
				for (Field field : fieldList) {
					this.fieldList.add(field);
				}
			}
			return this;
		}
		
		public Builder hasBuilder(boolean hasBuilder) {
			this.hasBuilder = hasBuilder;
			return this;
		}
		
		public Builder hasToString( boolean hasToString ) {
			this.hasToString = hasToString;
			return this;
		}

		public Builder hasHashCode( boolean hasHashCode ) {
			this.hasHashCode = hasHashCode;
			return this;
		}
		
		public Builder hasEquals( boolean hasEquals ) {
			this.hasEquals = hasEquals;
			return this;
		}

		public Pojo build() {
			return new Pojo(this);
		}
	}
}
