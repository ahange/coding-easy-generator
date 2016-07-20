package codingeasy;

import java.util.ArrayList;
import java.util.List;

public abstract class CodeGen<R extends CodeGen<?>> implements Printable {

	private final String name;
	private final List<Modifier> modifiers = new ArrayList<>();
	private final List<Annotation> annotations = new ArrayList<>();

	public CodeGen(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isModifier(Modifier modifier) {
		return modifiers.contains(modifier);
	}
	
	public List<Modifier> getModifiers() {
		return modifiers;
	}

	@SuppressWarnings("unchecked")
	public R addModifier(Modifier modifier) {
		modifiers.add(modifier);
		return (R) this;
	}

	@SuppressWarnings("unchecked")
	public R removeModifier(Modifier modifier) {
		modifiers.remove(modifier);
		return (R) this;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}
	
	public Annotation addAnnotation(String name) {
		Annotation annotation = new Annotation(name);
		this.annotations.add(annotation);
		return annotation;
	}

	public Annotation addAnnotation(Class<? extends java.lang.annotation.Annotation> type) {
		return addAnnotation(type.getName());
	}
	
}
