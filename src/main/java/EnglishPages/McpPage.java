package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class McpPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("(//button[normalize-space()='Create agent'])[1]");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    
    private By addMcpServerButton = By.xpath("(//button[normalize-space()='Add MCP Server'])[1]");
    private By createNewMcpButton = By.xpath("//span[normalize-space()='Create New']");
    private By nameInput = By.xpath("//div[@class='space-y-2']//input[@id='name']");
    private By descriptionInput = By.xpath("(//textarea[@id='description'])[1]");
    private By urlInput = By.xpath("(//input[@id='url'])[1]");
    private By addServerButton = By.xpath("(//button[normalize-space()='Add Server'])[1]");
    private By saveButton = By.xpath("//button[normalize-space()='Save']");
    private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");

    // ---------- Constructor ----------
    public McpPage(WebDriver driver) {
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

    public void scrollToAddMcpServer() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addMcpServerButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void addMcpServer(String name, String description, String url) {
        scrollToAddMcpServer();
        wait.until(ExpectedConditions.elementToBeClickable(addMcpServerButton)).click();
        
        // Wait for the modal/popup to fully load and be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(createNewMcpButton));
        
        // Use JavaScript click to avoid interception issues
        WebElement createNewButton = wait.until(ExpectedConditions.elementToBeClickable(createNewMcpButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createNewButton);
        
        // Wait for form fields to be visible and interactable
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlInput)).sendKeys(url);

        WebElement addServer = wait.until(ExpectedConditions.elementToBeClickable(addServerButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addServer);
        
        // Wait for the modal to close (either success or error)
        try {
            // In case of duplicate name, a toast appears. Detect and retry with a unique name
            By duplicateToast = By.xpath("//*[contains(text(),'Integration name already exists')]");
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            try {
                shortWait.until(ExpectedConditions.visibilityOfElementLocated(duplicateToast));
                System.out.println(" Duplicate name detected. Retrying with a unique name.");
                // Clear and enter a unique name
                WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
                nameField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                nameField.sendKeys(Keys.DELETE);
                String uniqueName = name + "-" + System.currentTimeMillis();
                nameField.sendKeys(uniqueName);
                // Click Add Server again
                addServer = wait.until(ExpectedConditions.elementToBeClickable(addServerButton));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addServer);
            } catch (TimeoutException ignored) {
                // No duplicate toast; continue normal flow
            }

            // Wait for modal to disappear - success case
            wait.until(ExpectedConditions.invisibilityOfElementLocated(createNewMcpButton));
            System.out.println(" MCP Server added: " + name + " - " + description);
        } catch (Exception e) {
            // If modal doesn't close automatically, try to close it manually
            try {
                By closeButton = By.xpath("//button[contains(@class, 'close') or contains(@aria-label, 'close') or .//svg[contains(@class, 'close')]]");
                WebElement closeBtn = driver.findElement(closeButton);
                closeBtn.click();
                System.out.println(" MCP Server creation had issues, but modal closed manually");
            } catch (Exception closeException) {
                // Force close with Escape key as last resort
                driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                System.out.println(" MCP Server creation had issues, modal closed with Escape key");
            }
        }

        // After adding MCP Server, click Save on the Agent page
        clickSaveButton();
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

    // ---------- Save actions ----------
    private void scrollToSaveButton() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void clickSaveButton() {
        scrollToSaveButton();
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        // Optionally wait for any unsaved changes banner to disappear
        try {
            By unsavedBanner = By.xpath("//*[contains(text(),'You have unsaved changes')]");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(unsavedBanner));
        } catch (Exception ignored) {
            // If banner is not present, ignore
        }
        System.out.println(" Save button clicked successfully.");
    }
}