[![Build Status](https://travis-ci.org/dgouvea/coding-easy-generator.svg?branch=master)](https://travis-ci.org/dgouvea/coding-easy-generator)
# CodingEasy generator
Java Code Generator based on Java 8

## Example

```java
TypeBuilder builder = Code.newType("MyTest");

// add instance field
builder.field("instance", field -> {
	field.type("MyTest").addModifier(Modifier.PRIVATE).addModifier(Modifier.STATIC).addModifier(Modifier.FINAL).value("new MyTest()");
});

// add private constructor
builder.constructor(constructor -> {
	constructor.addModifier(Modifier.PRIVATE);
});

// add singleton get instance
builder.method("getInstance", method -> {
	method.addModifier(Modifier.PUBLIC).addModifier(Modifier.STATIC).returnType("MyTest").body("return instance;");
});

// add print method
builder.method("print", method -> {
	method.addModifier(Modifier.PUBLIC).body("System.out.println(\"Hello!\");");
});

// add method main
builder.method("main", method -> {
	method.addModifier(Modifier.PUBLIC).addModifier(Modifier.STATIC).param("args", param -> param.type("String[]").javadoc(javadoc -> javadoc.text("the args")));
	method.javadoc(javadoc -> javadoc.text("Main method"));
	method.body("MyTest myTest = MyTest.getInstance();\nmyTest.print();");
});

// build the class
Type myTest = builder.build();

// print the code		
CodePrinter printer = new CodePrinter();
myTest.print(printer);
System.out.println(printer.format());
```

Output result:

```java
public class MyTest {
	private static final MyTest instance = new MyTest();
	private MyTest() {
	}
	public static MyTest getInstance() {
		return instance;
	}
	public void print() {
		System.out.println("Hello!");
	}
	/**
	 * Main method
	 * @param args the args
	 */
	public static void main(String[] args) {
		MyTest myTest = MyTest.getInstance();
		myTest.print();
	}
}
```
