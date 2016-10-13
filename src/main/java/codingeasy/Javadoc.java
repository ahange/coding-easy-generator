package codingeasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Javadoc implements Printable {

	private final String text;
	private final List<JavadocAuthor> authors;
	private final List<JavadocParam> parameters;
	private final List<JavadocException> exceptions;
	private final JavadocSince since;
	private final JavadocReturn returnType;
	
	public Javadoc(String text, List<JavadocAuthor> authors, List<JavadocParam> parameters, List<JavadocException> exceptions, JavadocSince since, JavadocReturn returnType) {
		this.text = text;
		this.authors = authors;
		this.parameters = parameters;
		this.exceptions = exceptions;
		this.since = since;
		this.returnType = returnType;
	}

	public String getText() {
		return text;
	}
	
	public List<JavadocAuthor> getAuthors() {
		return authors;
	}
	
	public List<JavadocParam> getParameters() {
		return parameters;
	}
	
	public List<JavadocException> getExceptions() {
		return exceptions;
	}
	
	public JavadocSince getSince() {
		return since;
	}
	
	public JavadocReturn getReturnType() {
		return returnType;
	}
	
	@Override
	public void print(CodePrinter code) {
		StringBuilder javadoc = new StringBuilder();
		if (text != null) {
			javadoc.append(text);
		}
		authors.forEach(author -> javadoc.append("\n").append(author.toString()));
		if (since != null) {
			javadoc.append("\n").append(since);
		}
		parameters.forEach(param -> javadoc.append("\n").append(param.toString()));
		if (returnType != null) {
			javadoc.append("\n").append(returnType);
		}
		exceptions.forEach(exception -> javadoc.append("\n").append(exception.toString()));
		
		code.append("/**");
		code.line().append(" * ");
		code.append(javadoc.toString().replace("\n", "\n * "));
		code.line().append(" */").line();
	}
	
	public static JavadocBuilder builder() {
		return new JavadocBuilder();
	}
	
	public static final class JavadocSince extends JavadocText {
		
		public JavadocSince(String since) {
			super(since);
		}
		
		public String toString() {
			return "@since " + getText();
		}
		
	}

	public static final class JavadocReturn extends JavadocText {
		
		public JavadocReturn(String since) {
			super(since);
		}
		
		public String toString() {
			return "@return " + getText();
		}
		
	}

	public static final class JavadocAuthor extends JavadocText {
		
		public JavadocAuthor(String name) {
			super(name);
		}

		public String toString() {
			return "@author " + getText();
		}
		
	}
	
	public static class JavadocParam extends JavadocText {
		
		private final String name;
		
		public JavadocParam(String name, String text) {
			super(text);
			
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return "@param " + name + " " + getText();
		}
		
	}
	
	public static final class JavadocException extends JavadocParam {

		public JavadocException(String name, String text) {
			super(name, text);
		}

		@Override
		public String toString() {
			return "@throws " + getName() + " " + getText();
		}
		
	}
	
	public static abstract class JavadocText {
		
		private final String text;
		
		public JavadocText(String text) {
			this.text = text;
		}
		
		public String getText() {
			return text;
		}
		
	}
	
	public static class JavadocBuilder {
	
		private final StringBuilder text = new StringBuilder();
		private final List<JavadocAuthor> authors = new ArrayList<>();
		private final List<JavadocParam> parameters = new ArrayList<>();
		private final List<JavadocException> exceptions = new ArrayList<>();
		private JavadocSince since;
		private JavadocReturn returnType;
		
		private JavadocBuilder() {
			
		}
		
		public JavadocBuilder clone(Javadoc javadoc) {
			text(javadoc.getText());
			javadoc.getAuthors().forEach(author -> author(author.getText()));
			javadoc.getParameters().forEach(param -> param(param.getName(), param.getText()));
			javadoc.getExceptions().forEach(exception -> exception(exception.getName(), exception.getText()));
			if (Objects.nonNull(javadoc.getSince())) {
				since(javadoc.getSince().getText());
			}
			if (Objects.nonNull(javadoc.getReturnType())) {
				returnDoc(javadoc.getReturnType().getText());
			}
			return this;
		}
		
		public JavadocBuilder text(String text) {
			this.text.append(text);
			return this;
		}
		
		public JavadocBuilder author(String author) {
			this.authors.add(new JavadocAuthor(author));
			return this;
		}
	
		public JavadocBuilder since(String since) {
			this.since = new JavadocSince(since);
			return this;
		}
	
		public JavadocBuilder param(String name, String text) {
			this.parameters.add(new JavadocParam(name, text));
			return this;
		}
	
		public JavadocBuilder exception(String name, String text) {
			this.exceptions.add(new JavadocException(name, text));
			return this;
		}
	
		public JavadocBuilder returnDoc(String text) {
			this.returnType = new JavadocReturn(text);
			return this;
		}
		
		public Javadoc build() {
			if (text.toString().trim().isEmpty()) {
				return null;
			}
			return new Javadoc(text.toString(), authors, parameters, exceptions, since, returnType);
		}

	}

}
