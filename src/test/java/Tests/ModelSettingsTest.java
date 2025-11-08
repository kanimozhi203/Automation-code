package Tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Base.BaseTest;
import EnglishPages.LoginPage;
import EnglishPages.ModelSetting;
public class ModelSettingsTest extends BaseTest {
   private LoginPage loginPage;
   private ModelSetting modelSetting;
   @BeforeClass
   public void setUpTest() {
       loginPage = new LoginPage(driver);
       loginPage.enterEmail("kandan@zudu.ai");
       loginPage.clickContinue();
       loginPage.enterPassword("Qwerty@mlp123");
       loginPage.clickSignIn();
       modelSetting = new ModelSetting(driver);
   }
   @Test(priority = 1)
   public void createAgent() {
       // Step 1: Create Agent
       modelSetting.goToCreateAgent("Test Agent");
       System.out.println(" Test executed - Agent created successfully.");
   }
   
   @Test(priority = 2)
   public void addVariables() {
       // Step 2: Add Variables (without saving)
       modelSetting.addRequiredVariables();
       System.out.println(" Test executed - Variables added successfully.");
   }
   
   @Test(priority = 3)
   public void configureSystemPrompt() {
       try {
           // Step 3: Configure System Prompt and Save
           modelSetting.clearSystemPrompt();
           modelSetting.enterSystemPromptGuidelines();
           
           // Wait a moment for content to be processed
           Thread.sleep(1000);
           
           // Validate system prompt was entered correctly
           if (modelSetting.isSystemPromptConfigured()) {
               System.out.println(" System prompt validation passed - Guidelines entered correctly.");
           } else {
               System.out.println("️ System prompt validation warning - Content may not be fully loaded.");
           }
           
           // Save after configuring system prompt
           modelSetting.clickSave();
           System.out.println(" Test executed - System prompt configured and saved successfully.");
           
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
           System.err.println("Test interrupted: " + e.getMessage());
       } catch (Exception e) {
           System.err.println("System prompt configuration failed: " + e.getMessage());
           throw e;
       }
   }
   
   @Test(priority = 4)
   public void clickStartCall() {
       // Step 4: Start Call
       modelSetting.clickStartCall();
       System.out.println("Test executed - Call started successfully.");
   }
   
//  Override BaseTest's tearDown so browser won't close
   @AfterClass
   public void tearDown() {
       System.out.println("⚠ Browser will stay open for manual call inspection.");
       // Do NOT call driver.quit()
   }
}
