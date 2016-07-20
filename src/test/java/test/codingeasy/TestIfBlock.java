package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.IfBlock;

public class TestIfBlock extends AbstractCodingTest {

	@Test
	public void testSimple() {
		IfBlock _if = new IfBlock();
		_if.test("list.isEmpty()").code("return true;");
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n}");
	}

	@Test
	public void test() {
		IfBlock _if = new IfBlock();
		_if.test("list.isEmpty()").code(Blocks._new("return true;"));
		
		expect(_if, "if (list.isEmpty()) {\n\t {\n\t\treturn true;\n\t}\n}", true);
	}
	
}
