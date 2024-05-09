package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.contactus;
import pomPages.enqiry_form;

public class tafe_cotactus extends BaseClass {

	Logger log = Logger.getLogger(this.getClass().getName());
	@Test(testName = "contact us functionality", description = "Validation of contact us form")
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
	        
	        form.contactus();
	        form.contact_form(Name, mobile, email, state, category, country,driver);
	}
	
	@Test(testName = "Contact us details", description = "Validation of tafe details")
	public void validating_details() 
	{
		contactus details = new contactus(driver);
		enqiry_form form = new enqiry_form(driver);
		  form.contactus();
		details.tafe_details();
	}
	@Test(testName = "FAQ section", description = "Validation of FAQ section")

	public void faq_section() throws InterruptedException 
	{
		contactus FAQ = new contactus(driver);
		enqiry_form form = new enqiry_form(driver);
		  form.contactus();
		FAQ.accordian(driver);
	}
}
