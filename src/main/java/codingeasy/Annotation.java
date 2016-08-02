package codingeasy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Annotation implements Printable {

	private static final String VALUE = "value";
	
	private final String name;
	private final Map<String, String> parameters;
	
	Annotation(String name, Map<String, String> parameters) {
		this.name = name;
		this.parameters = Collections.unmodifiableMap(parameters);
	}

	@Override
	public void print(CodePrinter code) {
		code.append("@").append(name);
		if (!parameters.isEmpty()) {
			code.openParenthesis();
			if (parameters.size() == 1 && parameters.containsKey(VALUE)) {
				code.append(parameters.get(VALUE));
			} else {
				code.append(parameters.entrySet().stream().map(e -> e.getKey() + " = " + e.getValue()).collect(Collectors.joining(", ")));
			}
			code.closeParenthesis();
		}
	}
	
	public static AnnotationBuilder builder(String name) {
		return new AnnotationBuilder(name);
	}
	
	public static class AnnotationBuilder {
		
		private final String name;
		private Map<String, String> parameters = new LinkedHashMap<>();
		
		AnnotationBuilder(String name) {
			this.name = name;
		}
		
		public AnnotationBuilder param(String name, String value) {
			parameters.put(name, value);
			return this;
		}

		public AnnotationBuilder stringParam(String name, String value) {
			parameters.put(name, "\"" + value + "\"");
			return this;
		}

		public AnnotationBuilder value(String value) {
			parameters.put(VALUE, value);
			return this;
		}

		public AnnotationBuilder stringValue(String value) {
			parameters.put(VALUE, "\"" + value + "\"");
			return this;
		}
		
		public Annotation build() {
			return new Annotation(name, parameters);
		}
		
	}
	
}
