package codingeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Annotation implements Printable {

	private static final String VALUE = "value";
	
	private final String name;
	private final List<AnnotationParameter> parameters = new ArrayList<>();
	
	public Annotation(String name) {
		this.name = name;
	}

	public AnnotationParameter param(String name) {
		AnnotationParameter param = new AnnotationParameter(name);
		parameters.add(param);
		return param;
	}

	public AnnotationParameter value(String value) {
		AnnotationParameter param = new AnnotationParameter(VALUE).value(value);
		parameters.add(param);
		return param;
	}

	public AnnotationParameter stringValue(String value) {
		AnnotationParameter param = new AnnotationParameter(VALUE).stringValue(value);
		parameters.add(param);
		return param;
	}
	
	@Override
	public void print(CodePrinter code) {
		code.append("@").append(name);
		if (!parameters.isEmpty()) {
			code.openParenthesis();
			if (parameters.size() == 1 && parameters.get(0).name.equals(VALUE)) {
				code.append(parameters.get(0).value);
			} else {
				code.append(parameters.stream().map(param -> param.name + " = " + param.value).collect(Collectors.joining(", ")));
			}
			code.closeParenthesis();
		}
	}
	
	public static final class AnnotationParameter {
		
		private final String name;
		private String value;
		
		public AnnotationParameter(String name) {
			this.name = name;
		}
		
		public AnnotationParameter value(String value) {
			this.value = value;
			return this;
		}

		public AnnotationParameter stringValue(String value) {
			this.value = "\"" + value + "\"";
			return this;
		}
		
	}
	
}
