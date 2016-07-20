package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.Constructor;
import codingeasy.Type;

public class TestConstructor extends AbstractCodingTest {

	@Test
	public void test() {
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		
		expect(constructor, "\n\tpublic A() {\n\t}");
	}

	@Test
	public void testWithParameter() {
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		constructor.param("name").type("String");
		
		expect(constructor, "\n\tpublic A(String name) {\n\t}");
	}

	@Test
	public void testWithParameters() {
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		constructor.param("name").type("String");
		constructor.param("age").type("int");
		
		expect(constructor, "\n\tpublic A(String name, int age) {\n\t}");
	}

	@Test
	public void testWithAnnotation() {
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		constructor.param("name").type("String");
		constructor.param("age").type("int");
		constructor.addAnnotation("Deprecated");
		
		expect(constructor, "\n\t@Deprecated\n\tpublic A(String name, int age) {\n\t}");
	}

	@Test
	public void testWithAnnotations() {
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		constructor.param("name").type("String");
		constructor.param("age").type("int");
		constructor.addAnnotation("Deprecated");
		constructor.addAnnotation("Documented");
		
		expect(constructor, "\n\t@Deprecated\n\t@Documented\n\tpublic A(String name, int age) {\n\t}");
	}

	@Test
	public void testHelloWorld() {
		String statement = "System.out.println(\"Hello World!\");";
		
		Type type = new Type("test.A");
		Constructor constructor = new Constructor(type);
		constructor.body(Blocks._new(statement));
		
		expect(constructor, "\n\tpublic A() {\n\t\t" + statement + "\n\t}");
	}
	
}
