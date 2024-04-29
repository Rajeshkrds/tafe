package testScripts;

import java.io.IOException;

import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pomPages.home_page;

public class tafe_home extends BaseClass{
	
	
	@Test(testName = "social popup functionality", description = "Validation of social Menus")
	public void TC_validating_social_links() throws InterruptedException, IOException 
	{
		home_page home = new home_page(driver);
		home.social_popup(driver);
	}
	@Test(testName = "Testing Home Page CTA's", description = "Validation of CTA in home page")
	
	public void Tc_validating_homePage_CTA() throws InterruptedException 
	{
		home_page home = new home_page(driver);
//		home.banner_CTA();
		home.bourcher(driver);
		home.events_section(driver);
		home.find_dealers(driver);
	}
	
	@Test(testName = "Witness Tafe functionality", description = "Validation of witness tafe section")
	public void witness_Tafe() 
	{
		home_page home = new home_page(driver);
		home.witness_Tafe(driver);
	}
}
