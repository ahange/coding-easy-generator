package test.codingeasy;

import org.junit.Test;

import codingeasy.Javadoc;

public class TestJavadoc extends AbstractCodingTest {

	@Test
	public void testSimple() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n */\n");
	}

	@Test
	public void testThrows() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").exception("Exception", "testing exception javadoc").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @throws Exception testing exception javadoc\n */\n");
	}

	@Test
	public void testSince() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").since("1.5").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @since 1.5\n */\n");
	}

	@Test
	public void testDoubleSince() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").since("1.5").since("1.8").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @since 1.8\n */\n");
	}

	@Test
	public void testReturnType() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").returnDoc("the index").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @return the index\n */\n");
	}

	@Test
	public void testParamsThrows() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").param("name", "the name of person").exception("Exception", "testing exception javadoc").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @param name the name of person\n * @throws Exception testing exception javadoc\n */\n");
	}

	@Test
	public void testParamsThrowsInverted() {
		Javadoc javadoc = Javadoc.builder().text("Simple javadoc").exception("Exception", "testing exception javadoc").param("name", "the name of person").build();
		
		expect(javadoc, "/**\n * Simple javadoc\n * @param name the name of person\n * @throws Exception testing exception javadoc\n */\n");
	}
	
}
