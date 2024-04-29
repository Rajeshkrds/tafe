package pomPages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.github.dockerjava.api.model.Links;

public class home_page {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	@FindBy(xpath="//button[contains(@class,\"relative z-10 bg-primary\")]")
	private WebElement social_popup;
	
	@FindBy(xpath="//div[contains(@class,\"absolute bottom-0 w-full\")]//a")
	private List<WebElement> social_links;
	
	
	public home_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void social_popup(WebDriver driver) 
	{
		if(social_popup.isDisplayed()) 
		{
			social_popup.click();
			for(WebElement links : social_links) 
			{
				String current_url = 
				((JavascriptExecutor)driver).executeAsyncScript("window.open(arguments[0])", links.getAttribute("href"));
				ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(tabs.size()-1));
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
		
		}
}
