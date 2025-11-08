package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.AdvancePage;
import EnglishPages.LoginPage;

public class Advance extends BaseTest {
    private LoginPage loginPage;
    private AdvancePage advancePage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        advancePage = new AdvancePage(driver);
        
        // Login to the application
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
    }

    @Test(priority = 1)
    public void createAgent() {
        advancePage.createAgent("Advance Test");
    }

    @Test(priority = 2)
    public void configureAdvancedSettings() {
        advancePage.configureAdvancedSettings();
        advancePage.clickSave();
    }

    @Test(priority = 3)
    public void startCall() {
        advancePage.clickStartCall();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("⚠️ Browser will stay open for manual inspection.");
        // driver.quit(); // ntentionally not quitting for manual check
    }
}
