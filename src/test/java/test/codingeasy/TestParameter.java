package test.codingeasy;

import org.junit.Test;

import codingeasy.Annotation;
import codingeasy.Method;
import codingeasy.Method.MethodBuilder;
import codingeasy.Parameter;
import codingeasy.Type;
import codingeasy.Type.TypeBuilder;

public class TestParameter extends AbstractCodingTest {

	@Test
	public void testString() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test");
		Parameter parameter = Parameter.builder(methodBuilder, "name").type(String.class).build();
		expect(parameter, "String name");
	}

	@Test
	public void testInt() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test");
		Parameter parameter = Parameter.builder(methodBuilder, "age").type("int").build();
		expect(parameter, "int age");
	}

	@Test
	public void testWithAnnotation() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test");
		Parameter parameter = Parameter.builder(methodBuilder, "age").type("int").addAnnotation(Annotation.builder("Param").build()).build();
		expect(parameter, "@Param int age");
	}

	@Test
	public void testWith2Annotations() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		MethodBuilder methodBuilder = Method.builder(typeBuilder, "test");
		Parameter parameter = Parameter.builder(methodBuilder, "age").type("int").addAnnotation(Annotation.builder("Param").build()).addAnnotation(Annotation.builder("NotNull").build()).build();
		expect(parameter, "@Param @NotNull int age");
	}
	
}
