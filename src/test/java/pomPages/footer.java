package pomPages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import BaseClass.configFile;

public class footer {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	
	@FindBy(xpath="(//div[contains(@class,\"flex flex-wrap \")])[6]/child::div//a")
	private List<WebElement> footer_links;
	
	@FindBy(xpath="(//div[contains(@class,\"flex flex-wrap \")])[6]/child::div")
	private List<WebElement> footer_menu;
	
	@FindBy(xpath="//div[contains(@class,\"max-w-screen-max text\")]/child::div[1]//div")
	private List<WebElement> tafe_details;
	
	@FindBy(xpath="//div[@class=\"text-center p-6 text-sm\"]")
	private WebElement copyRights;
	
	@FindBy(xpath="//form[@class=\"flex flex-wrap gap-4\"]")
	private WebElement contact_form;
	
	@FindBy(name="name")
	private WebElement name_field;
	
	@FindBy(name="email")
	private WebElement email_id;
	
	@FindBy(name="phone")
	private WebElement phone_number;
	
	@FindBy(name="category")
	private WebElement category_dd;
	
	@FindBy(name="country")
	private WebElement country_dd;
	
	@FindBy(name="province")
	private WebElement province_field;
	
	@FindBy(id="rc-anchor-container")
	private WebElement captcha;
	
	@FindBy(xpath="//button[contains(text(),\"Submit\")]")
	private WebElement submit_Button;
	
	public footer(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void footer_links(WebDriver driver) throws InterruptedException, IOException 
	{
		
		
		log.info("Testing footer links");
		for(int i=0; i< footer_links.size();i++)
		//for(WebElement options : footer_links)
		{
			String text= footer_links.get(i).getText();
			//String text= options.getText();
			String curent_url = footer_links.get(i).getAttribute("href");
		//	String curent_url = options.getAttribute("href");
			((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", footer_links.get(i).getAttribute("href"));
		//	((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", options.getAttribute("href"));
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size()-1));
			Thread.sleep(5000);
			
			URL url =new URL(curent_url);
			HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
			httpConnect.connect();
			
			if(httpConnect.getResponseCode()>=400) 
			{
				log.info("404 Page not found - "+driver.getTitle());
			}
			else 
			{
				log.info(driver.getTitle());
				
			}
			Thread.sleep(5000);
			driver.close();
			
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(5000);
			
		}
	}
	
	
	public void footer_menus(WebDriver driver) throws InterruptedException, IOException 
	{
		if(footer_menu.size()>0) 
		{
			log.info("Getting Footer Menus");
			for(WebElement menus : footer_menu) 
			{
				log.info(menus.getText());
			}
			
			footer_links(driver);
		}
		else
			log.info("No Menus found in footer section");
		
	}
	
	public void tafe_details() 
	{
		if(tafe_details.size()>0) 
		{
			log.info("Getting Tafe Details from footer");
			for(WebElement details : tafe_details) 
			{
				log.info(details.getText());
			}
		}
		else
			log.info("Tafe corporate details are misssing in Footer section");
		
	}
	
	public void copy_rights() 
	{
		if(copyRights.isDisplayed())
			log.info(copyRights.getText());
		else
			log.info("Copy rights text not availble in footer");
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public void contact_form(String Name, String mobile, String email, String state, String category, String country, WebDriver driver ) throws InterruptedException 
	{
		if(contact_form.isDisplayed()) 
		{
			
			log.info("Entering name");
			name_field.sendKeys(Name);
			log.info("Entering email id");
			email_id.sendKeys(email);
			log.info("Entering mobile");
			phone_number.sendKeys(mobile);
			log.info("Selecting a category from Category dropdown");
			Select categories = new Select(category_dd);
			log.info("List of categories available are : ");
			for(WebElement cat_option : categories.getOptions() ) 
			{
				log.info(cat_option.getText());
				
				if(cat_option.getText().equals(category))
					categories.selectByVisibleText(category);
				
			}
			
			log.info("Selecting a country from country dropdown");
			Select countries = new Select(country_dd);
			//log.info(countries.getOptions());
			log.info("List of countries available are : ");
			for(WebElement coun_option : countries.getOptions() ) 
			{
				log.info(coun_option.getText());
				if(coun_option.getText().equals(country))
					countries.selectByVisibleText(country);
				
			}
			
			log.info("Entering state");
			province_field.sendKeys(state);
			log.info("Validating recaptch");
			driver.switchTo().frame(0);
			Thread.sleep(5000);
			if(captcha.isDisplayed())
				captcha.click();
			else
				log.info("Captch is missing in footer section");
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			submit_Button.click();
			log.info("Clicking on submit button");
			
		}
		
		else 
			log.info("Contact form is missing in footer section");
	}
}
