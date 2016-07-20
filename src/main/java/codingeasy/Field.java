package codingeasy;

import java.util.Objects;

public class Field extends CodeGen<Field> {

	private String returnType;
	private final Type type;
	private String value;

	public Field(Type type, String name) {
		super(name);

		this.type = type;

		addModifier(Modifier.PRIVATE);
	}

	public Field generateGetterAndSetter() {
		generateGetter();
		generateSetter();
		return this;
	}
	
	public Field generateGetter() {
		type.method(getGetter(getName())).type(returnType).body("return " + getName() + ";");
		return this;
	}

	public Field generateSetter() {
		type.method(getSetter(getName())).type("void")
				.body("this." + getName() + " = " + getName() + ";").param(getName()).type(returnType);
		return this;
	}

	public String getReturnType() {
		return returnType;
	}

	public Field type(Class<?> type) {
		return type(type.getName());
	}

	public Field type(String type) {
		this.type.addImport(type);

		type = type.replace("java.lang.", "");
		if (type.contains(".")) {
			type = type.substring(type.lastIndexOf(".") + 1);
		}

		this.returnType = type;
		return this;
	}

	public Field value(String value) {
		this.value = value;
		return this;
	}

	public Field stringValue(String value) {
		if (!Objects.isNull(value)) {
			value = "\"" + value + "\"";
		}
		this.value = value;
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		code.line().ident();

		for (Modifier modifier : getModifiers()) {
			code.append(modifier.getCode()).space();
		}

		code.append(returnType).space().append(getName());
		if (value != null) {
			code.space().append("=").space().append(value);
		}
		code.end();
	}

	private String getGetter(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private String getSetter(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
}