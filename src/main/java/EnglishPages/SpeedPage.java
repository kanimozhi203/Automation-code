package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpeedPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("//button[normalize-space()='Create agent']");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    private By speedSlider = By.xpath("//span[@role='slider']");
    private By speedValue = By.xpath("//span[@role='slider']");
    private By saveButton = By.xpath("//button[normalize-space()='Save']");
    private By startCallButton = By.xpath("//button[normalize-space()='Start call']");
    private By allowThisTimeButton = By.xpath("//button[contains(text(),'Allow this time')]");

    // ---------- Constructor ----------
    public SpeedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- Actions ----------
    public void createAgent(String agentName) {
        wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createAgentButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput)).sendKeys(agentName);
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        System.out.println("Agent created: " + agentName);
    }

    public void adjustSpeed(double targetSpeed) {
        // Wait for speed slider to be present
        WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(speedSlider));
        
        // Get current speed value from aria-valuenow attribute
        String currentSpeedText = slider.getAttribute("aria-valuenow");
        System.out.println("Current speed: " + currentSpeedText);
        
        // Get slider range from aria attributes
        double minSpeed = Double.parseDouble(slider.getAttribute("aria-valuemin"));
        double maxSpeed = Double.parseDouble(slider.getAttribute("aria-valuemax"));
        double currentSpeed = Double.parseDouble(currentSpeedText);
        
        // Calculate percentage position for target speed
        double percentage = (targetSpeed - minSpeed) / (maxSpeed - minSpeed);
        
        // Get slider dimensions
        int sliderWidth = slider.getSize().getWidth();
        
        // Calculate drag distance based on percentage
        int dragDistance = (int)(percentage * sliderWidth);
        
        // Perform drag action to set the speed
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider)
               .moveByOffset(dragDistance, 0)
               .release()
               .perform();
        
        // Wait a moment for the speed to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Verify the new speed
        String newSpeedText = slider.getAttribute("aria-valuenow");
        System.out.println("✅ Speed adjusted to: " + newSpeedText);
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        System.out.println("💾 Save button clicked.");
    }

    public void clickStartCall() {
        WebElement startCall = wait.until(ExpectedConditions.elementToBeClickable(startCallButton));
        startCall.click();
        System.out.println("📞 Start Call button clicked successfully.");
        
        // Microphone permissions are now handled at browser level in BaseTest
        // No need to handle the popup manually
        System.out.println("✅ Call started - microphone permissions handled automatically by browser configuration.");
    }
} 