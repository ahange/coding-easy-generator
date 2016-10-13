package codingeasy;

public class Blocks {

	public static final IfBlock _if(String test) {
		return new IfBlock().test(test);
	}

	public static final WhileBlock _while(String test) {
		return new WhileBlock().test(test);
	}

	public static final DoWhileBlock _do_while(String test) {
		return new DoWhileBlock().test(test);
	}

	public static final ForBlock _for() {
		return new ForBlock();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static CodeBlock<CodeBlock<?>> _new(String body) {
		return new CodeBlock<CodeBlock>().code(body);
	}

}
