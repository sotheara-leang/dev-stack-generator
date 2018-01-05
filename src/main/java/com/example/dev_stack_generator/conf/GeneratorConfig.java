package com.example.dev_stack_generator.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dev_stack_generator.platform.generator.dao.DaoGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.PojoGenerator;
import com.example.dev_stack_generator.platform.generator.type.EnumGenerator;

@Configuration
public class GeneratorConfig {

	@Bean
	public PojoGenerator pojoGenerator() {
		return new PojoGenerator();
	}
	
	@Bean
	public DaoGenerator daoGenerator() {
		return new DaoGenerator();
	}
	
	@Bean
	public EnumGenerator enumGenerator() {
		return new EnumGenerator();
	}
}
