package test.codingeasy;

import org.junit.Test;

import codingeasy.Code;
import codingeasy.EnumType;
import codingeasy.Modifier;
import codingeasy.Type;
import codingeasy.Type.TypeBuilder;

public class TestType extends AbstractCodingTest {

	@Test
	public void testEmptyClass() {
		Type typeA = Code.newType("A").build();
		
		expect(typeA, "\npublic class A {\n}");
	}

	@Test
	public void testField() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.field("name").type("String").build();
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic class A {\n\tprivate String name;\n}");
	}

	@Test
	public void testMethod() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.method("main").addModifier(Modifier.PUBLIC).returnType("String").body("return \"Hello World\";").build();
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic class A {\n\tpublic String main() {\n\t\treturn \"Hello World\";\n\t}\n}");
	}

	@Test
	public void testInterface() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.type(EnumType.INTERFACE);
		typeBuilder.method("main").returnType("String").addModifier(Modifier.PUBLIC).addModifier(Modifier.ABSTRACT).build();
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic interface A {\n\tString main();\n}");
	}
	
}