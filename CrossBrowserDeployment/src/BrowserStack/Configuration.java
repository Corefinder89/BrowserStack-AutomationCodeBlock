package BrowserStack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;

public class Configuration 
{
	//Instantiate WebDriver
	WebDriver driver;
	
	@BeforeClass
	@org.testng.annotations.Parameters(value={"browser","browser_version","os","os_version","resolution"})
	
	public WebDriver browser_configuration(String browser,String browser_version,String os,String os_version,String resolution)
	{
		final String USERNAME = "<USERNAME>";
		final String AUTOMATE_KEY = "<AutomateKey>";
		final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		
		try
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser",browser);
			caps.setCapability("browser_version",browser_version);
			caps.setCapability("os",os);
			caps.setCapability("os_version",os_version);
			caps.setCapability("resolution",resolution);
			caps.setCapability("project","Project-1");
			caps.setCapability("build","1.0");
			caps.setCapability("browserstack.debug","true");
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new RemoteWebDriver(new URL(URL),caps);
		} 
		catch(MalformedURLException e)
		{
			e.getMessage();
		}
		
		return driver;
	}
	
	public String readConfigProperties(String parameter) throws IOException,FileNotFoundException
	{
		Properties prop = new Properties();
		String path = "../CrossBrowserDeployment/Information.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		String value = prop.getProperty(parameter);
		if(value == null)
			System.out.println("Property value " +parameter+" not found");
		
		return value;
	}
}
