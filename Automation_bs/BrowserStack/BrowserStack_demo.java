/*
 * This code is an explanatory of
 * 1) Functioning of WebDriver
 * 2) Functioning of Assertion
 * 3) Generating test suite
 * 4) Running parallel tests on BrowserStack
 * 
 * @author: Soumyajit Basu
 */

package BrowserStack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BrowserStack_demo 
{
	String email = "rockwithsoumya@gmail.com";
	String password = "soumya8017284515";
	
	WebDriver driver;
	
	@BeforeClass
	//Specify parameters for test run
	@org.testng.annotations.Parameters(value={"browser","version","platform"})
	
	//Set up browser stack for the test cases
	public void bs_setup(String browser, String version, String platform) throws Exception
	{
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform",platform);
		capability.setCapability("browserName",browser);
		capability.setCapability("browserVersion",version);
		capability.setCapability("project","Project-1");
		capability.setCapability("build","1.0");
		capability.setCapability("browserstack.debug", "true");
		driver = new RemoteWebDriver(new URL("http://soumyajitbasu2:LQrXpBax441bhp659Rkm@hub.browserstack.com/wd/hub"),capability);
	}
	
	@Test
	public void demo_browserstack() throws InterruptedException
	{
		//Declare Soft Assert
		SoftAssert s_assert = new SoftAssert();
		
		//Get website
		driver.get("http://www.google.com");
	    System.out.println("Page title is: " + driver.getTitle());
	    
	    //Check for the page
	    s_assert.assertEquals("Google", driver.getTitle());
	    
	    WebElement element = driver.findElement(By.name("q"));
	    
	    //Send data
	    element.sendKeys("Browser Stack");
	    element.submit();
	    
	    driver = new Augmenter().augment(driver);
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try 
	    {
	      FileUtils.copyFile(srcFile, new File("Screenshot.png"));
	    } 
	    catch (IOException e) 
	    {
	      e.printStackTrace();
	    }
	    
	    s_assert.assertAll();
	    
	    //Close the webdriver
	    driver.close();
	}
	
	@AfterClass  
	public void tearDown() throws Exception 
	{  
	    driver.quit();  
	}
}
