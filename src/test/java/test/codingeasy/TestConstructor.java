package test.codingeasy;

import org.junit.Test;

import codingeasy.Annotation;
import codingeasy.Blocks;
import codingeasy.Constructor;
import codingeasy.Constructor.ConstructorBuilder;
import codingeasy.Type;
import codingeasy.Type.TypeBuilder;

public class TestConstructor extends AbstractCodingTest {

	@Test
	public void test() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Constructor constructor = Constructor.builder(typeBuilder).build();
		
		expect(constructor, "\npublic A() {\n}");
	}

	@Test
	public void testWithParameter() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		ConstructorBuilder constructorBuilder = Constructor.builder(typeBuilder);
		constructorBuilder.param("name").type("String").build();
		Constructor constructor = constructorBuilder.build();
		
		expect(constructor, "\npublic A(String name) {\n}");
	}

	@Test
	public void testWithParameters() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		ConstructorBuilder constructorBuilder = Constructor.builder(typeBuilder);
		constructorBuilder.param("name").type("String").build();
		constructorBuilder.param("age").type("int").build();
		Constructor constructor = constructorBuilder.build();
		
		expect(constructor, "\npublic A(String name, int age) {\n}");
	}

	@Test
	public void testWithAnnotation() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		ConstructorBuilder constructorBuilder = Constructor.builder(typeBuilder);
		constructorBuilder.param("name").type("String").build();
		constructorBuilder.param("age").type("int").build();
		constructorBuilder.addAnnotation(Annotation.builder("Deprecated").build());
		Constructor constructor = constructorBuilder.build();
		
		expect(constructor, "\n@Deprecated\npublic A(String name, int age) {\n}");
	}

	@Test
	public void testWithAnnotations() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		ConstructorBuilder constructorBuilder = Constructor.builder(typeBuilder);
		constructorBuilder.param("name").type("String").build();
		constructorBuilder.param("age").type("int").build();
		constructorBuilder.addAnnotation(Annotation.builder("Deprecated").build());
		constructorBuilder.addAnnotation(Annotation.builder("Documented").build());
		Constructor constructor = constructorBuilder.build();
		
		expect(constructor, "\n@Deprecated\n@Documented\npublic A(String name, int age) {\n}");
	}

	@Test
	public void testHelloWorld() {
		String statement = "System.out.println(\"Hello World!\");";
		
		TypeBuilder typeBuilder = Type.builder("test.A");
		ConstructorBuilder constructorBuilder = Constructor.builder(typeBuilder);
		constructorBuilder.body(Blocks._new(statement));
		Constructor constructor = constructorBuilder.build();
		
		expect(constructor, "\npublic A() {\n\t" + statement + "\n}");
	}
	
}
