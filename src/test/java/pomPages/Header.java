package pomPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.configFile;
import BaseClass.utilities;

public class Header {
	
	utilities u = new utilities();
	configFile c = new configFile();

	Logger log = Logger.getLogger(getClass().getName());
	@FindBy(xpath="//div[contains(@class,\"flex flex-col overflow\")]//a")
	private List<WebElement> header_Menu;
	
	@FindBy(xpath="//button[@class=\"text-xl shrink-0 text-light\"]")
	private WebElement select_country_CTA;
	
	@FindBy(xpath="//a[contains(text(),'International Site')]")
	private WebElement international_site_CTA;
	
	@FindBy(xpath ="(//div[contains(@class,\"grid grid-cols\")])[1]/child::div//p")
	private List<WebElement> regions;
	
	@FindBy(xpath="(//div[contains(@class,\"grid grid-cols\")])[1]/child::div//div")
	private List<WebElement> countries;
	
	@FindBy(xpath="//button[contains(@class,\"hidden lg:block\")]")
	private WebElement close_icon;
	
	@FindBy(xpath="//button[contains(@class,\"p-1 flex items-baseline\")]")
	private WebElement language;
	
	@FindBy(xpath="//button[contains(@class,\"p-1 flex items-baseline\")]/following-sibling::div//a")
	private List<WebElement> lang_list;
	
	public Header(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void header_menu(WebDriver driver) throws InterruptedException 
	{
		log.info("Testing Header Menu");
		for(WebElement options:header_Menu)
	{
		String text = options.getText();
		log.info("Header Menu - "+options.getText());
		((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", options.getAttribute("href"));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size()-1));
		Thread.sleep(5000);
		log.info(driver.getTitle());
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}	
	}
	
	public void countryPopup(WebDriver driver) 
	{
		select_country_CTA.click();
		log.info("Clicking on Select country Dropdown");
		if(international_site_CTA.isDisplayed()) 
		{
			log.info("Clicking on International site CTA");
			international_site_CTA.click();
			log.info("Redirecting to international site's Home page "+driver.getTitle());
			u.assertion(c.getBaseURL(), driver.getCurrentUrl());
		}
	}
	
	public void regions_countries(WebDriver driver) throws InterruptedException 
	{
		select_country_CTA.click();
		for(int i=0; i<regions.size();i++) 
		{
			log.info("Regions : "+regions.get(i).getText());
			log.info("List of countries available in "+regions.get(i).getText()+" are :");
			log.info("=============================================================");
			List<WebElement> country_list = driver.findElements(By.xpath("(//div[contains(@class,\"grid grid-cols\")])[1]/child::div["+ (i+1) +"]//div"));
			for(WebElement country: country_list)
			{
				log.info(country.getText());
				}
		}
		
		Random rand = new Random();
		int newrand = rand.nextInt(countries.size());
		log.info("Clicking country or locale "+	countries.get(newrand).getText());
		countries.get(newrand).click();
	
		Thread.sleep(3000);
		
		log.info("Redirecting to " + select_country_CTA.getText()+" site");
		
	}

	public void closePopup() 
	{
		log.info("Opening country Popup");
		select_country_CTA.click();
		log.info("closing country Popup");
		close_icon.click();
	}
	
	public void languages(WebDriver driver) throws InterruptedException 
	{
		language.click();
		for(WebElement languages : lang_list) 
		{
			log.info(languages.getText());
			
		}
		Random ran = new Random();
		int randLang = ran.nextInt(lang_list.size());
		log.info("Clicking on "+	lang_list.get(randLang).getText()+ " locale ");
		lang_list.get(randLang).click();
		Thread.sleep(5000);
		log.info(driver.getCurrentUrl());
	}
}