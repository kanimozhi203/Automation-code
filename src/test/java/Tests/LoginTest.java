package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() {
	    System.out.println("=== Starting Login Test ===");
	    
	    // Ensure we start from a clean state - navigate to login page
	    BaseTest.resetToLoginPage();
	    
	    LoginPage loginPage = new LoginPage(driver);
	    
	    // Use the improved login method with better error handling
	    boolean loginResult = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
	    
	    // Verify login was successful
	    Assert.assertTrue(loginResult, "Login process failed - check console output for details");
	    
	    // Additional verification
	    String currentUrl = driver.getCurrentUrl();
	    String actualTitle = driver.getTitle();
	    
	    System.out.println("Final URL: " + currentUrl);
	    System.out.println("Final Title: " + actualTitle);
	    
	    // Check if we're on the expected page
	    boolean isOnCorrectPage = currentUrl.contains("agents") || 
	                             currentUrl.contains("dashboard") || 
	                             actualTitle.contains("ZUDU") || 
	                             actualTitle.contains("Home");
	    
	    Assert.assertTrue(isOnCorrectPage, 
	        "Login verification failed! Current URL: " + currentUrl + 
	        ", Title: " + actualTitle);
	    
	    System.out.println("=== Login Test Completed Successfully ===");
	}
	
	/*@Test
	public void testLoginAndNavigateToAgents() {
	    System.out.println("=== Starting Login and Navigation Test ===");
	    
	    LoginPage loginPage = new LoginPage(driver);
	    
	    // Login first
	    boolean loginResult = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
	    Assert.assertTrue(loginResult, "Login failed");
	    
	    // Try to navigate to agents page
	    try {
	        driver.get("https://app.uat.zudu.ai/agents");
	        Thread.sleep(3000);
	        
	        String currentUrl = driver.getCurrentUrl();
	        System.out.println("After navigation to agents: " + currentUrl);
	        
	        // Check if we can see agents page content
	        boolean hasAgentsContent = driver.getPageSource().contains("Agents") || 
	                                   driver.getPageSource().contains("Create agent");
	        
	        Assert.assertTrue(hasAgentsContent, "Agents page not loaded properly");
	        
	    } catch (Exception e) {
	        Assert.fail("Navigation to agents page failed: " + e.getMessage());
	    }
	    
	    System.out.println("=== Login and Navigation Test Completed ===");
	} */

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
