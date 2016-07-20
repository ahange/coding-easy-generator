package codingeasy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Type extends CodeGen<Type> {

	private String packageName;
	private EnumType enumType = EnumType.CLASS;
	private String superClass;
	private final Set<String> imports = new HashSet<String>();
	private final Set<String> interfaces = new HashSet<String>();
	private final List<Field> fields = new ArrayList<Field>();
	private final List<Method> methods = new ArrayList<Method>();

	public Type(String className) {
		super(className.contains(".") ? className.substring(className.indexOf(".") + 1) : className);

		if (className.contains(".")) {
			packageName(className.substring(0, className.lastIndexOf(".")));
		}
		
		addModifier(Modifier.PUBLIC);
	}

	public String getSuperClass() {
		return superClass;
	}
	
	public Type superClass(Class<?> type) {
		return superClass(type.getName());
	}
	
	public Type superClass(String type) {
		addImport(type);
		
		type = type.replace("java.lang.", "");
		if (type.contains(".")) {
			type = type.substring(type.lastIndexOf(".") + 1);
		}
		
		superClass = type;
		return this;
	}
	
	public Type addInterface(Class<?> type) {
		return addInterface(type.getName());
	}
	
	public Type addInterface(String type) {
		addImport(type);
		
		type = type.replace("java.lang.", "");
		if (type.contains(".")) {
			type = type.substring(type.lastIndexOf(".") + 1);
		}
		
		interfaces.add(type);
		return this;
	}
	
	public Field field(String name) {
		Field field = new Field(this, name);
		fields.add(field);
		return field;
	}

	public Method constructor() {
		Constructor method = new Constructor(this);
		methods.add(method);
		return method;
	}

	public Method method(String name) {
		Method method = new Method(this, name);
		methods.add(method);
		return method;
	}

	public EnumType getType() {
		return enumType;
	}

	public Type type(EnumType type) {
		enumType = type;
		return this;
	}

	public Type addImport(Class<?> classType) {
		return addImport(classType.getName());
	}

	public Type addImport(String classType) {
		if (!classType.contains(".") || classType.startsWith("java.lang")) {
			return this;
		}
		
		String simpleName = classType.substring(classType.lastIndexOf(".") + 1);
		String packName = classType.replace("." + simpleName, "");
		
		if (packName.equals(packageName)) {
			return this;
		}
		
		if (classType.contains("<")) {
			classType = classType.substring(0, classType.indexOf("<"));
		}
		imports.add(classType);
		return this;
	}

	public String getSimpleName() {
		return getName().replace(getPackageName(), "");
	}
	
	public String getPackageName() {
		return packageName;
	}

	public Type packageName(String packageName) {
		this.packageName = packageName;
		return this;
	}

	@Override
	public void print(CodePrinter code) {
		if (packageName != null) {
			code.line().append("package ").append(packageName).append(";");
			code.line();
		}

		if (!imports.isEmpty()) {
			for (String importType : imports) {
				code.line().append("import ").append(importType).end();
			}
			code.line();
		}

		List<Modifier> modifiers = getModifiers();
		for (Modifier modifier : modifiers) {
			code.line().append(modifier.getCode()).space();
		}

		code.append(enumType.getCode()).space().append(getName());
		
		if (getSuperClass() != null) {
			code.space().append("extends").space().append(getSuperClass());
		}

		if (!interfaces.isEmpty()) {
			code.space().append("implements").space();
			for (Iterator<String> it = interfaces.iterator(); it.hasNext();) {
				String type = it.next();
				code.append(type);
				if (it.hasNext()) {
					code.append(", ");
				}
			}
		}
		
		code.openBrace();

		for (Field field : fields) {
			field.print(code);
		}

		code.line().line();

		for (Method method : methods) {
			method.print(code);
		}

		code.line().closeBrace();
	}

}