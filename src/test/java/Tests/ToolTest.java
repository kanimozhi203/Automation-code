package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import Base.BaseTest;
import EnglishPages.LoginPage;
import EnglishPages.ToolsPage;

public class ToolTest extends BaseTest {
    private LoginPage loginPage;
    private ToolsPage toolsPage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        toolsPage = new ToolsPage(driver);
        
        // Wait for login to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(priority = 1)
    public void testCreateAgent() {
        try {
            toolsPage.createAgent("Tools test");
            System.out.println(" Test 1: Agent created successfully");
        } catch (Exception e) {
            System.err.println(" Test 1 failed: " + e.getMessage());
            Assert.fail("Agent creation failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testAddEndCallTool() {
        try {
            toolsPage.addEndCallTool();
            System.out.println(" Test 2: End Call tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 2 failed: " + e.getMessage());
            Assert.fail("End Call tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testAddVoicemailDetectionTool() {
        try {
            toolsPage.addVoicemailDetectionTool();
            System.out.println(" Test 3: Voicemail Detection tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 3 failed: " + e.getMessage());
            Assert.fail("Voicemail Detection tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testAddLanguageDetectionTool() {
        try {
            toolsPage.addLanguageDetectionTool();
            System.out.println(" Test 4: Language Detection tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 4 failed: " + e.getMessage());
            Assert.fail("Language Detection tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testAddPressDigitTool() {
        try {
            toolsPage.addPressDigitTool();
            System.out.println(" Test 5: Press Digit tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 5 failed: " + e.getMessage());
            Assert.fail("Press Digit tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void testAddWebhookTool() {
        try {
            String description = "Webhook to send email via n8n workflow";
            String url = "https://kanimozhi1.app.n8n.cloud/webhook-test/custom-tool";
            toolsPage.addWebhookTool(description, url);
            System.out.println(" Test 6: Webhook tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 6 failed: " + e.getMessage());
            Assert.fail("Webhook tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void testAddTransferToAIAgentTool() {
        try {
            String description = "This allows to transform call another ai agent";
            String condition = "as per the user request";
            toolsPage.addTransferToAIAgentTool(description, condition);
            System.out.println(" Test 7: Transfer to AI Agent tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 7 failed: " + e.getMessage());
            Assert.fail("Transfer to AI Agent tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void testAddTransferToHumanTool() {
        try {
            String description = "This tool allows the system to transfer a call to a human agent when needed.";
            String phoneNumber = "+918968668";
            toolsPage.addTransferToHumanTool(description, phoneNumber);
            System.out.println(" Test 8: Transfer to Human tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 8 failed: " + e.getMessage());
            Assert.fail("Transfer to Human tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 9)
    public void testAddCheckCalendarAvailabilityTool() {
        try {
            String description = "This tool checks the availability of events in a user's Cal.com calendar.";
            String apiKey = "cal_live_d1ba4b6c9eb0c990b5935cfa4e48d7f6";
            String eventTypeId = "3082200";
            String timezone = "Asia/Kolkata";
            toolsPage.addCheckCalendarTool(description, apiKey, eventTypeId, timezone);
            System.out.println("Test 9: Check Calendar Availability tool added successfully");
        } catch (Exception e) {
            System.err.println("Test 9 failed: " + e.getMessage());
            Assert.fail("Check Calendar Availability tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 10)
    public void testAddBookOnCalendarTool() {
        try {
            String description = "This tool allows you to book an appointment on a specific Cal.com calendar based on the event type provided.";
            String apiKey = "cal_live_d1ba4b6c9eb0c990b5935cfa4e48d7f6";
            String eventTypeId = "3082200";
            String timezone = "Asia/Kolkata";
            toolsPage.addBookOnCalendarTool(description, apiKey, eventTypeId, timezone);
            System.out.println("Test 10: Book on Calendar tool added successfully");
        } catch (Exception e) {
            System.err.println("Test 10 failed: " + e.getMessage());
            Assert.fail("Book on Calendar tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 11)
    public void testAddSendSMSTool() {
        try {
            String staticContent = "meeting booked";
            toolsPage.addSendSMSTool(staticContent);
            System.out.println("Test 11: Send SMS tool added successfully");
        } catch (Exception e) {
            System.err.println(" Test 11 failed: " + e.getMessage());
            Assert.fail("Send SMS tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 12)
    public void testAddScheduleCallTool() {
        try {
            toolsPage.addScheduleCallTool();
            System.out.println(" Test 12: Schedule Call tool added successfully");
        } catch (Exception e) {
            System.err.println("Test 12 failed: " + e.getMessage());
            Assert.fail("Schedule Call tool addition failed: " + e.getMessage());
        }
    }

    @Test(priority = 13)
    public void testStartCall() {
        try {
            // Wait a bit before the final test
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            toolsPage.clickStartCall();
            System.out.println(" Test 13: Start Call button clicked successfully");
        } catch (Exception e) {
            System.err.println("Test 13 failed: " + e.getMessage());
            Assert.fail("Start Call failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Browser will stay open for manual inspection.");
        // driver.quit(); // intentionally not quitting for manual check
    }
} 