package codingeasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import codingeasy.Constructor.ConstructorBuilder;
import codingeasy.Field.FieldBuilder;
import codingeasy.Method.MethodBuilder;

public class Type extends CodeGen<Type> {

	private final String packageName;
	private final EnumType enumType;
	private final String superClass;
	private final Set<String> imports;
	private final Set<String> interfaces;
	private final List<Field> fields;
	private final List<Method> methods;
	
	Type(String name, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations,
			String packageName, EnumType enumType, String superClass, Set<String> imports, Set<String> interfaces,
			List<Field> fields, List<Method> methods) {
		super(name, javadoc, modifiers, annotations);
		this.packageName = packageName;
		this.enumType = enumType;
		this.superClass = superClass;
		this.imports = Collections.unmodifiableSet(imports);
		this.interfaces = Collections.unmodifiableSet(interfaces);
		this.fields = Collections.unmodifiableList(fields);
		this.methods = Collections.unmodifiableList(methods);
	}

	public final String getSuperClass() {
		return superClass;
	}
	
	public final EnumType getType() {
		return enumType;
	}

	public final EnumType getEnumType() {
		return enumType;
	}

	public final List<Field> getFields() {
		return fields;
	}
	
	public final Set<String> getImports() {
		return imports;
	}
	
	public final Set<String> getInterfaces() {
		return interfaces;
	}
	
	public final List<Method> getMethods() {
		return methods;
	}
	
	public final String getSimpleName() {
		return getName().replace(getPackageName(), "");
	}
	
	public final String getPackageName() {
		return packageName;
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

		CodePrinter inner = new CodePrinter();
		
		for (Field field : fields) {
			field.print(inner);
		}

		for (Method method : methods) {
			method.print(inner);
		}

		if (!inner.isEmpty()) {
			String innerCode = inner.format().replace("\n", "\n\t");
			if (getType() == EnumType.INTERFACE) {
				innerCode = innerCode.replace("public abstract ", "");
			}
			code.ident().append(innerCode);
		}
		
		code.line().closeBrace();
	}
	
	public static TypeBuilder builder(String name) {
		return new TypeBuilder(name);
	}

	public static class TypeBuilder extends CodeGen.CodeGenBuilder<TypeBuilder> {
		
		private String packageName;
		private EnumType enumType = EnumType.CLASS;
		private String superClass;
		private Set<String> imports = new HashSet<>();
		private Set<String> interfaces = new HashSet<>();
		private List<Field> fields = new ArrayList<>();
		private List<Method> methods = new ArrayList<>();
		
		TypeBuilder(String name) {
			super(name);
			
			if (name.contains(".")) {
				packageName(name.substring(0, name.lastIndexOf(".") - 1));
			}
			
			addModifier(Modifier.PUBLIC);
		}
		
		public TypeBuilder superClass(Class<?> type) {
			return superClass(type.getName());
		}
		
		public TypeBuilder superClass(String type) {
			addImport(type);
			
			type = type.replace("java.lang.", "");
			if (type.contains(".")) {
				type = type.substring(type.lastIndexOf(".") + 1);
			}
			
			superClass = type;
			return this;
		}
		
		public TypeBuilder addInterface(Class<?> type) {
			return addInterface(type.getName());
		}
		
		public TypeBuilder addInterface(String type) {
			addImport(type);
			
			type = type.replace("java.lang.", "");
			if (type.contains(".")) {
				type = type.substring(type.lastIndexOf(".") + 1);
			}
			
			interfaces.add(type);
			return this;
		}
		
		public TypeBuilder field(Field field) {
			fields.add(field);
			return this;
		}
		
		public FieldBuilder field(String name) {
			return Field.builder(this, name);
		}

		public ConstructorBuilder constructor() {
			return Constructor.builder(this);
		}

		public TypeBuilder method(Method method) {
			methods.add(method);
			return this;
		}
		
		public MethodBuilder method(String name) {
			return Method.builder(this, name);
		}

		public TypeBuilder type(EnumType type) {
			enumType = type;
			return this;
		}

		public TypeBuilder addImport(Class<?> classType) {
			return addImport(classType.getName());
		}

		public TypeBuilder addImport(String classType) {
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

		public TypeBuilder packageName(String packageName) {
			this.packageName = packageName;
			return this;
		}
		
		public Type build() {
			return new Type(getName(), getJavadoc(), getModifiers(), getAnnotations(), packageName, enumType, superClass, imports, interfaces, fields, methods);
		}
		
	}
	
}