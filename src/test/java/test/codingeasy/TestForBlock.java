package test.codingeasy;

import org.junit.Test;

import codingeasy.ForBlock;

public class TestForBlock extends AbstractCodingTest {

	@Test
	public void testInfinity() {
		String statement = "System.out.println(\"done\");";

		ForBlock _for = new ForBlock();
		_for.code(statement);
		
		expect(_for, "for (; ; ) {\n\t" + statement + "\n}");
	}

	@Test
	public void testTo10() {
		String statement = "System.out.println(\"done\");";
		
		ForBlock _for = new ForBlock();
		_for.to("10").code(statement);
		
		expect(_for, "for (int i = 0; i < 10; i++) {\n\t" + statement + "\n}");
	}

	@Test
	public void testSimple() {
		String statement = "System.out.println(\"done\");";
		
		ForBlock _for = new ForBlock();
		_for.declare("int x = 0").test("x < 3").increment("x++").code(statement);
		
		expect(_for, "for (int x = 0; x < 3; x++) {\n\t" + statement + "\n}");
	}
	
}
