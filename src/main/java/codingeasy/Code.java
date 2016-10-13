package codingeasy;

import codingeasy.Type.TypeBuilder;

public final class Code {
	
	public static TypeBuilder newType(String name) {
		return Type.builder(name);
	}

}
