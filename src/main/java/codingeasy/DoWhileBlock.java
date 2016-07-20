package codingeasy;

public class DoWhileBlock extends TestBlock<DoWhileBlock> {

	public DoWhileBlock() {
		super("while");
	}

	@Override
	public void print(CodePrinter code) {
		code.append("do");
		code.openBrace();
		code.ident().append(this.code.toString());
		code.closeBrace();
		code.space().append(keyword).space().openParenthesis().append(test).closeParenthesis().end();
	}

}