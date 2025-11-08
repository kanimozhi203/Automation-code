package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnalysisPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("(//button[normalize-space()='Create agent'])[1]");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    private By addItemButton = By.xpath("(//button[normalize-space()='Add Item'])[1]");
    private By identifierInput = By.xpath("(//input[contains(@placeholder,'Enter Identifier')])[1]");
    private By descriptionInput = By.xpath("(//textarea[contains(@placeholder,'Enter a description of what data should be extracted')])[1]");
    private By confirmAddItemButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 px-4 py-2 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90\"])[1]");
    private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");
    private By saveButton = By.xpath("//button[normalize-space()='Save' and not(@disabled)]");

    // ---------- Constructor ----------
    public AnalysisPage(WebDriver driver) {
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

    public void scrollToAddItem() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addItemButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void addAnalysisItem(String identifier, String description) {
        scrollToAddItem();
        wait.until(ExpectedConditions.elementToBeClickable(addItemButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifierInput)).sendKeys(identifier);
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionInput)).sendKeys(description);

        // Trigger blur so UI marks unsaved changes
        driver.findElement(descriptionInput).sendKeys(Keys.TAB);

        wait.until(ExpectedConditions.elementToBeClickable(confirmAddItemButton)).click();
        System.out.println(" Analysis Item added: " + identifier + " - " + description);

        // Immediately Save after adding item
        clickSave();
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        System.out.println("Save button clicked.");
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
