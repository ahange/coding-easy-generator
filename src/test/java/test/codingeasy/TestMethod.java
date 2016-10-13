package test.codingeasy;

import org.junit.Test;

import codingeasy.Annotation;
import codingeasy.Blocks;
import codingeasy.Method;
import codingeasy.Method.MethodBuilder;
import codingeasy.Modifier;
import codingeasy.Type;
import codingeasy.Type.TypeBuilder;

public class TestMethod extends AbstractCodingTest {

	@Test
	public void test() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Method method = Method.builder(typeBuilder, "test").returnType("void").addModifier(Modifier.PUBLIC).build();
		
		expect(method, "\npublic void test() {\n}");
	}

	@Test
	public void testWithParameter() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("void").addModifier(Modifier.PUBLIC);
		methodBuilder.param("name", param -> {
			param.type("String");
		});
		Method method = methodBuilder.build();
		
		expect(method, "\npublic void test(String name) {\n}");
	}

	@Test
	public void testWithParameters() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("void").addModifier(Modifier.PUBLIC);
		methodBuilder.param("name", param -> {
			param.type("String");
		});
		methodBuilder.param("age", param -> {
			param.type("int");
		});
		Method method = methodBuilder.build();
		
		expect(method, "\npublic void test(String name, int age) {\n}");
	}

	@Test
	public void testWithAnnotation() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("String").addModifier(Modifier.PUBLIC);
		methodBuilder.param("name", param -> {
			param.type("String");
		});
		methodBuilder.param("age", param -> {
			param.type("int");
		});
		methodBuilder.addAnnotation(Annotation.builder("Deprecated").build());
		Method method = methodBuilder.build();
		
		expect(method, "\n@Deprecated\npublic String test(String name, int age) {\n}");
	}

	@Test
	public void testWithAnnotations() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("int").addModifier(Modifier.PUBLIC);
		methodBuilder.param("name", param -> {
			param.type("String");
		});
		methodBuilder.param("age", param -> {
			param.type("int");
		});
		methodBuilder.addAnnotation(Annotation.builder("Deprecated").build());
		methodBuilder.addAnnotation(Annotation.builder("Documented").build());
		Method method = methodBuilder.build();
		
		expect(method, "\n@Deprecated\n@Documented\npublic int test(String name, int age) {\n}");
	}

	@Test
	public void testHelloWorld() {
		String statement = "System.out.println(\"Hello World!\");";
		
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("int").addModifier(Modifier.PUBLIC);
		methodBuilder.body(Blocks._new(statement));
		Method method = methodBuilder.build();
		
		expect(method, "\npublic int test() {\n\t" + statement + "\n}");
	}

	@Test
	public void testJavadoc() {
		String statement = "System.out.println(\"Hello World!\");";
		
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType(String.class).addModifier(Modifier.PUBLIC);
		methodBuilder.javadoc(javadoc -> javadoc.text("Testing javadoc"));
		methodBuilder.body(Blocks._new(statement));
		Method method = methodBuilder.build();
		
		expect(method, "\n/**\n * Testing javadoc\n */\npublic String test() {\n\t" + statement + "\n}");
	}

	@Test
	public void testJavadocWithParameters() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test").returnType("String").addModifier(Modifier.PUBLIC);
		methodBuilder.javadoc(javadoc -> javadoc.text("Testing javadoc").returnDoc("the user's name"));
		methodBuilder.param("name", param -> {
			param.type("String").javadoc(javadoc -> javadoc.text("the user's name"));
		});
		methodBuilder.param("age", param -> {
			param.type("int").javadoc(javadoc -> javadoc.text("the user's age"));
		});
		Method method = methodBuilder.build();
		
		expect(method, "\n/**\n * Testing javadoc\n * @param name the user's name\n * @param age the user's age\n * @return the user's name\n */\npublic String test(String name, int age) {\n}");
	}
	
}
