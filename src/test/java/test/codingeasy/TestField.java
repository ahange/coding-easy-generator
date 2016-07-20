package test.codingeasy;

import org.junit.Test;

import codingeasy.Field;
import codingeasy.Modifier;
import codingeasy.Type;

public class TestField extends AbstractCodingTest {

	@Test
	public void testStringType() {
		Type type = new Type("test.A");
		Field field = new Field(type, "name");
		field.type(String.class);
		
		expect(field, "\nprivate String name;");
	}

	@Test
	public void testStringTypeWithDefaultValueNull() {
		Type type = new Type("test.A");
		Field field = new Field(type, "name");
		field.type(String.class);
		field.value("null");
		
		expect(field, "\nprivate String name = null;");
	}

	@Test
	public void testStringTypeWithDefaultValueNotNull() {
		Type type = new Type("test.A");
		Field field = new Field(type, "name");
		field.type(String.class);
		field.stringValue("no name");
		
		expect(field, "\nprivate String name = \"no name\";");
	}

	@Test
	public void testIntTypeWithDefaultValueAndFinal() {
		Type type = new Type("test.A");
		Field field = new Field(type, "value");
		field.addModifier(Modifier.FINAL);
		field.type("int");
		field.value("1");
		
		expect(field, "\nprivate final int value = 1;");
	}

	@Test
	public void testIntTypeWithDefaultValueAndFinalAndStatic() {
		Type type = new Type("test.A");
		Field field = new Field(type, "value");
		field.addModifier(Modifier.STATIC).addModifier(Modifier.FINAL);
		field.type("int");
		field.value("1");
		
		expect(field, "\nprivate static final int value = 1;");
	}
	
}
