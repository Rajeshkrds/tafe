package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.footer;

public class tafe_footer extends BaseClass {

	@Test
	public void Tc_validating_footer() throws InterruptedException, IOException 
	{
		footer footer = new footer(driver);
		
		footer.footer_menus(driver);
		
		footer.tafe_details();
		
		footer.copy_rights();
	}
	
	@Test
	public void TC_contact_form() throws IOException, InterruptedException 
	{
		 Properties testData = new Properties();
	        FileInputStream input = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\src\\test\\resources\\data.properties");
	        testData.load(input);

	        String Name = testData.getProperty("Name");
	        String mobile = testData.getProperty("mobile");
	        String email = testData.getProperty("email");
	        String state = testData.getProperty("state");
	        String category = testData.getProperty("category");
	        String country = testData.getProperty("country");

	        footer footer = new footer(driver);
	        footer.contact_form(Name, mobile, email, state, category, country,driver);
	}
}
