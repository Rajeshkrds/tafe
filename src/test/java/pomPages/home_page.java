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
	
	@FindBy(xpath="//button[contains(@class,\"text-lg tracking-wider \")]")
	private List<WebElement> categories;
	
	@FindBy(xpath="//button[contains(@class,\"cursor-pointer text-light\")]")
	private WebElement closeicon;
	
	//@FindBy(xpath="//div[contains(@class,\"relative max-lg\")]/child::div//p")
	//@FindBy(xpath="(//div[contains(@class,\"swiper-wrapper\")])[4]/child::div//p")
	@FindBy(xpath="//div[contains(@class,\"mb-10 w-full\")]/child::div//p")
	private List<WebElement> models;
	
	@FindBy(xpath="//div[contains(@class,\"swiper-slide swiper-slide-visible\")]//h3")
	private WebElement model_name;
	
	@FindBy(xpath="//div[contains(@class,\"swiper-slide swiper-slide-visible\")]//a")
	private WebElement arrow;
	
	@FindBy(xpath="//a[contains(text(),\"Know More\")]")
	private WebElement event_CTA;
	
	//@FindBy(xpath="//a[contains(text(),\"EXPLORE MORE\")]")
	@FindBy(xpath="//div[@class=\"flex flex-wrap gap-5\"]//a")
	private WebElement dealers;
	
	//@FindBy(xpath="//span[text()='PLAY']")
	@FindBy(xpath="//button[contains(@class,\"flex items-center absolute\")]")
	private WebElement playbutton;
	
	@FindBy(xpath="//a[text()='Read More']")
	private List<WebElement> read_button;
	
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
	
	public void bourcher(WebDriver driver) throws InterruptedException 
	{
		log.info("Checking for broucher in banner");
		if(broucher_CTA.isDisplayed()) 
		{

		log.info("Broucher CTA present");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", broucher_CTA);
			broucher_CTA.click();
			log.info("Clicking on broucher CTA");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabs.size()-1));
			Thread.sleep(5000);
			log.info(driver.getTitle());
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(tabs.get(0));
		}
		else
			log.info("Broucher not available");
	}
	
	public void explore_CTA(WebDriver driver) 
	{
		log.info("Clicking on explore all CTA");
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", explore_models);
		Actions a = new Actions(driver);
		a.moveToElement(explore_models).build().perform();
		explore_models.click();
		u.assertion(driver.getTitle(), "TAFE | Sub 100 HP Range | TAFE TRACTOR Models");
		u.back_navigation(driver);
		
	}


	public void witness_Tafe(WebDriver driver) throws InterruptedException 
	{
		//model_name.sendKeys(Keys.ARROW_DOWN);
//		for(WebElement category: categories) 
//		{
//			log.info(category.getText());
//			
//		}
		
		Actions a =new Actions(driver);

		for(WebElement category1: categories) 
		{
			a.moveToElement(category1).build().perform();
			category1.click();
			log.info(category1.getText());
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", category1);
			
			log.info("Total models in "+category1.getText()+" are : " +models.size());
			for(WebElement name : models) 
			//for(int i=1;i<models.size();i++)
			{
				log.info(name.getText());
				//log.info(models.get(i).getText());
				Thread.sleep(2000);
			}
		}
		
		Thread.sleep(5000);
		Random num = new Random();
		int count= num.nextInt(models.size());
		log.info("Clicking on a model"+models.get(count).getText());
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", name);
		a.moveToElement(models.get(count)).build().perform();
		models.get(count).click();
		if(model_name.getText().contains(models.get(count).getText())) 
		{
			a.moveToElement(arrow).build().perform();
			arrow.click();
			log.info("Redirecting to "+model_name.getText()+"'s details page");
			Thread.sleep(5000);
			log.info(driver.getTitle());
		}
	//	u.back_navigation(driver);

	}
	
	public void events_section(WebDriver driver) throws InterruptedException 
	{
		log.info("Checking for Events section");
		if(event_CTA.isDisplayed()) 
		{
			log.info("Events section with CTA is displayed");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", event_CTA);
		//	event_CTA.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(3000);
		//	event_CTA.sendKeys(Keys.ARROW_DOWN);
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
			log.info("Dealers CTA is displayed");
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dealers);
			Actions a = new Actions(driver);
			a.moveToElement(dealers).build().perform();
			dealers.click();
			u.assertion("Contact TAFE TRACTORS | Tractors & Farm Equipment", driver.getTitle());
			u.back_navigation(driver);
		}
	}
	
	public void story_section(WebDriver driver) 
	{
		log.info("Clicking on Play button");
		Actions a = new Actions(driver);
		a.moveToElement(playbutton).build().perform();
		playbutton.click();
		log.info("Closing video popup");
		closeicon.click();
	}
	
	public void news_blogs(WebDriver driver) throws InterruptedException 
	{
		log.info("Clicking on Read More CTA in news and blog section");
		for(WebElement read_more : read_button) 
			//	for(int i=0; i<social_links.size();i++)
				{
				//	String current_url = social_links.get(i).getText();
					String current_url = read_more.getText();
					((JavascriptExecutor)driver).executeScript("window.open(arguments[0])", read_more.getAttribute("href"));
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(tabs.size()-1));
					Thread.sleep(5000);
					log.info(driver.getTitle());
					Thread.sleep(5000);
					driver.close();
					driver.switchTo().window(tabs.get(0));

				}
	}
}
