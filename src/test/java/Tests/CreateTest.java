package Tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import EnglishPages.LoginPage;
import TamilPages.CreateAgent;

public class CreateTest extends BaseTest {
    private LoginPage loginPage;
    private CreateAgent createAgent;

    @BeforeClass
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("Qateam@klanty.com");
        loginPage.clickContinue();
        loginPage.enterPassword("Qwerty@mlp123");
        loginPage.clickSignIn();
        createAgent = new CreateAgent(driver);
    }

    @Test(priority = 1)
    public void createTamilAgent() {
        createAgent.createTamilAgent();
        System.out.println("✅ Test executed - Tamil Agent created successfully.");
    }
}
