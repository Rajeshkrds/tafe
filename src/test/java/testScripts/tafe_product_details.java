package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.product_details;

public class tafe_product_details extends BaseClass {

	Logger log = Logger.getLogger(this.getClass().getName());

	@Test(testName ="Product details page", description ="Validating product details page")
	public void details_page() throws IOException, InterruptedException 
	{
		product_details details = new product_details(driver);
		 Properties testData = new Properties();
	        FileInputStream input = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\src\\test\\resources\\data.properties");
	       testData.load(input);
	       String modelname= testData.getProperty("modelname");
	       String driveType = testData.getProperty("driveType");
		details.get_details_page(driver, modelname, driveType);
	}
	
	@Test(testName ="Product details page", description ="Validating product details page")

	public void detail_page() throws InterruptedException 
	{
		product_details details = new product_details(driver);
		details.get_products(driver);
		details.get_spec();
		details.compare_model();
		details.contact_dealer(driver);
	}
}
