package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("(//button[normalize-space()='Create agent'])[1]");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    private By advancedSection = By.xpath("(//p[normalize-space()='Advanced'])[1]");
    private By silenceTimeoutInput = By.xpath("//input[@name ='silenceEndCallTimeout']");
    private By maxConversationInput = By.xpath("//input[@name ='maxConversationDuration']");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");

    // ---------- Constructor ----------
    public AdvancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ---------- Actions ----------
    public void createAgent(String agentName) {
        wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createAgentButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput)).sendKeys(agentName);
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        System.out.println(" Agent created: " + agentName);
    }

    public void scrollToAdvancedSection() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(advancedSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void configureAdvancedSettings() {
        scrollToAdvancedSection();
        
        // Wait a bit for the page to fully load after scrolling
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Configure Silence end call timeout
        WebElement silenceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(silenceTimeoutInput));
        wait.until(ExpectedConditions.elementToBeClickable(silenceTimeoutInput));
        silenceInput.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        silenceInput.sendKeys("50");
        System.out.println(" Silence end call timeout set to: 50");
        
        // Wait a bit between field changes
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Configure Max conversation duration
        WebElement maxConvInput = wait.until(ExpectedConditions.visibilityOfElementLocated(maxConversationInput));
        wait.until(ExpectedConditions.elementToBeClickable(maxConversationInput));
        maxConvInput.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        maxConvInput.sendKeys("1200");
        System.out.println(" Max conversation duration set to: 1200");
        
        // Wait for the input to be processed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Trigger blur to mark unsaved changes
        driver.findElement(maxConversationInput).sendKeys(Keys.TAB);
        
        // Wait for unsaved changes indicator
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        System.out.println(" Save button clicked.");
    }

    public void scrollToStartCall() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(startCallButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void clickStartCall() {
        scrollToStartCall();
        WebElement startCall = wait.until(ExpectedConditions.elementToBeClickable(startCallButton));
        startCall.click();
        System.out.println(" Start Call button clicked successfully.");
    }
}