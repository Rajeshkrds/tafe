package pomPages;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class product_listing {

	Logger log = Logger.getLogger(getClass().getName());
	@FindBy(xpath="//a[text()='Products']")
	private WebElement product_menu;
	
	@FindBy(xpath="//div[contains(@class,\"flex gap-4 items-center mb-6\")]//button")
	private List< WebElement> drive_type;
	
	@FindBy(xpath="//div[contains(@class,\"relative bg-gray-200\")]")
	private List<WebElement> model;
	
	@FindBy(xpath="(//select[contains(@class,\"appearance-none\")])[1]")
	private WebElement categories;
	
	public product_listing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void product_menu(WebDriver driver) throws IOException 
	{
		log.info("Clicking on product menu from header");
		product_menu.click();
		String url =driver.getCurrentUrl();
		URL newurl =new URL(url);
		HttpsURLConnection connect = (HttpsURLConnection) newurl.openConnection();
		connect.connect();
		 if (connect.getResponseCode() >= 400) {
		        log.info("Page not found - (Response Code : " + connect.getResponseCode() + ")");
		        driver.close();
		    } else {
		        log.info("Listing Page is displayed");
		        SoftAssert a = new SoftAssert();
		        a.assertEquals(driver.getTitle(), "TAFE | Sub 100 HP Range | TAFE TRACTOR Models");
		        a.assertAll();
		    }
		
	}
	
	public void get_drive_type(String driveType) 
	{
		for(WebElement drive : drive_type) 
		{
			
			if(drive.getText().equals(driveType)) 
			{
				log.info("Clicking on drive type : "+drive.getText());
				drive.click();
				log.info("Total models available in "+drive.getText()+ " are : "+model.size());
				for(WebElement models: model) 
				{
					log.info(models.getText());
				}
			}
//			else
//			{
//				log.info("Please provide type of driver looking for");
//			}
			
		}
	}
	
	public void get_category(String driveType, String category) throws InterruptedException 
	{
		for(WebElement drive : drive_type) 
		{
			
			if(drive.getText().equals(driveType)) 
			{
				log.info("Clicking on drive type : "+drive.getText());
				drive.click();
				log.info("Total models available in "+drive.getText()+ " are : "+model.size());
				Select s = new Select (categories);
				s.selectByVisibleText(category);
				Thread.sleep(3000);
				log.info("Total models available in "+drive.getText()+ " and category "+ s.getFirstSelectedOption().getText()+" are : "+model.size());
		
				if(category.equalsIgnoreCase(s.getFirstSelectedOption().getText())) 
				{
					log.info("Available model are : " );
					for(WebElement models: model) 
					{
						log.info(models.getText());
					}
				}
				
			}
//			else
//			{
//				log.info("Please provide type of driver looking for");
//			}
			
		}
	}
	
}
