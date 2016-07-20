package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.Method;
import codingeasy.Type;

public class TestMethod extends AbstractCodingTest {

	@Test
	public void test() {
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		
		expect(method, "\n\tpublic test() {\n\t}");
	}

	@Test
	public void testWithParameter() {
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		method.param("name").type("String");
		
		expect(method, "\n\tpublic test(String name) {\n\t}");
	}

	@Test
	public void testWithParameters() {
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		method.param("name").type("String");
		method.param("age").type("int");
		
		expect(method, "\n\tpublic test(String name, int age) {\n\t}");
	}

	@Test
	public void testWithAnnotation() {
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		method.param("name").type("String");
		method.param("age").type("int");
		method.addAnnotation("Deprecated");
		
		expect(method, "\n\t@Deprecated\n\tpublic test(String name, int age) {\n\t}");
	}

	@Test
	public void testWithAnnotations() {
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		method.param("name").type("String");
		method.param("age").type("int");
		method.addAnnotation("Deprecated");
		method.addAnnotation("Documented");
		
		expect(method, "\n\t@Deprecated\n\t@Documented\n\tpublic test(String name, int age) {\n\t}");
	}

	@Test
	public void testHelloWorld() {
		String statement = "System.out.println(\"Hello World!\");";
		
		Type type = new Type("test.A");
		Method method = new Method(type, "test");
		method.body(Blocks._new(statement));
		
		expect(method, "\n\tpublic test() {\n\t\t" + statement + "\n\t}");
	}
	
}
