package BrowserStack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class FunctionalAutomationProcess extends Configuration 
{
	SoftAssert sa = new SoftAssert();
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
	
	public void Account_Activity() throws Exception
	{
		String type = readConfigProperties("type");
		
		WebElement e5 = driver.findElement(By.xpath("(//a[contains(text(),'Savings')])[1]"));
		e5.click();
		WebElement e6 = driver.findElement(By.xpath("//a[@href='#ui-tabs-2']"));
		e6.click();
		WebElement e7 = driver.findElement(By.xpath("//select[@id='aa_type']//option[@value='"+type+"']"));
		e7.click();
		WebElement e8 = driver.findElement(By.xpath("//button[@type='submit']"));
		e8.click();
		String description = driver.findElement(By.xpath("(//td[contains(text(),'ONLINE')])[3]")).getText().toString().trim();
		String[] transaction_id = description.split("#");
		System.out.println("The transaction id is: "+transaction_id[1]);
	}
	
	public void Transfer_Funds() throws Exception
	{
		WebElement e9 = driver.findElement(By.xpath("//a[contains(text(),'Transfer Funds')]"));
		e9.click();
		WebElement e10 = driver.findElement(By.xpath("//select[@id='tf_fromAccountId']//option[@value='2']"));
		e10.click();
		WebElement e11 = driver.findElement(By.xpath("//select[@id='tf_toAccountId']//option[@value='2']"));
		e11.click();
		String amount = readConfigProperties("AmountTransfer");
		WebElement e12 = driver.findElement(By.xpath("//input[@id='tf_amount']"));
		e12.sendKeys(amount);
		String transfer_description = readConfigProperties("Transfer_Description");
		WebElement e13 = driver.findElement(By.xpath("//input[@id='tf_description']"));
		e13.sendKeys(transfer_description);
		WebElement e14 = driver.findElement(By.xpath("//button[@type='submit']"));
		e14.click();
		WebElement e15 = driver.findElement(By.xpath("//button[@id='btn_submit']"));
		e15.click();
		String Actual_element = driver.findElement(By.xpath("//div[@class='alert alert-success' and contains(text(),'You successfully submitted your transaction')]")).getText().toString();
		String Expected_element = readConfigProperties("Expected_Text");
		
		sa.assertEquals(Actual_element, Expected_element);
		sa.assertAll();
	}
	
	public void Pay_Bills() throws Exception
	{
		WebElement e16 = driver.findElement(By.xpath("//a[contains(text(),'Pay Bills')]"));
		e16.click();
		String payee = readConfigProperties("Payee");
		WebElement e17 = driver.findElement(By.xpath("//select[@id='sp_payee']//option[@value='"+payee+"']"));
		e17.click();
		WebElement e18 = driver.findElement(By.xpath("//select[@id='sp_account']//option[@value='3']"));
		e18.click();
		String amount = readConfigProperties("AmountTransfer");
		WebElement e19 = driver.findElement(By.xpath("//input[@id='sp_amount']"));
		e19.sendKeys(amount);
		String date = readConfigProperties("Payee_Date");
		WebElement e20 = driver.findElement(By.xpath("//input[@id='sp_date']"));
		e20.sendKeys(date);
		String payee_bill = readConfigProperties("Payee_Description");
		WebElement e21 = driver.findElement(By.xpath("//input[@id='sp_description']"));
		e21.sendKeys(payee_bill);
		WebElement e22 = driver.findElement(By.xpath("//input[@type='submit']"));
		e22.click();
		String actual_element = driver.findElement(By.xpath("//div[@id='alert_content']")).getText().toString();
		String expected_element = readConfigProperties("Expected_Element");
		
		sa.assertEquals(actual_element, expected_element);
		sa.assertAll();
	}
}
