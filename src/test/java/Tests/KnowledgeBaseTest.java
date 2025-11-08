package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.KnowledgeBasePage;
import EnglishPages.LoginPage;

public class KnowledgeBaseTest extends BaseTest {
    private LoginPage loginPage;
    private KnowledgeBasePage knowledgeBasePage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        
        // Use the new login method with validation
        boolean loginSuccess = loginPage.loginAs("kandan@zudu.ai", "Qwerty@mlp123");
        
        if (!loginSuccess) {
            throw new RuntimeException("Login failed! Cannot proceed with tests.");
        }
        
        System.out.println("Login successful, proceeding with tests...");
        knowledgeBasePage = new KnowledgeBasePage(driver);
    }

    @Test(priority = 1)
    public void testCreateAgent() {
        System.out.println(" Starting Test 1: Create Agent");
        knowledgeBasePage.createAgent("Kb test");
        System.out.println("Test 1 Completed: Agent created successfully");
    }

    @Test(priority = 2)
    public void testKnowledgeBaseData() {
        System.out.println("🚀 Starting Test 2: Add Knowledge Base Data");
        
        try {
            // Add Web Page Knowledge Base
            System.out.println("📄 Adding Web Page Knowledge Base...");
            knowledgeBasePage.addWebPageKnowledgeBase(
                "link", 
                "https://www.visitsingapore.com/travel-tips/essential-travel-information/?anchorid=weather"
            );
            
            // Add File Knowledge Base - Singapore GDP PDF
            System.out.println(" Adding File Knowledge Base - Singapore GDP...");
            knowledgeBasePage.addFileKnowledgeBase("files", "Singapore_GDP_2000_2024.pdf");
            
            // Add File Knowledge Base - South Korea Cities DOCX
            System.out.println(" Adding File Knowledge Base - South Korea Cities...");
            knowledgeBasePage.addFileKnowledgeBase("files", "South_Korea_Cities.docx");
            
            // Add Text Knowledge Base
            System.out.println("Adding Text Knowledge Base...");
            knowledgeBasePage.addTextKnowledgeBase(
                "Text", 
                "data", 
                "This is extra context for testing the AI"
            );
            
            // Refresh page to see if knowledge bases are visible
            System.out.println("Refreshing page to verify knowledge bases...");
            knowledgeBasePage.refreshPage();
            
            // Verify knowledge bases exist in UI
            System.out.println(" Verifying knowledge bases in UI...");
            knowledgeBasePage.verifyKnowledgeBaseExists("link");
            knowledgeBasePage.verifyKnowledgeBaseExists("files");
            knowledgeBasePage.verifyKnowledgeBaseExists("Text");
            
            System.out.println("Test 2 Completed: All Knowledge Base data added successfully");
            
        } catch (Exception e) {
            System.err.println(" Error in Test 2: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3)
    public void testStartCall() {
        System.out.println(" Starting Test 3: Start Call");
        try {
            knowledgeBasePage.clickStartCall();
            System.out.println("Test 3 Completed: Start Call button clicked successfully");
        } catch (Exception e) {
            System.err.println(" Error in Test 3: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println(" Browser will stay open for manual inspection.");
        System.out.println(" Test Summary:");
        System.out.println("   - Test 1: Create Agent ");
        System.out.println("   - Test 2: Knowledge Base Data ");
        System.out.println("   - Test 3: Start Call ");
        // driver.quit(); // intentionally not quitting for manual check
    }
}