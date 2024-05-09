package pomPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class contactus {

	Logger log = Logger.getLogger(this.getClass().getName());

	@FindBy(xpath="//div[contains(@class,'flex py-10 lg')]")
	private WebElement tafe_details;
	
	@FindBy(xpath="(//div[contains(@class,'max-w-screen-max')])[4]")
	private  WebElement accordian;
	
	@FindBy(xpath="(//article[contains(@class,'flex justify-between')])[1]")
	private WebElement text;
	
	public contactus(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void tafe_details() 
	{
		log.info(tafe_details.getText());
	}
	
	public void accordian(WebDriver driver) throws InterruptedException 
	{
		//log.info(accordian.getText());
		
		log.info(text.getText());
		
//		for(WebElement faq: accordian) 
//		{
//			
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faq);
//
//			faq.click();
//			Thread.sleep(3000);
//			log.info(faq.getText());
//			Thread.sleep(3000);
//
//		}
	}
}
