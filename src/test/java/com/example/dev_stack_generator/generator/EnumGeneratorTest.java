package com.example.dev_stack_generator.generator;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dev_stack_generator.AbstractServiceTest;
import com.example.dev_stack_generator.platform.generator.type.EnumGenerator;
import com.example.dev_stack_generator.platform.generator.type.model.Enum;
import com.example.dev_stack_generator.platform.generator.type.model.EnumValue;
import com.squareup.javapoet.JavaFile;

public class EnumGeneratorTest extends AbstractServiceTest {

	@Autowired
	private EnumGenerator enumGenerator;
	
	@Test
	public void testSimpleEnum() throws IOException {
		Enum<String> myEnum = new Enum<>();
		myEnum.setHasValue(false);
		myEnum.setHasDescription(false);
		
		myEnum.setName("MySimpleEnum");
		myEnum.setClassPackage("com.example.dev_stack_generator.dto.type");
		myEnum.setModifier(Modifier.PUBLIC);

		EnumValue<String> value1 = new EnumValue<>();
		value1.setName("VALUE1");
		
		EnumValue<String> value2 = new EnumValue<>();
		value2.setName("VALUE2");
		
		List<EnumValue<String>> valueSet = myEnum.getValueSet();
		valueSet.add(value1);
		valueSet.add(value2);
		
		JavaFile javaFile = enumGenerator.generate(myEnum);
		javaFile.writeTo(System.out);
	}
	
	@Test
	public void testDescriptiveEnum() throws IOException {
		Enum<String> myEnum = new Enum<>();
		myEnum.setHasValue(false);
		myEnum.setHasDescription(true);
		
		myEnum.setName("MySimpleEnum");
		myEnum.setClassPackage("com.example.dev_stack_generator.dto.type");
		myEnum.setModifier(Modifier.PUBLIC);

		EnumValue<String> value1 = new EnumValue<>();
		value1.setName("VALUE1");
		value1.setDescription("This is description 1");
		
		EnumValue<String> value2 = new EnumValue<>();
		value2.setName("VALUE2");
		value2.setDescription("This is description 1");
		
		List<EnumValue<String>> valueSet = myEnum.getValueSet();
		valueSet.add(value1);
		valueSet.add(value2);
		
		JavaFile javaFile = enumGenerator.generate(myEnum);
		javaFile.writeTo(System.out);
	}
	
	@Test
	public void testValuableEnum() throws IOException {
		Enum<Integer> myEnum = new Enum<>();
		myEnum.setHasValue(true);
		myEnum.setHasDescription(false);
		myEnum.setValueClass(Integer.class);
		
		myEnum.setName("MySimpleEnum");
		myEnum.setClassPackage("com.example.dev_stack_generator.dto.type");
		myEnum.setModifier(Modifier.PUBLIC);

		EnumValue<Integer> value1 = new EnumValue<>();
		value1.setName("VALUE1");
		value1.setValue(1);
		
		EnumValue<Integer> value2 = new EnumValue<>();
		value2.setName("VALUE2");
		value2.setValue(2);

		List<EnumValue<Integer>> valueSet = myEnum.getValueSet();
		valueSet.add(value1);
		valueSet.add(value2);
		
		JavaFile javaFile = enumGenerator.generate(myEnum);
		javaFile.writeTo(System.out);
	}
	
	@Test
	public void testValueAndDescriptionEnum() throws IOException {
		Enum<String> myEnum = new Enum<>();
		myEnum.setValueClass(String.class);
		myEnum.setHasValue(true);
		myEnum.setHasDescription(true);
		
		myEnum.setName("MySimpleEnum");
		myEnum.setClassPackage("com.example.dev_stack_generator.dto.type");
		myEnum.setModifier(Modifier.PUBLIC);

		EnumValue<String> value1 = new EnumValue<>();
		value1.setName("VALUE1");
		value1.setValue("This is value1");
		value1.setDescription("This is description1");
		
		EnumValue<String> value2 = new EnumValue<>();
		value2.setName("VALUE2");
		value2.setValue("This is value1");
		value2.setDescription("This is description2");
		
		List<EnumValue<String>> valueSet = myEnum.getValueSet();
		valueSet.add(value1);
		valueSet.add(value2);
		
		JavaFile javaFile = enumGenerator.generate(myEnum);
		javaFile.writeTo(System.out);
	}
	
	@Test
	public void testDoubuleValueAndDescriptionEnum() throws IOException {
		Enum<Double> myEnum = new Enum<>();
		myEnum.setValueClass(Double.class);
		myEnum.setHasValue(true);
		myEnum.setHasDescription(true);
		
		myEnum.setName("MySimpleEnum");
		myEnum.setClassPackage("com.example.dev_stack_generator.dto.type");
		myEnum.setModifier(Modifier.PUBLIC);

		EnumValue<Double> value1 = new EnumValue<>();
		value1.setName("VALUE1");
		value1.setValue(1D);
		value1.setDescription("This is description1");
		
		EnumValue<Double> value2 = new EnumValue<>();
		value2.setName("VALUE2");
		value2.setValue(2D);
		value2.setDescription("This is description2");
		
		List<EnumValue<Double>> valueSet = myEnum.getValueSet();
		valueSet.add(value1);
		valueSet.add(value2);
		
		JavaFile javaFile = enumGenerator.generate(myEnum);
		javaFile.writeTo(System.out);
	}
}