package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.enqiry_form;

public class tafe_enqiryForm extends BaseClass{
	
	Logger log = Logger.getLogger(this.getClass().getName());

	@Test(testName = "Enquiry form functionality", description = "Validation of enquiry form")
	public void validating_enquiry_form() throws IOException, InterruptedException 
	{
		enqiry_form form = new enqiry_form(driver);
		 Properties testData = new Properties();
	        FileInputStream input = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\src\\test\\resources\\data.properties");
	       testData.load(input);

	        String Name = testData.getProperty("Name");
	        String mobile = testData.getProperty("mobile");
	        String email = testData.getProperty("email");
	        String state = testData.getProperty("state");
	        String category = testData.getProperty("category");
	        String country = testData.getProperty("country");

	        form.enqirenow();
	        form.contact_form(Name, mobile, email, state, category, country,driver);
	}
	
	public void validating_details() 
	{
		
	}

}
