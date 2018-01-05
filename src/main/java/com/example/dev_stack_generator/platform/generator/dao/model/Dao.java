package com.example.dev_stack_generator.platform.generator.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

public class Dao {

	private String sourceFolder;
	private String classPackage;
	private String className;
	private Class<?> classAnnotation;
	private Modifier classModifier;
	private List<Method> methodList = new ArrayList<>();
	
	public Dao() {}
	
	private Dao(Builder builder) {
		this.sourceFolder = builder.sourceFolder;
		this.classPackage = builder.classPackage;
		this.className = builder.className;
		this.classAnnotation = builder.classAnnotation;
		this.classModifier = builder.classModifier;
		this.methodList = builder.methodList;
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

	public Class<?> getClassAnnotation() {
		return classAnnotation;
	}

	public void setClassAnnotation(Class<?> classAnnotation) {
		this.classAnnotation = classAnnotation;
	}

	public Modifier getClassModifier() {
		return classModifier;
	}

	public void setClassModifier(Modifier classModifier) {
		this.classModifier = classModifier;
	}

	public List<Method> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<Method> methodList) {
		this.methodList = methodList;
	}

	@Override
	public String toString() {
		return "Dao [sourceFolder=" + sourceFolder + ", classPackage=" + classPackage + ", className=" + className
				+ ", classAnnotation=" + classAnnotation + ", classModifier=" + classModifier + ", methodList="
				+ methodList + "]";
	}
	
	public static Builder builder () {
		return new Builder();
	}

	public static final class Builder {
		
		private String sourceFolder;
		private String classPackage;
		private String className;
		private Class<?> classAnnotation;
		private Modifier classModifier;
		private List<Method> methodList = new ArrayList<>();
		
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

		public Builder classAnnotation(Class<?> classAnnotation) {
			this.classAnnotation = classAnnotation;
			return this;
		}

		public Builder classModifier(Modifier classModifier) {
			this.classModifier = classModifier;
			return this;
		}

		public Builder methodList(List<Method> methodList) {
			this.methodList = methodList;
			return this;
		}
		
		public Builder addMethodList(Method... methodList) {
			if (methodList != null) {
				if (this.methodList == null) {
					this.methodList = new ArrayList<>();
				}
				for (Method method : methodList) {
					this.methodList.add(method);
				}
			}
			return this;
		}
		
		public Dao build() {
			return new Dao(this);
		}
	}
}
