package codingeasy;

public abstract class TestBlock<R extends TestBlock<?>> extends CodeBlock<R> {

	protected final String keyword;
	protected String test;

	protected TestBlock(String keyword) {
		this.keyword = keyword;
	}

	@SuppressWarnings("unchecked")
	public R test(String test) {
		this.test = test;
		return (R) this;
	}

	@Override
	public void print(CodePrinter code) {
		code.append(keyword).space().openParenthesis().append(test).closeParenthesis();
		super.print(code);
	}

}