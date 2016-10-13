package codingeasy;

import java.util.List;
import java.util.Objects;

import codingeasy.Type.TypeBuilder;

public class Field extends CodeGen<Field> {

	private final String type;
	private final String value;

	protected Field(String name, String type, String value, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations) {
		super(name, javadoc, modifiers, annotations);
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public void print(CodePrinter code) {
		code.line();

		Javadoc javadoc = getJavadoc();
		if (!Objects.isNull(javadoc)) {
			javadoc.print(code);
		}
	
		for (Modifier modifier : getModifiers()) {
			code.append(modifier.getCode()).space();
		}

		code.append(type).space().append(getName());
		if (value != null) {
			code.space().append("=").space().append(value);
		}
		code.end();
	}

	public static FieldBuilder builder(TypeBuilder type, String name) {
		return new FieldBuilder(type, name);
	}
	
	public static class FieldBuilder extends CodeGen.CodeGenBuilder<FieldBuilder> {
		
		private final TypeBuilder typeBuilder;
		private String type;
		private String value;
		
		private FieldBuilder(TypeBuilder typeBuilder, String name) {
			super(name);
			this.typeBuilder = typeBuilder;
		}
		
		public FieldBuilder type(Class<?> type) {
			typeBuilder.addImport(type);
			return type(type.getSimpleName());
		}
		
		public FieldBuilder type(String type) {
			this.type = type;
			return this;
		}
		
		public FieldBuilder value(String value) {
			this.value = value;
			return this;
		}

		public FieldBuilder stringValue(String value) {
			this.value = "\"" + value + "\"";
			return this;
		}
		
		public FieldBuilder getter() {
			String methodName = "get" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
			typeBuilder.method(methodName, method -> {
				method.returnType(type).addModifier(Modifier.PUBLIC).body("return " + getName() + ";");
			});
			return this;
		}

		public FieldBuilder setter() {
			String methodName = "set" + getName().substring(0, 1).toUpperCase() + getName().substring(1);
			typeBuilder.method(methodName, method -> {
				method.returnType("void").addModifier(Modifier.PUBLIC).body("this." + getName() + " = " + getName() + ";");
				method.param(getName(), param -> {
					param.type(type);
				});
			});
			return this;
		}
		
		public Field build() {
			Field field = new Field(getName(), type, value, getJavadoc(), getModifiers(), getAnnotations());
			typeBuilder.field(field);
			return field;
		}

	}
	
}