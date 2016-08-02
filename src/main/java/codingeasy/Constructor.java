package codingeasy;

import java.util.List;

import codingeasy.Type.TypeBuilder;

public class Constructor extends Method {

	Constructor(String name, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations, List<Parameter> parameters, CodeBlock body) {
		super(name, javadoc, modifiers, annotations, null, parameters, body);
	}

	@Override
	public void print(CodePrinter code) {
		super.print(code);
	}
	
	public static ConstructorBuilder builder(TypeBuilder typeBuilder) {
		return new ConstructorBuilder(typeBuilder, typeBuilder.getName());
	}
	
	public static class ConstructorBuilder extends MethodBuilder {

		ConstructorBuilder(TypeBuilder typeBuilder, String name) {
			super(typeBuilder, name);
			addModifier(Modifier.PUBLIC);
		}

		@Override
		public MethodBuilder returnType(String type) {
			throw new IllegalArgumentException("Constructor has no return type");
		}
		
		@Override
		public Constructor build() {
			Constructor method = new Constructor(getTypeBuilder().getName(), getJavadoc(), getModifiers(), getAnnotations(), getParameters(), getBody());
			getTypeBuilder().method(method);
			return method;
		}
		
	}

}
