package Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.BaseTest;
import EnglishPages.AgentPage;
import EnglishPages.LoginPage;

public class AgentTest extends BaseTest {

    private LoginPage loginPage;
    private AgentPage agentPage;

    
    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        
        // Use the new login method with validation
        boolean loginSuccess = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
        
        if (!loginSuccess) {
            throw new RuntimeException("Login failed! Cannot proceed with tests.");
        }
        
        System.out.println("Login successful, proceeding with tests...");
        agentPage = new AgentPage(driver);
    }

    // Test 1: Validate Application Title
    @Test(priority = 1)
    public void testApplicationTitle() {
        String actualTitle = driver.getTitle();
        System.out.println("Application title after login: " + actualTitle);
        Assert.assertTrue(actualTitle.contains("ZUDU") || actualTitle.contains("Dashboard"),
                "Unexpected application title after login!");
    }

    // Test 2: Create, Update, Cancel Delete, and Navigate to Call History
    @Test(priority = 2)
    public void testCreateUpdateAndGoToCallHistory() {
        String initialName = "Test Agent";
        String updatedName = "Updated Agent";

        // Create agent and update name
        agentPage.createAndUpdateAgent(initialName, updatedName);

        // Verify name update
        Assert.assertTrue(agentPage.isOnAgentDetailPage(updatedName),
                "Agent detail page not loaded or agent name not updated!");

        // Debug: Check what options are available in the more menu
        agentPage.debugMoreMenuOptions();

        // Attempt to delete but cancel (or skip if not available)
        agentPage.attemptDeleteAgentButCancelOrSkip();

        // Navigate to Call History
        agentPage.clickCallHistory();

        // Validate Call History page
        Assert.assertTrue(agentPage.isCallHistoryPageVisible(),
                "Call History page not loaded correctly!");
    }
}
