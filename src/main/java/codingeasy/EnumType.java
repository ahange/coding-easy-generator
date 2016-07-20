package codingeasy;

public enum EnumType {

	CLASS("class"),
	INTERFACE("interface"),
	ANNOTATION("@interface"),
	ENUM("enum");

	private String code;

	EnumType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}