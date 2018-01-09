package com.example.dev_stack_generator.generator;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dev_stack_generator.AbstractServiceTest;
import com.example.dev_stack_generator.common.SampleInterface;
import com.example.dev_stack_generator.platform.generator.pojo.PojoGenerator;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.LengthConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.NotEmptyConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.constraint.NotNullConstraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Constraint;
import com.example.dev_stack_generator.platform.generator.pojo.model.Field;
import com.example.dev_stack_generator.platform.generator.pojo.model.FieldInitializer;
import com.example.dev_stack_generator.platform.generator.pojo.model.Pojo;
import com.squareup.javapoet.JavaFile;

public class PojoGeneratorTest extends AbstractServiceTest {

	@Autowired
	private PojoGenerator pojoGenerator;
	
	@Test
	public void testGenerateDto() throws IOException {
		Pojo pojo = new Pojo();
		pojo.setHasBuilder(true);
		pojo.setClassName("UserDto");
		pojo.setClassPackage("com.example.dev_stack_generator.dto");
		pojo.setClassModifierList( Arrays.asList( Modifier.PUBLIC ) );
		pojo.setInterfaceClassList( Arrays.asList( Serializable.class, SampleInterface.class ));
		
		Field field1 = new Field();
		field1.setName("lastName");
		field1.setType(String.class);
		field1.setFieldInitializer(FieldInitializer.builder().format("$S", "Leang").build());
		pojo.getFieldList().add(field1);
		
		Field field2 = new Field();
		field2.setName("firstName");
		field2.setType(String.class);
		pojo.getFieldList().add(field2);
		
		Field field3 = new Field();
		field3.setName("age");
		field3.setType(Integer.class);
		pojo.getFieldList().add(field3);
		
		Field field4 = new Field();
		field4.setName("phoneNumberList");
		field4.setType(List.class);
		field4.getSubTypeList().add(String.class);
		pojo.getFieldList().add(field4);
		
		JavaFile generateFile = pojoGenerator.generate(pojo);
		generateFile.writeTo(System.out);
	}
	
	@Test
	public void testGenerateDtoWithBuilder() throws IOException {
		Pojo pojo = Pojo.builder()
				.className("UserDto")
				.classPackage("com.example.dev_stack_generator.dto")
				.addFieldList(Field.builder()
						.name("lastName")
						.type(String.class)
						.build())
				.addFieldList(Field.builder()
						.name("firstName")
						.type(String.class)
						.build())
				.addFieldList(Field.builder()
						.name("age")
						.type(Integer.class)
						.build())
				.addFieldList(Field.builder()
						.name("phoneNumberList")
						.type(List.class)
						.addSubTypeList(String.class)
						.build())
				.build();
		
		JavaFile generateFile = pojoGenerator.generate(pojo);
		generateFile.writeTo(System.out);
	}
	
	@Test
	public void testGenerateVo() throws IOException {
		Pojo pojo = new Pojo();
		pojo.setClassName("UserVo");
		pojo.setClassPackage("com.example.dev_stack_generator.vo");
		
		Constraint notNullConstraint = new NotNullConstraint();
		Constraint notEmptyConstraint = new NotEmptyConstraint();
		LengthConstraint lengthConstraint = new LengthConstraint();
		lengthConstraint.setMax(10);
		
		Field field1 = new Field();
		field1.setName("lastName");
		field1.setType(String.class);
		List<Constraint> constraintList = field1.getConstraintList();
		constraintList.add(notEmptyConstraint);
		constraintList.add(lengthConstraint);
		
		pojo.getFieldList().add(field1);
		
		Field field2 = new Field();
		field2.setName("firstName");
		field2.setType(String.class);
		field2.getConstraintList().add(notEmptyConstraint);
		pojo.getFieldList().add(field2);
		
		Field field3 = new Field();
		field3.setName("age");
		field3.setType(Integer.class);
		field3.getConstraintList().add(notNullConstraint);
		
		pojo.getFieldList().add(field3);
		
		JavaFile generateFile = pojoGenerator.generate(pojo);
		generateFile.writeTo(System.out);
	}
}
