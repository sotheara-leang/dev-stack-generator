package com.example.dev_stack_generator.platform.generator.dao;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.example.dev_stack_generator.platform.generator.dao.model.Dao;
import com.example.dev_stack_generator.platform.generator.dao.model.Method;
import com.example.dev_stack_generator.platform.generator.pojo.util.Utils;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public class DaoGenerator {
	
	protected MethodGenerator methodGenerator;
	
	public DaoGenerator() {
		this (new MethodGenerator());
	}
	
	public DaoGenerator(MethodGenerator methodGenerator) {
		this.methodGenerator = methodGenerator;
	}
	
	public void generateFile(Dao dao) throws IOException {
		JavaFile daoFile = generate(dao);
		daoFile.writeTo(Paths.get(dao.getSourceFolder()));
	}

	public JavaFile generate(Dao dao) {
		Assert.notNull(dao, "dao must not be null");
		
		Modifier daoClassModifier = dao.getClassModifier() == null ? Modifier.PUBLIC : dao.getClassModifier();
		
		Class<?> classAnnotation = dao.getClassAnnotation() == null ? Repository.class : dao.getClassAnnotation();
		
		String className = Utils.formatClassName(dao.getClassName());
		
		com.squareup.javapoet.TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
					.addModifiers(daoClassModifier)
					.addAnnotation(classAnnotation);
		
		List<Method> methodList = dao.getMethodList();
		if (methodList != null) {
			for (Method abstractMethod : methodList) {
				MethodSpec methodSpec = methodGenerator.generateMethodSpec(abstractMethod);
				if (methodSpec != null) {
					typeSpecBuilder.addMethod(methodSpec);
				}
			}
		}
		
		return JavaFile.builder(dao.getClassPackage(), typeSpecBuilder.build()).build();
	}
}
