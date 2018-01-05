package com.example.dev_stack_generator.platform.generator.sql.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parameter {

	private String column;
	private String field;
	private Object value;
	private String jdbcType;
	private Mode mode;
}
