package BaseClass;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class utilities {

	public void assertion(String actual, String expected) 
	{
		SoftAssert s = new SoftAssert();
		s.assertEquals(actual, expected);
	}
	
	public void back_navigation(WebDriver driver) 
	{
		driver.navigate().back();
	}
	
	
}
