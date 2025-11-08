package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.AnalysisPage;
import EnglishPages.LoginPage;

public class AnalysisTest extends BaseTest {
    private LoginPage loginPage;
    private AnalysisPage analysisPage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        analysisPage = new AnalysisPage(driver);
    }

    @Test(priority = 1)
    public void testCreateAgent() {
        analysisPage.createAgent("Analysis Test");
    }

    @Test(priority = 2)
    public void testAddAnalysisItem() {
        analysisPage.addAnalysisItem("destination_city", "The city where customer want to travel");
    }

    @Test(priority = 3)
    public void testStartCall() {
        analysisPage.clickStartCall();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Browser will stay open for manual inspection.");
        // driver.quit(); // intentionally not quitting for manual check
    }
}
