package test.codingeasy;

import org.junit.Test;

import codingeasy.Annotation;

public class TestAnnotation extends AbstractCodingTest {

	@Test
	public void testValueParameter() {
		Annotation annotation = Annotation.builder("Target").value("ElementType.METHOD").build();
		expect(annotation, "@Target(ElementType.METHOD)");
	}

	@Test
	public void testNoParameter() {
		Annotation annotation = Annotation.builder("Target").build();
		expect(annotation, "@Target");
	}

	@Test
	public void testParameter() {
		Annotation annotation = Annotation.builder("Target").param("test", "1").build();
		expect(annotation, "@Target(test = 1)");
	}

	@Test
	public void testMultiParameters() {
		Annotation annotation = Annotation.builder("Target").stringParam("name", "Test").param("test", "1").build();
		expect(annotation, "@Target(name = \"Test\", test = 1)");
	}

	@Test
	public void testStringParameter() {
		Annotation annotation = Annotation.builder("Target").stringParam("test", "a").build();
		expect(annotation, "@Target(test = \"a\")");
	}
	
}
