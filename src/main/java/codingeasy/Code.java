package codingeasy;

import codingeasy.Type.TypeBuilder;

public class Code {

	public static TypeBuilder newType(String name) {
		return Type.builder(name);
	}

}
