package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.IfBlock;

public class TestIfBlock extends AbstractCodingTest {

	@Test
	public void testSimple() {
		IfBlock _if = Blocks._if("list.isEmpty()").code("return true;");
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n}");
	}

	@Test
	public void test() {
		IfBlock _if = Blocks._if("list.isEmpty()").code(Blocks._new("return true;"));
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n}");
	}

	@Test
	public void testElse() {
		IfBlock _if = Blocks._if("list.isEmpty()").code(Blocks._new("return true;"))._else(Blocks._new("return false;"));
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n} else {\n\treturn false;\n}");
	}

	@Test
	public void testElseIf() {
		IfBlock _if = Blocks._if("list.isEmpty()").code(Blocks._new("return true;"))._else(Blocks._if("!list.isEmpty()").code(Blocks._new("return false;")));
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n} else if (!list.isEmpty()) {\n\treturn false;\n}");
	}

	@Test
	public void testElseIfElse() {
		IfBlock _if = Blocks._if("list.isEmpty()")
					.code(Blocks._new("return true;"))
				._else(Blocks._if("!list.isEmpty()")
					.code("return false;")
				._else(Blocks._new("return false;")));
		
		expect(_if, "if (list.isEmpty()) {\n\treturn true;\n} else if (!list.isEmpty()) {\n\treturn false;\n} else {\n\treturn false;\n}");
	}
	
}
