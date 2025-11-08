
package TamilTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;
import TamilPages.CreateAgent;

public class TamilTest extends BaseTest {
	private LoginPage loginPage;
	private CreateAgent createAgent;

	@BeforeClass
	public void setUpTest() {
		loginPage = new LoginPage(driver);
		
		// Use the new login method with validation
		boolean loginSuccess = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
		
		if (!loginSuccess) {
			throw new RuntimeException("Login failed! Cannot proceed with tests.");
		}
		
		System.out.println("Login successful, proceeding with tests...");
		createAgent = new CreateAgent(driver);
	}

	@Test(priority = 1)
	public void createTamilAgent() {
		createAgent.createTamilAgent();
		System.out.println("Test executed - Tamil Agent created successfully.");
	}
	
	@Test(priority = 2)
	public void configureTamilAgent() {
		createAgent.configureTamilAgent();
		System.out.println("Test executed - Tamil Agent configured successfully.");
	}
	
	

	// tearDown is handled by BaseTest - browser will stay open for all test methods
}
