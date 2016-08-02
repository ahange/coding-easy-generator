package codingeasy;

public class Javadoc implements Printable {

	private final String text;

	private Javadoc(String text) {
		this.text = text;
	}
	
	public final String getText() {
		return text.toString();
	}
	
	@Override
	public void print(CodePrinter code) {
		code.append("/**");
		code.line().append(" * ");
		code.append(text.toString().replace("\n", "\n * "));
		code.line().append(" */").line();
	}
	
	public static JavadocBuilder builder() {
		return new JavadocBuilder();
	}
	
	public static class JavadocBuilder {
	
		private final StringBuilder text = new StringBuilder();
		private final StringBuilder authors = new StringBuilder();
		private final StringBuilder parameters = new StringBuilder();
		private final StringBuilder exceptions = new StringBuilder();
		private String since = "";
		private String returnType = "";
		
		private JavadocBuilder() {
			
		}
		
		public JavadocBuilder text(String text) {
			this.text.append(text);
			return this;
		}
		
		public JavadocBuilder author(String author) {
			this.authors.append("\n@author ").append(author);
			return this;
		}
	
		public JavadocBuilder since(String since) {
			this.since = "\n@since " + since;
			return this;
		}
	
		public JavadocBuilder param(String name, String javadoc) {
			this.parameters.append("\n@param ").append(name).append(" ").append(javadoc);
			return this;
		}
	
		public JavadocBuilder exception(String name, String javadoc) {
			this.exceptions.append("\n@throws ").append(name).append(" ").append(javadoc);
			return this;
		}
	
		public JavadocBuilder returnDoc(String doc) {
			this.returnType = "\n@return " + doc;
			return this;
		}
		
		public Javadoc build() {
			if (text.toString().trim().isEmpty()) {
				return null;
			}
			
			StringBuilder javadoc = new StringBuilder();
			javadoc.append(text);
			javadoc.append(authors);
			javadoc.append(since);
			javadoc.append(parameters);
			javadoc.append(returnType);
			javadoc.append(exceptions);
			
			return new Javadoc(javadoc.toString());
		}

	}

}
