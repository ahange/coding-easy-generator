package test.codingeasy;

import org.junit.Test;

import codingeasy.Code;
import codingeasy.EnumType;
import codingeasy.Modifier;
import codingeasy.Type;

public class TestType extends AbstractCodingTest {

	@Test
	public void testEmptyClass() {
		Type typeA = Code.newType("A");
		
		expect(typeA, "\npublic class A {\n}");
	}

	@Test
	public void testField() {
		Type typeA = Code.newType("A");
		typeA.field("name").type("String");
		
		expect(typeA, "\npublic class A {\n\tprivate String name;\n}");
	}

	@Test
	public void testMethod() {
		Type typeA = Code.newType("A");
		typeA.method("main").type("String").body("return \"Hello World\";");
		
		expect(typeA, "\npublic class A {\n\tpublic String main() {\n\t\treturn \"Hello World\";\n\t}\n}");
	}

	@Test
	public void testInterface() {
		Type typeA = Code.newType("A");
		typeA.type(EnumType.INTERFACE);
		typeA.method("main").type("String").addModifier(Modifier.ABSTRACT).removeModifier(Modifier.PUBLIC);
		
		expect(typeA, "\npublic interface A {\n\tString main();\n}");
	}
	
}