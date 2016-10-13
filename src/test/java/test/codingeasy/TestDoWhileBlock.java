package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.DoWhileBlock;

public class TestDoWhileBlock extends AbstractCodingTest {

	@Test
	public void testSimple() {
		DoWhileBlock block = Blocks._do_while("true").code("System.out.println(\"Hello\");");
		
		expect(block, "do {\n\tSystem.out.println(\"Hello\");\n} while (true);");
	}
	
}
