package com.example.dev_stack_generator.generator;

import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dev_stack_generator.AbstractServiceTest;
import com.example.dev_stack_generator.platform.generator.dao.DaoGenerator;
import com.example.dev_stack_generator.platform.generator.dao.model.Method;
import com.example.dev_stack_generator.platform.generator.dao.model.Dao;
import com.example.dev_stack_generator.platform.generator.dao.model.Parameter;
import com.squareup.javapoet.JavaFile;

public class DaoGeneratorTest extends AbstractServiceTest {

	@Autowired
	private DaoGenerator daoGenerator;
	
	@Test
	public void testGenerateSimpleMethod() throws IOException {
		Dao dao = new Dao();
		dao.setClassAnnotation(Repository.class);
		dao.setClassModifier(Modifier.PUBLIC);
		dao.setClassPackage("com.example.dev_stack_generator.dao");
		dao.setClassName("MyDao");
		
		Parameter param1 = new Parameter();
		param1.setName("param1");
		param1.setAlias("param");
		param1.setType(String.class);
		
		Method method1 = new Method();
		method1.setName("simple");
		method1.setReturnType(int.class);
		
		method1.getParameterList().add(param1);
		
		dao.getMethodList().add(method1);
		
		JavaFile generateFile = daoGenerator.generate(dao);
		generateFile.writeTo(System.out);
	}
}
