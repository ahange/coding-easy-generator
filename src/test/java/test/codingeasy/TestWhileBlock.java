package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.WhileBlock;

public class TestWhileBlock extends AbstractCodingTest {

	@Test
	public void testSimple() {
		WhileBlock block = Blocks._while("true").code("System.out.println(\"Hello\");");
		
		expect(block, "while (true) {\n\tSystem.out.println(\"Hello\");\n}");
	}
	
}
