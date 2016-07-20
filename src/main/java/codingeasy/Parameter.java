package codingeasy;

import java.util.List;

public class Parameter extends Field {

	public Parameter(Type type, String name) {
		super(type, name);
		removeModifier(Modifier.PRIVATE);
	}

	@Override
	public Field value(String value) {
		throw new IllegalArgumentException("Parameters has no value");
	}
	
	@Override
	public void print(CodePrinter code) {
		List<Annotation> annotations = getAnnotations();
		annotations.forEach(annotation -> {
			annotation.print(code);
			code.space();
		});
		code.append(getReturnType()).space().append(getName());
	}

}