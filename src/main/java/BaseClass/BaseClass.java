package BaseClass;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.internal.org.jline.utils.Log;

@Listeners({testListners.class})
public class BaseClass {

	public WebDriver driver;
	public static Logger log ;
	configFile readFile = new configFile();
	public String url  = readFile.getBaseURL();
	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters("browser")
	public void openBrowser(@Optional("chrome") String browser) throws IOException {
	    log = Logger.getLogger(BaseClass.class);
	    PropertyConfigurator.configure("log4j.properties");
	    
	    if (browser.equals("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        log.info("Chrome Browser setup completed");
	        driver = new ChromeDriver();
	    } else if (browser.equals("FireFox")) {
	        WebDriverManager.firefoxdriver().setup();
	        log.info("FireFox Browser setup completed");
	        driver = new FirefoxDriver();
	    } else if (browser.equals("Edge")) {
	        WebDriverManager.edgedriver().setup();
	        log.info("Edge Browser setup completed");
	        driver = new EdgeDriver();
	    } else {
	        throw new IllegalArgumentException("Invalid browser specified: " + browser);
	    }
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get(url);
	    
	    URL Current_URL = new URL(url);
	    HttpsURLConnection URL_connect = (HttpsURLConnection) Current_URL.openConnection();
	    
	    URL_connect.connect();
	    
	    if (URL_connect.getResponseCode() >= 400) {
	        log.info("Page not found - (Response Code : " + URL_connect.getResponseCode() + ")");
	        driver.close();
	    } else {
	        log.info("Landing Page is displayed");
	        SoftAssert a = new SoftAssert();
	        a.assertEquals(driver.getTitle(), "TAFE TRACTORS | International Agricultural and Farm Tractor");
	        a.assertAll();
	    }
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) throws IOException {
	    
	        int status = result.getStatus();
	        String name = result.getName();
	        
	        if (status == ITestResult.FAILURE) {
	            screenShot s = new screenShot();
	            s.takeScreenshot(driver, name);
	            log.info("Taking Screenshot of failed test case" + getClass().getName());
	        }
	    
	    log.info("Closing Browser");
	//    driver.close();
	}

}
