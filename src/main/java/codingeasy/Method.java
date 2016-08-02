package codingeasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import codingeasy.Parameter.ParameterBuilder;
import codingeasy.Type.TypeBuilder;

public class Method extends CodeGen<Method> {

	private final String returnType;
	private final List<Parameter> parameters;
	private final CodeBlock body;
	
	Method(String name, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations,
			String returnType, List<Parameter> parameters, CodeBlock body) {
		super(name, javadoc, modifiers, annotations);
		this.returnType = returnType;
		this.parameters = Collections.unmodifiableList(parameters);
		this.body = body;
	}

	@Override
	public void print(CodePrinter code) {
		code.line();
		
		Javadoc javadoc = getJavadoc();
		if (!Objects.isNull(javadoc)) {
			javadoc.print(code);
		}
		
		List<Annotation> annotations = getAnnotations();
		annotations.forEach(annotation -> {
			annotation.print(code);
			code.line();
		});
		
		for (Modifier modifier : getModifiers()) {
			code.append(modifier.getCode()).space();
		}

		if (returnType != null) {
			code.append(returnType).space();
		}

		code.append(getName()).openParenthesis();

		for (int i = 0; i < parameters.size(); i++) {
			if (i > 0) {
				code.append(", ");
			}

			Parameter param = parameters.get(i);
			param.print(code);
		}

		code.closeParenthesis();
		
		if (isModifier(Modifier.ABSTRACT)) {
			code.end();
		} else {
			if (body != null) {
				code.append(body.build());
			} else {
				code.openBrace().closeBrace();
			}
		}
	}
	
	public static MethodBuilder builder(TypeBuilder typeBuilder, String name) {
		return new MethodBuilder(typeBuilder, name);
	}
	
	public static class MethodBuilder extends CodeGenBuilder<MethodBuilder> {

		private final TypeBuilder typeBuilder;
		private final List<Parameter> parameters = new ArrayList<>();
		private String returnType = "void";
		private CodeBlock body;

		MethodBuilder(TypeBuilder typeBuilder, String name) {
			super(name);
			this.typeBuilder = typeBuilder;
		}
		
		protected TypeBuilder getTypeBuilder() {
			return typeBuilder;
		}
		
		public List<Parameter> getParameters() {
			return parameters;
		}
		
		public String getReturnType() {
			return returnType;
		}
		
		public ParameterBuilder param(String name) {
			return Parameter.builder(this, name);
		}

		public MethodBuilder returnType(Class<?> type) {
			return returnType(type.getName());
		}

		public MethodBuilder returnType(String type) {
			if (Objects.isNull(type)) {
				return this;
			}
			
			this.typeBuilder.addImport(type);
			
			type = type.replace("java.lang.", "");
			if (type.contains(".")) {
				type = type.substring(type.lastIndexOf(".") + 1);
			}
			
			this.returnType = type;
			return this;
		}

		public CodeBlock getBody() {
			return body;
		}
		
		public MethodBuilder body(String body) {
			this.body = Blocks._new(body);
			return this;
		}
		
		public MethodBuilder body(CodeBlock body) {
			this.body = body;
			return this;
		}
		
		public MethodBuilder param(Parameter parameter) {
			parameters.add(parameter);
			return this;
		}

		public Method build() {
			Method method = new Method(getName(), getJavadoc(), getModifiers(), getAnnotations(), returnType, parameters, body);
			typeBuilder.method(method);
			return method;
		}
		
	}

}