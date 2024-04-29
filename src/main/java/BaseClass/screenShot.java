package BaseClass;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenShot {

	public void takeScreenshot(WebDriver driver, String testName) throws IOException 
	{
		Date Current_date = new Date();
		String formatted_date = Current_date.toString().replaceAll(":","-");
		String file_Name = testName+"_"+formatted_date+".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\failed_TC_screenshots\\"+file_Name);
		FileUtils.copyFile(src, des);
	}
}
