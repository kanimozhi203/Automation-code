package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;
import EnglishPages.McpPage;

public class McpTest extends BaseTest {
    private LoginPage loginPage;
    private McpPage mcpPage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        mcpPage = new McpPage(driver);
    }

    @Test(priority = 1)
    public void testCreateAgent() {
        mcpPage.createAgent("mcp test");
    }

    @Test(priority = 2)
    public void testAddMcpServer() {
        mcpPage.addMcpServer(
            "sending email",
            "Custom MCP server to send emails",
            "https://mcp.zapier.com/api/mcp/s/N2MwMTdlZmQtNjVkYi00ZWM2LWJjZWEtODAzYTVlMzcwMDY2OjYxZTY1YmEyLWFmZWUtNGY4Yi05NTQ4LWE2NjZmMmI4MWVmMA==/mcp"
        );
    }

    @Test(priority = 3)
    public void testStartCall() {
        mcpPage.clickStartCall();
    }

    @AfterClass
    public void tearDown() {
        System.out.println(" Browser will stay open for manual inspection.");
        // driver.quit(); // bowser open for debugging
    }
}



