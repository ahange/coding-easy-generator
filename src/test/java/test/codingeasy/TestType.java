package test.codingeasy;

import java.util.ArrayList;

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
	public void testConstructor() {
		Type typeA = Code.newType("A").constructor(constructor -> constructor.addModifier(Modifier.PUBLIC)).build();
		
		expect(typeA, "\npublic class A {\n\tpublic A() {\n\t}\n}");
	}

	@Test
	public void testImport() {
		Type typeA = Code.newType("A").superClass(ArrayList.class).build();
		
		expect(typeA, "\nimport java.util.ArrayList;\npublic class A extends ArrayList {\n}");
	}

	@Test
	public void testField() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.field("name", field -> {
			field.type("String");
		});
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic class A {\n\tString name;\n}");
	}

	@Test
	public void testMethod() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.method("main", (method) -> {
			method.addModifier(Modifier.PUBLIC).returnType("String").body("return \"Hello World\";");
		});
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic class A {\n\tpublic String main() {\n\t\treturn \"Hello World\";\n\t}\n}");
	}

	@Test
	public void testInterface() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.type(EnumType.INTERFACE);
		typeBuilder.method("main", method -> { 
			method.returnType("String").addModifier(Modifier.ABSTRACT);
		});
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic interface A {\n\tabstract String main();\n}");
	}

	@Test
	public void testRunnable() {
		TypeBuilder typeBuilder = Code.newType("A");
		typeBuilder.addInterface("Runnable");
		typeBuilder.method("run", method -> { 
			method.addModifier(Modifier.PUBLIC);
		});
		Type typeA = typeBuilder.build();
		
		expect(typeA, "\npublic class A implements Runnable {\n\tpublic void run() {\n\t}\n}");
	}
	
}