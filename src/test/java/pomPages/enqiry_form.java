package pomPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class enqiry_form {

	Logger log = Logger.getLogger(this.getClass().getName());

	
	@FindBy(xpath="//div[text()=\"Enquiry Form\"]")
	private WebElement enquiry_form;
	
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
	
	@FindBy(xpath="//a[contains(text(),'Contact Us')]")
	private WebElement contactus_menu;
	
	public  enqiry_form(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enqirenow() 
	{
		enquiry_form.click();
	}
	
	public void contactus() 
	{
		contactus_menu.click();
	}
	
	public void contact_form(String Name, String mobile, String email, String state, String category, String country, WebDriver driver ) throws InterruptedException 
	{
		
		if(enquiry_form.isDisplayed()) 
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
