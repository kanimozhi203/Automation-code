package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;
import EnglishPages.SpeedPage;

public class SpeedTest extends BaseTest {
    private LoginPage loginPage;
    private SpeedPage speedPage;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("kandan@zudu.ai");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        speedPage = new SpeedPage(driver);
    }

    @Test(priority = 1)
    public void createAgent() {
        speedPage.createAgent("Speed Test");
        System.out.println("✅ Test executed - Agent created successfully.");
    }

    @Test(priority = 2)
    public void speed() {
        speedPage.adjustSpeed(1.2);
        speedPage.clickSave();
        System.out.println("✅ Test executed - Speed adjusted to 1.2x and saved successfully.");
    }

    @Test(priority = 3)
    public void startCall() {
        speedPage.clickStartCall();
        System.out.println("✅ Test executed - Call started successfully.");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("⚠️ Browser will stay open for manual inspection.");
        // driver.quit(); // intentionally not quitting for manual check
    }
} 