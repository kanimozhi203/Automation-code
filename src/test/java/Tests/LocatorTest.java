package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.KnowledgeBasePage;
import EnglishPages.LoginPage;

public class LocatorTest extends BaseTest {
    private LoginPage loginPage;
    private KnowledgeBasePage knowledgeBasePage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        knowledgeBasePage = new KnowledgeBasePage(driver);
    }

    @Test(priority = 1)
    public void testCreateAgentAndNavigateToEditPage() {
        System.out.println(" Testing: Create Agent and Navigate to Edit Page");
        try {
            knowledgeBasePage.createAgent("Locator Test Agent");
            System.out.println(" Agent created successfully");
        } catch (Exception e) {
            System.err.println("Error creating agent: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2)
    public void testAllLocators() {
        System.out.println(" Testing: All Knowledge Base Locators");
        try {
            knowledgeBasePage.testAllLocators();
            System.out.println(" Locator testing completed");
        } catch (Exception e) {
            System.err.println(" Error testing locators: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("️ Browser will stay open for manual inspection.");
        System.out.println(" Locator Test Summary:");
        System.out.println("   - Check the console output above for locator status");
        System.out.println("   - Update any failed locators in KnowledgeBasePage.java");
        // driver.quit(); // intentionally not quitting for manual check
    }
}