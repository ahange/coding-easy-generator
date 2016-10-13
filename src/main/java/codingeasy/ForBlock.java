package codingeasy;

public class ForBlock extends CodeBlock<ForBlock> {

	private String declare = "";
	private String test = "";
	private String increment = "";

	protected ForBlock() {
		
	}
	
	public ForBlock to(String size) {
		declare("int i = 0");
		test("i < " + size);
		increment("i++");
		return this;
	}

	public ForBlock declare(String declare) {
		this.declare = declare;
		return this;
	}

	public ForBlock test(String test) {
		this.test = test;
		return this;
	}

	public ForBlock increment(String increment) {
		this.increment = increment;
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		code.append("for ");
		code.openParenthesis();
		code.append(declare).end().space();
		code.append(test).end().space();
		code.append(increment);
		code.closeParenthesis();
		super.print(code);
	}

}