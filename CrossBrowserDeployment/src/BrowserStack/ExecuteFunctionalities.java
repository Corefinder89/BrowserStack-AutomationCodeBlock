package BrowserStack;

import org.testng.annotations.Test;

public class ExecuteFunctionalities extends FunctionalAutomationProcess 
{
	@Test
	public void executeFunctions() throws Exception
	{
		SignIn();
		CashAccounts();
		driver.quit();
	}
}
