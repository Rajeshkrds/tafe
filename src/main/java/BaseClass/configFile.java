package BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class configFile {

	Properties datafile;
	public  configFile()
	{
		
		try {
			FileInputStream filepath = new FileInputStream(
					"C:\\Users\\Administrator\\eclipse-workspace\\TAFE\\src\\test\\resources\\data.properties");
			datafile = new Properties();
			datafile.load(filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error loading configuration file "+e.getMessage());
		}

	}
	
	public String getBrowser() 
	{
		String Browser = datafile.getProperty("browser");
		return Browser;
	}
	
	public String getBaseURL() 
	{

		String baseurl = datafile.getProperty("url");
		
		if(baseurl!=null)
			return baseurl;
		else
			 throw new RuntimeException("URL not specified in properites file");
	
	}
	
	
	
}
