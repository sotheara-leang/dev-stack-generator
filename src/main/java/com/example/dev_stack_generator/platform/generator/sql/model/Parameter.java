package com.example.dev_stack_generator.platform.generator.sql.model;

public class Parameter {

	private String column;
	private String field;
	private Object value;
	private String jdbcType;
	private Mode mode;

	public Parameter() {
	}

	public Parameter( Builder builder ) {
		this.column = builder.column;
		this.field = builder.field;
		this.value = builder.value;
		this.jdbcType = builder.jdbcType;
		this.mode = builder.mode;
	}

	public String getColumn() {
		return column;
	}
	public void setColumn( String column ) {
		this.column = column;
	}
	public String getField() {
		return field;
	}
	public void setField( String field ) {
		this.field = field;
	}
	public Object getValue() {
		return value;
	}
	public void setValue( Object value ) {
		this.value = value;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType( String jdbcType ) {
		this.jdbcType = jdbcType;
	}
	public Mode getMode() {
		return mode;
	}
	public void setMode( Mode mode ) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "Parameter [column=" + column + ", field=" + field + ", value=" + value + ", jdbcType=" + jdbcType
				+ ", mode=" + mode + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String column;
		private String field;
		private Object value;
		private String jdbcType;
		private Mode mode;

		public Builder column( String column ) {
			this.column = column;
			return this;
		}

		public Builder field( String field ) {
			this.field = field;
			return this;
		}

		public Builder value( Object value ) {
			this.value = value;
			return this;
		}

		public Builder jdbcType( String jdbcType ) {
			this.jdbcType = jdbcType;
			return this;
		}

		public Builder mode( Mode mode ) {
			this.mode = mode;
			return this;
		}

		public Parameter build() {
			return new Parameter( this );
		}
	}
}
