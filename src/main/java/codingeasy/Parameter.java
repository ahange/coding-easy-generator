package codingeasy;

import java.util.List;

import codingeasy.Method.MethodBuilder;

public class Parameter extends Field {

	public Parameter(String name, String type, String value, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations) {
		super(name, type, value, javadoc, modifiers, annotations);
	}

	@Override
	public void print(CodePrinter code) {
		List<Annotation> annotations = getAnnotations();
		annotations.forEach(annotation -> {
			annotation.print(code);
			code.space();
		});
		code.append(getType()).space().append(getName());
	}

	public static ParameterBuilder builder(MethodBuilder methodBuilder, String name) {
		return new ParameterBuilder(methodBuilder, name);
	}
	
	public static class ParameterBuilder extends CodeGenBuilder<ParameterBuilder> {
		
		private final MethodBuilder methodBuilder;
		private String type;
		
		ParameterBuilder(MethodBuilder methodBuilder, String name) {
			super(name);
			this.methodBuilder = methodBuilder;
		}
		
		public ParameterBuilder type(Class<?> type) {
			methodBuilder.getTypeBuilder().addImport(type);
			return type(type.getSimpleName());
		}
		
		public ParameterBuilder type(String type) {
			this.type = type;
			return this;
		}
		
		public Parameter build() {
			Parameter parameter = new Parameter(getName(), type, null, getJavadoc(), getModifiers(), getAnnotations());
			methodBuilder.param(parameter);
			return parameter;
		}
		
	}
	
}