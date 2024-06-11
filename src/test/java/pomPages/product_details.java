package pomPages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import BaseClass.utilities;

public class product_details {

	utilities u = new utilities(); 
	Logger log = Logger.getLogger(getClass().getName());
	@FindBy(xpath="//a[text()='Products']")
	private WebElement product_menu;
	
	//@FindBy(xpath="//div[contains(@class,\"relative bg-gray-200\")]")
	@FindBy(xpath="//div[contains(@class,\"relative bg-gray-200\")]//h4")
	private List<WebElement> models;
	
	@FindBy(xpath="//div[contains(@class,\"relative bg-gray-200\")]/ancestor::div//a")
	private List<WebElement> driveCTA;
	
	@FindBy(xpath="//div[contains(@class,\"grid gap-y-10\")]//p")
	private List<WebElement> spec;
	
	@FindBy(xpath="//div[contains(@class,\"grid gap-y-10\")]//h5")
	private List<WebElement> values;
	
	@FindBy(xpath="(//div[@class=\"mb-4\"])[2]")
	private WebElement name;
	
	@FindBy(xpath="(//select[contains(@class,\"appearance-none\")])[1]")
	private WebElement compare1;
	
	@FindBy(xpath="(//select[contains(@class,\"appearance-none\")])[2]")
	private WebElement compare2;
	
	@FindBy(xpath="//a[text()='Contact Dealer']")
	private WebElement contact;
	
	public product_details(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void get_details_page(WebDriver driver, String modelName, String driveType) throws IOException, InterruptedException 
	{
		log.info("Clicking on product menu from header");
		product_menu.click();
		String url =driver.getCurrentUrl();
		@SuppressWarnings("deprecation")
		URL newurl =new URL(url);
		HttpsURLConnection connect = (HttpsURLConnection) newurl.openConnection();
		connect.connect();
		 if (connect.getResponseCode() >= 400) {
		        log.info("Page not found - (Response Code : " + connect.getResponseCode() + ")");
		        driver.close();
		    } 
		 else 
		    {
		        log.info("Listing Page is displayed");
		        SoftAssert a = new SoftAssert();
		        a.assertEquals(driver.getTitle(), "TAFE | Sub 100 HP Range | TAFE TRACTOR Models");
		        a.assertAll();
	        	log.info("Total models in this page "+models.size());

		        for(int i=1;i<models.size();i++) 
		        {
		        	for(WebElement CTA: driveCTA) 
		        	{

		        		if(modelName.equals(models.get(i).getText()) && driveType.equals(CTA.getText())) 
			        	{
		        			log.info(models.get(i).getText());
				        	log.info(CTA.getText());
				        	CTA.click();
					        log.info(driver.getCurrentUrl());
			        	}
			        		
			        	}
		        	} 
		        	
		        }
		       
		    }
	
	public void get_products(WebDriver driver) throws InterruptedException 
	{
		product_menu.click();
		Random ran = new Random();
		int num =ran.nextInt(driveCTA.size());
		log.info(num);
		Thread.sleep(5000);
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driveCTA.get(num));
	//	((JavascriptExecutor) driver).executeScript("arguments[0].click();", driveCTA.get(num));
		Actions a = new Actions(driver);
		a.moveToElement(driveCTA.get(num)).build().perform();
		driveCTA.get(num).click();
		Thread.sleep(5000);
		log.info(driver.getCurrentUrl());
		
	}
	
	public void get_spec() 
	{
		log.info("Specfication for "+name.getText());
		for(int i=1;i<spec.size();i++) 
		{
			for(int j=1;j<values.size();j++) 
			{
				if(i==j	)
					log.info(spec.get(i).getText()	+" - "+values.get(j).getText());
			}
		}
	}
	
	
	public void compare_model() 
	{
		
		
		Select compare_dd = new Select(compare1);
		compare_dd.selectByIndex(1);
		
		Select compare_dd2 = new Select(compare2);
		compare_dd2.selectByIndex(1);
		
	}
	
	public void contact_dealer(WebDriver driver) throws InterruptedException 
	{
		contact.click();
		Thread.sleep(5000);
		u.assertion("Contact TAFE TRACTORS | Tractors & Farm Equipment", driver.getTitle());
	}
	
	
}

	

