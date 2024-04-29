package pomPages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.utilities;

public class home_page {

	Logger log = Logger.getLogger(this.getClass().getName());
	utilities u = new utilities();
	
	@FindBy(xpath="//button[contains(@class,\"relative z-10 bg-primary\")]")
	private WebElement social_popup;
	
	@FindBy(xpath="//div[contains(@class,\"absolute bottom-0 w-full\")]//a")
	private List<WebElement> social_links;
	
	@FindBy(xpath="//div[contains(@class,\"flex flex-wrap\")]/child::a")
	private WebElement banner_button;
	
	@FindBy(xpath="//a[text()=\"Download Corporate Brochure\"]")
	private WebElement broucher_CTA;
	
	@FindBy(xpath="//a[contains(text(),'EXPLORE ALL MODELS')]")
	private WebElement explore_models;
	
	@FindBy(xpath="//ul[contains(@class,\"flex justify-between\")]//li")
	private List<WebElement> categories;
	
	@FindBy(xpath="//div[contains(@class,\"relative max-lg\")]/child::div//p")
	private List<WebElement> models;
	
	@FindBy(xpath="//div[contains(@class,\"swiper-slide swiper-slide-visible\")]//h3")
	private WebElement model_name;
	
	@FindBy(xpath="//div[contains(@class,\"swiper-slide swiper-slide-visible\")]//a")
	private WebElement arrow;
	
	@FindBy(xpath="//a[contains(text(),\"Know More\")]")
	private WebElement event_CTA;
	
	@FindBy(xpath="//a[contains(text(),\"EXPLORE MORE\")]")
	private WebElement dealers;
	
	public home_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void social_popup(WebDriver driver) throws InterruptedException, IOException 
	{
		log.info("Checking for social pop over in banner section");
//		if(social_popup.isDisplayed()) 
//		{
			log.info("Social pop over is present");
			social_popup.click();
			log.info("Clicking on social pop over menus");
			for(WebElement links : social_links) 
		//	for(int i=0; i<social_links.size();i++)
			{
			//	String current_url = social_links.get(i).getText();
				String current_url = links.getText();
				((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", links.getAttribute("href"));
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(tabs.size()-1));
				Thread.sleep(5000);
				log.info(driver.getTitle());
				Thread.sleep(5000);
				driver.close();
				driver.switchTo().window(tabs.get(0));

			}
//		}
//		else 
//		{
//			log.info("Social pop over not displayed");
//		}
		
		}
	
	public void banner_CTA() 
	{
		log.info("Checking for CTA in banner");
		if(banner_button.isDisplayed()) 
		{
			log.info("Banner CTA present");
			
			banner_button.click();
			log.info("Clicking on banner CTA");
		}
		else
			log.info("Banner CTA not present");
	}
	
	public void bourcher(WebDriver driver) 
	{
		log.info("Checking for broucher in banner");
		if(broucher_CTA.isDisplayed()) 
		{
		log.info("Broucher CTA present");
		broucher_CTA.sendKeys(Keys.ARROW_DOWN);
			broucher_CTA.click();
			log.info("Clicking on broucher CTA");
		}
		else
			log.info("Broucher not available");
	}
	
	public void explore_CTA(WebDriver driver) 
	{
		log.info("Clicking on explore all CTA");
		explore_models.click();
		u.assertion(driver.getTitle(), "TAFE | Sub 100 HP Range | TAFE TRACTOR Models");
		u.back_navigation(driver);
		
	}


	public void witness_Tafe(WebDriver driver) 
	{
		
		for(WebElement category: categories) 
		{
			log.info(category.getText());
			
		}
		
		for(WebElement category1: categories) 
		{
			
			category1.click();
			log.info("Total models in "+category1+" are : " +models.size());
			for(WebElement name : models) 
			{
				log.info(name.getText());
				Random num = new Random();
				int count= num.nextInt(models.size());
				log.info("Clicking on a model"+models.get(count).getText());
				models.get(count).click();
				
				if(model_name.getText().equals(models.get(count).getText())) 
				{
					arrow.click();
					log.info("Redirecting to "+model_name.getText()+"'s details page");
					log.info(driver.getTitle());
					}
				u.back_navigation(driver);
			}
		}

	}
	
	public void events_section(WebDriver driver) throws InterruptedException 
	{
		log.info("Checking for Events section");
		if(event_CTA.isDisplayed()) 
		{
			log.info("Events section with CTA is displayed");
			event_CTA.click();
			log.info("Clicking on event CTA");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size()-1));
			Thread.sleep(5000);
			log.info(driver.getTitle());
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(tabs.get(0));
		}
		else
			log.info("No event found");
	}
	
	public void find_dealers(WebDriver driver) 
	{
		log.info("Checking for Find a dealers section");
		if(dealers.isDisplayed()) 
		{
			log.info("Events dealers CTA is displayed");
			dealers.click();
			u.back_navigation(driver);
		}
	}
}
