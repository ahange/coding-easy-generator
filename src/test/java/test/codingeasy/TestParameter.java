package test.codingeasy;

import org.junit.Test;

import codingeasy.Parameter;
import codingeasy.Type;

public class TestParameter extends AbstractCodingTest {

	@Test
	public void testString() {
		Type type = new Type("test.A");
		Parameter parameter = new Parameter(type, "name");
		parameter.type(String.class);
		
		expect(parameter, "String name");
	}

	@Test
	public void testInt() {
		Type type = new Type("test.A");
		Parameter parameter = new Parameter(type, "age");
		parameter.type("int");
		
		expect(parameter, "int age");
	}

	@Test
	public void testWithAnnotation() {
		Type type = new Type("test.A");
		Parameter parameter = new Parameter(type, "age");
		parameter.type("int");
		parameter.addAnnotation("Param");
		
		expect(parameter, "@Param int age");
	}

	@Test
	public void testWith2Annotations() {
		Type type = new Type("test.A");
		Parameter parameter = new Parameter(type, "age");
		parameter.type("int");
		parameter.addAnnotation("Param");
		parameter.addAnnotation("NotNull");
		
		expect(parameter, "@Param @NotNull int age");
	}
	
}
