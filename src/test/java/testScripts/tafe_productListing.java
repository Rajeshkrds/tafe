package testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.product_listing;

public class tafe_productListing extends BaseClass{

	@Test(testName="Product Listing", description="Validation of listing page")
	public void listingPage() throws IOException, InterruptedException 
	{
		product_listing listingpage = new product_listing(driver);
		listingpage.product_menu(driver);
		 Properties testData = new Properties();
	        FileInputStream input = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\src\\test\\resources\\data.properties");
	       testData.load(input);
		String drivetype = testData.getProperty("driveType");
		String category = testData.getProperty("category");
		listingpage.get_drive_type(drivetype);
		listingpage.get_category(drivetype, category);
		
		
	}
}
