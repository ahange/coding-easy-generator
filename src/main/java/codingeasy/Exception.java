package codingeasy;

public class Exception implements Printable {

	private String name;
	private String javadoc;
	
	public Exception() {

	}
	
	public String name() {
		return name;
	}
	
	public Exception name(String name) {
		this.name = name;
		return this;
	}
	
	public String javadoc() {
		return javadoc;
	}
	
	public Exception javadoc(String javadoc) {
		this.javadoc = javadoc;
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		code.append(name);
	}
	
}
