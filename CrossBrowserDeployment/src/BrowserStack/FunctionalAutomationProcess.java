package BrowserStack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class FunctionalAutomationProcess extends Configuration 
{
	SoftAssert sa = new SoftAssert();
	//WebDriver driver = browser_configuration();
	public void SignIn() throws Exception
	{
		String appurl = readConfigProperties("Appurl");
		String username = readConfigProperties("username");
		String password = readConfigProperties("password");
		
		driver.get(appurl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement e1 = driver.findElement(By.xpath("//button[@id='signin_button']"));
		e1.click();
		WebElement e2 = driver.findElement(By.xpath("//input[@id='user_login']"));
		e2.sendKeys(username);
		WebElement e3 = driver.findElement(By.xpath("//input[@id='user_password']"));
		e3.sendKeys(password);
		WebElement e4 = driver.findElement(By.xpath("//input[@type='submit']"));
		e4.click();
		String Expected_element = "Zero Bank";
		String Actual_element = driver.findElement(By.xpath("//a[@class='brand' and contains(text(),'Zero Bank')]")).getText().toString();
		
		sa.assertEquals(Actual_element,Expected_element);
		
		sa.assertAll();
	}
	
	public void CashAccounts() throws Exception
	{
		String from_date = readConfigProperties("from_date");
		String to_Date = readConfigProperties("to_date");
		String from_amount = readConfigProperties("from_amount");
		String to_amount = readConfigProperties("to_amount");
		String type = readConfigProperties("type");
		
		WebElement e5 = driver.findElement(By.xpath("(//a[contains(text(),'Savings')])[1]"));
		e5.click();
		WebElement e6 = driver.findElement(By.xpath("//a[@href='#ui-tabs-2']"));
		e6.click();
		WebElement e7 = driver.findElement(By.xpath("//input[@id='aa_description']"));
		e7.sendKeys("Transfer amount to my saving account");
		WebElement e8 = driver.findElement(By.xpath("//input[@id='aa_fromDate']"));
		e8.sendKeys(from_date);
		WebElement e9 = driver.findElement(By.xpath("//input[@id='aa_toDate']"));
		e9.sendKeys(to_Date);
		WebElement e10 = driver.findElement(By.xpath("//input[@id='aa_fromAmount']"));
		e10.sendKeys(from_amount);
		WebElement e11 = driver.findElement(By.xpath("//input[@id='aa_toAmount']"));
		e11.sendKeys(to_amount);
		WebElement e12 = driver.findElement(By.xpath("//select[@id='aa_type']//option[@value='"+type+"']"));
		e12.click();
		WebElement e13 = driver.findElement(By.xpath("//button[@type='submit']"));
		e13.click();
	}
}
