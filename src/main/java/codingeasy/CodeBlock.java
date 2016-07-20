package codingeasy;

public class CodeBlock implements Printable {

	protected final StringBuilder code = new StringBuilder();

	public CodeBlock code(String code) {
		if (this.code.length() > 0) {
			this.code.append("\n");
		}
		this.code.append(code);
		return this;
	}

	public CodeBlock code(CodeBlock code) {
		CodePrinter printer = new CodePrinter();
		code.print(printer);
		code(printer.format());
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		code.openBrace();
		code.ident().append(this.code.toString().replace("\n", "\n\t"));
		code.closeBrace();
	}
	
	public String build() {
		CodePrinter printer = new CodePrinter();
		print(printer);
		return printer.format();
	}

}