package test.codingeasy;

import org.junit.Test;

import codingeasy.Annotation;

public class TestAnnotation extends AbstractCodingTest {

	@Test
	public void testValueParameter() {
		Annotation annotation = new Annotation("Target");
		annotation.value("ElementType.METHOD");
		
		expect(annotation, "@Target(ElementType.METHOD)");
	}

	@Test
	public void testNoParameter() {
		Annotation annotation = new Annotation("Target");
		expect(annotation, "@Target");
	}

	@Test
	public void testParameter() {
		Annotation annotation = new Annotation("Target");
		annotation.param("test").value("1");
		
		expect(annotation, "@Target(test = 1)");
	}

	@Test
	public void testStringParameter() {
		Annotation annotation = new Annotation("Target");
		annotation.param("test").stringValue("a");
		
		expect(annotation, "@Target(test = \"a\")");
	}
	
}
