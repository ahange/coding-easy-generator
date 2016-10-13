package codingeasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import codingeasy.Annotation.AnnotationBuilder;
import codingeasy.Javadoc.JavadocBuilder;

public abstract class CodeGen<R extends CodeGen<?>> implements Printable {

	private final String name;
	private final Javadoc javadoc;
	private final List<Modifier> modifiers;
	private final List<Annotation> annotations;

	public CodeGen(String name, Javadoc javadoc, List<Modifier> modifiers, List<Annotation> annotations) {
		this.name = name;
		this.javadoc = javadoc;
		this.modifiers = Collections.unmodifiableList(modifiers);
		this.annotations = Collections.unmodifiableList(annotations);
	}

	public final String getName() {
		return name;
	}
	
	public final Javadoc getJavadoc() {
		return javadoc;
	}

	public final boolean isModifier(Modifier modifier) {
		return modifiers.contains(modifier);
	}
	
	public final List<Modifier> getModifiers() {
		return modifiers;
	}

	public final List<Annotation> getAnnotations() {
		return annotations;
	}
	
	public static abstract class CodeGenBuilder<T extends CodeGenBuilder<T>> {
		
		private final String name;
		private Javadoc javadoc;
		private List<Modifier> modifiers = new ArrayList<>();
		private List<Annotation> annotations = new ArrayList<>();
		
		CodeGenBuilder(String name) {
			String[] parts = name.split("\\.");
			this.name = parts[parts.length - 1];
		}
		
		protected final String getName() {
			return name;
		}
		
		public Javadoc getJavadoc() {
			return javadoc;
		}
		
		@SuppressWarnings("unchecked")
		public T javadoc(Consumer<JavadocBuilder> consumer) {
			JavadocBuilder javadocBuilder = Javadoc.builder();
			consumer.accept(javadocBuilder);
			this.javadoc = javadocBuilder.build();
			return (T) this;
		}

		public final List<Modifier> getModifiers() {
			return Collections.unmodifiableList(modifiers);
		}
		
		@SuppressWarnings("unchecked")
		public T addModifier(Modifier modifier) {
			modifiers.add(modifier);
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T removeModifier(Modifier modifier) {
			modifiers.remove(modifier);
			return (T) this;
		}

		public final List<Annotation> getAnnotations() {
			return Collections.unmodifiableList(annotations);
		}
		
		@SuppressWarnings("unchecked")
		public T addAnnotation(Annotation annotation) {
			annotations.add(annotation);
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T addAnnotation(String name, Consumer<AnnotationBuilder> consumer) {
			AnnotationBuilder annotationBuilder = Annotation.builder(name);
			consumer.accept(annotationBuilder);
			Annotation annotation = annotationBuilder.build();
			annotations.add(annotation);
			return (T) this;
		}
		
		@SuppressWarnings("unchecked")
		public T removeAnnotation(Annotation annotation) {
			annotations.remove(annotation);
			return (T) this;
		}
		
	}
	
}
