package BaseClass;

import org.testng.asserts.SoftAssert;

public class utilities {

	public void assertion(String actual, String expected) 
	{
		SoftAssert s = new SoftAssert();
		s.assertEquals(actual, expected);
	}
}
