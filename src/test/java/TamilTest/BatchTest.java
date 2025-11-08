package TamilTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;
import TamilPages.BatchCall;

public class BatchTest extends BaseTest{
	private LoginPage loginPage;
	private  BatchCall Batchcall;

	@BeforeClass
	public void setUpTest() {
		loginPage = new LoginPage(driver);
		
		// Use the new login method with validation
		boolean loginSuccess = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
		
		if (!loginSuccess) {
			throw new RuntimeException("Login failed! Cannot proceed with tests.");
		}
		
		System.out.println("Login successful, proceeding with tests...");
		Batchcall = new BatchCall(driver);
	}

	@Test(priority = 1)
	public void createTamilAgent() {
		Batchcall.createTamilAgent();
		System.out.println("Test executed - Tamil Agent created successfully.");
	}
	
	@Test(priority = 2)
	public void configureTamilAgent() {
		Batchcall.configureTamilAgent();
		System.out.println("Test executed - Tamil Agent configured, Outbound Call clicked, and navigated to batch call page successfully.");
	}
	
	@Test(priority = 3)
	public void createBatchCall() {
		Batchcall.createBatchCall();
		System.out.println("Test executed - Batch Call created successfully.");
	}

}
