package test.codingeasy;

import org.junit.Test;

import codingeasy.Field;
import codingeasy.Javadoc;
import codingeasy.Modifier;
import codingeasy.Type;
import codingeasy.Type.TypeBuilder;

public class TestField extends AbstractCodingTest {

	@Test
	public void testStringType() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "name").type(String.class).build();
		expect(field, "\nprivate String name;");
	}

	@Test
	public void testStringTypeWithDefaultValueNull() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "name").type(String.class).value("null").build();
		expect(field, "\nprivate String name = null;");
	}

	@Test
	public void testStringTypeWithDefaultValueNotNull() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "name").type(String.class).stringValue("no name").build();
		expect(field, "\nprivate String name = \"no name\";");
	}

	@Test
	public void testIntTypeWithDefaultValueAndFinal() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "value").addModifier(Modifier.FINAL).type("int").value("1").build();
		expect(field, "\nprivate final int value = 1;");
	}

	@Test
	public void testIntTypeWithDefaultValueAndFinalAndStatic() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "value").addModifier(Modifier.STATIC).addModifier(Modifier.FINAL).type("int").value("1").build();
		expect(field, "\nprivate static final int value = 1;");
	}

	@Test
	public void testJavadoc() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field field = Field.builder(typeBuilder, "value").javadoc(Javadoc.builder().text("This is the field javadoc").build()).addModifier(Modifier.STATIC).addModifier(Modifier.FINAL).type("int").value("1").build();
		expect(field, "\n/**\n * This is the field javadoc\n */\nprivate static final int value = 1;");
	}

	@Test
	public void testGetter() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field.builder(typeBuilder, "value").type("int").getter().build();
		Type type = typeBuilder.build();
		
		expect(type, "\npackage tes;\npublic class A {\n\tprivate int value;\n\tpublic int getValue() {\n\t\treturn value;\n\t}\n}");
	}

	@Test
	public void testSetter() {
		TypeBuilder typeBuilder = Type.builder("test.A");
		Field.builder(typeBuilder, "value").type("int").setter().build();
		Type type = typeBuilder.build();
		
		expect(type, "\npackage tes;\npublic class A {\n\tprivate int value;\n\tpublic void setValue(int value) {\n\t\tthis.value = value;\n\t}\n}");
	}
	
}
