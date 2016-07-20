package codingeasy;

public enum Modifier {

	DEFAULT(""),
	PUBLIC("public"),
	PROTECTED("protected"),
	PRIVATE("private"),
	STATIC("static"),
	FINAL("final"),
	ABSTRACT("abstract");

	private String code;

	private Modifier(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}