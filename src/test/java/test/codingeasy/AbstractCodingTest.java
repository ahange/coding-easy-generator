package test.codingeasy;

import java.util.logging.Logger;

import org.junit.Assert;

import codingeasy.CodePrinter;
import codingeasy.Printable;

public abstract class AbstractCodingTest {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	protected void expect(Printable printable, String expected) {
		expect(printable, expected, false);
	}
	
	protected void expect(Printable printable, String expected, boolean log) {
		String code = generate(printable);
		if (log) {
			log(expected, code);
		}
		Assert.assertEquals(expected, code);
	}
	
	protected String generate(Printable printable) {
		CodePrinter printer = new CodePrinter();
		printable.print(printer);
		return printer.format();
	}

	private void log(String expected, String code) {
		StringBuilder logging = new StringBuilder();
		logging.append("\n").append("Expected:");
		logging.append("\n").append(expected);
		
		logging.append("\n").append("Result:");
		logging.append("\n").append(code);
		
		logger.info(logging.toString());
	}
	
}
