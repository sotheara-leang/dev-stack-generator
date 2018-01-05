package com.example.dev_stack_generator.dto;

import java.util.List;

public class MetaTableDto {

	private String 				name;
	private List<MetaColumnDto> metaColumnList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MetaColumnDto> getMetaColumnList() {
		return metaColumnList;
	}

	public void setMetaColumnList(List<MetaColumnDto> metaColumnList) {
		this.metaColumnList = metaColumnList;
	}

	@Override
	public String toString() {
		return "MetaTableDto [name=" + name + ", metaColumnList=" + metaColumnList + "]";
	}
}