package com.example.dev_stack_generator.dto;

import java.util.Arrays;

public class MetaColumnDto {

	private String 		name;
	private String 		dataType;
	private int 		length = -1;
	private int 		precision = -1;
	private boolean 	nullable = true;
	private Object 		defaultValue;
	private String[]	valueSet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String[] getValueSet() {
		return valueSet;
	}

	public void setValueSet(String[] valueSet) {
		this.valueSet = valueSet;
	}

	@Override
	public String toString() {
		return "MetaColumnDto [name=" + name + ", dataType=" + dataType + ", length=" + length + ", precision="
				+ precision + ", nullable=" + nullable + ", defaultValue=" + defaultValue + ", valueSet="
				+ Arrays.toString(valueSet) + "]";
	}
}