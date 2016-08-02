package codingeasy;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodePrinter {

	private final StringBuilder code = new StringBuilder();

	public boolean isEmpty() {
		return code.toString().trim().isEmpty();
	}
	
	public CodePrinter line() {
		code.append("\n");
		return this;
	}

	public CodePrinter space() {
		code.append(" ");
		return this;
	}

	public CodePrinter ident() {
		return ident(1);
	}

	public CodePrinter ident(int times) {
		code.append(IntStream.range(0, times).mapToObj(i -> "\t").collect(Collectors.joining()));
		return this;
	}

	public CodePrinter append(String text) {
		code.append(text);
		return this;
	}

	public CodePrinter end() {
		code.append(";");
		return this;
	}

	public CodePrinter openParenthesis() {
		code.append("(");
		return this;
	}

	public CodePrinter closeParenthesis() {
		code.append(")");
		return this;
	}

	public CodePrinter openBrace() {
		code.append(" {");
		return this;
	}

	public CodePrinter closeBrace() {
		code.append("}");
		return this;
	}

	public String format() {
		String formattedCode = code.toString();

		formattedCode = formattedCode.replace("{", "{\n");
		formattedCode = formattedCode.replaceAll("(\\s*)}", "\n$1}");
		formattedCode = formattedCode.replaceAll("\n\\s+\\n", "\n");
		formattedCode = formattedCode.replaceAll("\n+", "\n");

		return formattedCode;
	}

}