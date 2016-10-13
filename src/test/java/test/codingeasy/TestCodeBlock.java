package test.codingeasy;

import org.junit.Test;

import codingeasy.Blocks;
import codingeasy.CodeBlock;

public class TestCodeBlock extends AbstractCodingTest {

	@Test
	public void testPrintHelloWorld() {
		String statement = "System.out.println(\"Hello World!\");";
		CodeBlock<?> block = Blocks._new(statement);
		
		expect(block, " {\n\t" + statement + "\n}");
	}

	@Test
	public void testPrintNestedBlocks() {
		String statement = "System.out.println(\"Hello World!\");";
		
		CodeBlock<?> block = Blocks._new("");
		block.code(Blocks._new(statement));
		
		expect(block, " {\n\t {\n\t\t" + statement + "\n\t}\n}");
	}
	
}
