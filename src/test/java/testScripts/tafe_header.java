package testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.Header;

public class tafe_header extends BaseClass{
	
	Logger log = Logger.getLogger(this.getClass().getName());
	@Test(testName = "Header Menu functionality", description = "Validation of Header Menus")
	public void TC_header_Menu() throws InterruptedException 
	{
		
		Header header = new Header(driver);
		
		header.header_menu(driver);
	
	}
	
	@Test(testName = "Country PopUp functionality", description = "Validation of countries")
	public void TC_validating_regions() throws InterruptedException 
	{
		Header header = new Header(driver);
		
		header.countryPopup(driver);
		
		header.regions_countries(driver);
		
		header.closePopup();
		
		
	}
	
	@Test(testName = "Language switch functionality", description = "Validation of Locales")
	public void TC_validating_languages() throws InterruptedException 
	{
		Header header = new Header(driver);
		header.languages(driver);
	}
}
