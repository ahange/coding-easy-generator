package codingeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Method extends CodeGen<Method> {

	private final Type type;
	private final List<Parameter> parameters = new ArrayList<Parameter>();
	private String returnType;
	private CodeBlock body;

	public Method(Type type, String name) {
		super(name);

		this.type = type;
		
		addModifier(Modifier.PUBLIC);
	}

	public Parameter param(String name) {
		Parameter param = new Parameter(type, name);
		parameters.add(param);
		return param;
	}

	public Method type(Class<?> type) {
		return type(type.getName());
	}

	public Method type(String type) {
		if (Objects.isNull(type)) {
			return this;
		}
		
		this.type.addImport(type);
		
		type = type.replace("java.lang.", "");
		if (type.contains(".")) {
			type = type.substring(type.lastIndexOf(".") + 1);
		}
		
		this.returnType = type;
		return this;
	}

	public Method body(String body) {
		this.body = Blocks._new(body);
		return this;
	}
	
	public Method body(CodeBlock body) {
		this.body = body;
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		code.line().ident();
		
		List<Annotation> annotations = getAnnotations();
		annotations.forEach(annotation -> {
			annotation.print(code);
			code.line().ident();
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
				code.append(body.build().replace("\n", "\n\t"));
			} else {
				code.openBrace().ident().closeBrace();
			}
		}
	}

}