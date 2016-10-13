package codingeasy;

public class CodeBlock<T> implements Printable {

	protected final StringBuilder code = new StringBuilder();

	protected CodeBlock() {
		
	}
	
	protected String getCode() {
		return code.toString();
	}
	
	@SuppressWarnings("unchecked")
	public T code(String code) {
		if (this.code.length() > 0) {
			this.code.append("\n");
		}
		this.code.append(code);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T code(CodeBlock<?> code) {
		CodePrinter printer = new CodePrinter();
		code.print(printer);
		code(printer.format());
		return (T) this;
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