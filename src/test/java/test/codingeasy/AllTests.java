package test.codingeasy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAnnotation.class, TestCodeBlock.class, TestConstructor.class, TestField.class, TestForBlock.class,
		TestIfBlock.class, TestJavadoc.class, TestMethod.class, TestParameter.class, TestType.class, TestWhileBlock.class,
		TestDoWhileBlock.class })
public class AllTests {

}
