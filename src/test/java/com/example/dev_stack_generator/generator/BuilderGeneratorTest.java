package com.example.dev_stack_generator.generator;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dev_stack_generator.AbstractServiceTest;
import com.example.dev_stack_generator.platform.generator.pojo.ClassBuilderGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.model.BuilderField;
import com.example.dev_stack_generator.platform.generator.pojo.model.ClassBuilder;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

public class BuilderGeneratorTest extends AbstractServiceTest {

	@Autowired
	private ClassBuilderGenerator innerClassBuilderGenerator;

	@Test
	public void testGenerateInnerClassBuilderTypeSpec() throws IOException {
		ClassBuilder innerClassBuilder = new ClassBuilder();
		innerClassBuilder.setTargetClassPackage("com.example.dev_stack_generator.generator");
		innerClassBuilder.setTargetClassName("MyPojo");

		BuilderField buildField1 = new BuilderField();
		buildField1.setName("field1");
		buildField1.setType(String.class);
		innerClassBuilder.getFieldList().add(buildField1);
		
		BuilderField buildField2 = new BuilderField();
		buildField2.setName("field2");
		buildField2.setType(String.class);
		innerClassBuilder.getFieldList().add(buildField2);
		
		TypeSpec builderTypeSpec = innerClassBuilderGenerator.generateInnerClassBuilderTypeSpec(innerClassBuilder);
		JavaFile build = JavaFile.builder("com.example.dev_stack_generator.dto", builderTypeSpec).build();
		build.writeTo(System.out);
	}
}
