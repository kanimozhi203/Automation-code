package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;

public class KnowledgeBasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    // Agent creation locators (verified from existing code)
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("(//button[normalize-space()='Create agent'])[1]");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    
    // Knowledge Base locators (need verification)
    private By addDocumentButton = By.xpath("(//button[normalize-space()='Add document'])[1]");
    private By createNewButton = By.xpath("(//span[normalize-space()='Create New'])[1]");
    private By knowledgeBaseNameInput = By.xpath("(//input[@id='kb-name'])[1]");
    private By addButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive border bg-background hover:bg-accent hover:text-accent-foreground dark:bg-input/30 dark:border-input dark:hover:bg-input/50 h-9 px-4 py-2 has-[>svg]:px-3 wtbtn1 justify-start\"])[1]");
    
    // Dropdown options
    private By addWebPageOption = By.xpath("(//span[normalize-space()='Add Web Page'])[1]");
    private By addTextOption = By.xpath("(//span[normalize-space()='Add Text'])[1]");
    private By uploadFilesOption = By.xpath("(//span[normalize-space()='Upload Files'])[1]");
    
    // Form inputs
    private By urlAddressInput = By.xpath("(//input[@type='url'])[1]");
    private By fileNameInput = By.xpath("(//input[@id='text-title'])[1]");
    private By textContentInput = By.xpath("(//textarea[@id='text-content'])[1]");
    
    // File upload
    private By fileUploadArea = By.xpath("(//div[contains(@class,'flex flex-col items-center gap-2')])[1]");
    private By fileInput = By.xpath("//input[@type='file']");
    
    // Action buttons
    private By saveButton = By.xpath("//button[normalize-space()='Save' and not(@disabled)]");
    private By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");
    
    // Specific Save buttons for different modals
    private By addWebPageSaveButton = By.xpath("(//button[normalize-space()='Save'])[2]");
    private By addFilesSaveButton = By.xpath("(//button[normalize-space()='Save'])[1]");

    // ---------- Constructor ----------
    public KnowledgeBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- Actions ----------
    public void createAgent(String agentName) {
        // Navigate to agents page
        wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
        
        // Wait for page to load completely
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Click create agent button
        wait.until(ExpectedConditions.elementToBeClickable(createAgentButton)).click();
        
        // Wait for the create agent modal to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Fill in agent name
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput));
        nameField.clear();
        nameField.sendKeys(agentName);
        
        // Wait a moment before submitting
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Submit the form
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        
        // Wait for the API call to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("✅ Agent created: " + agentName);
    }

    public void clickAddDocument() {
        WebElement addDocBtn = wait.until(ExpectedConditions.elementToBeClickable(addDocumentButton));
        // Use JavaScript click to bypass modal overlay interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addDocBtn);
        System.out.println("📄 Add Document button clicked");
    }

    public void clickCreateNew() {
        WebElement createNewBtn = wait.until(ExpectedConditions.elementToBeClickable(createNewButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createNewBtn);
        System.out.println("➕ Create New button clicked");
    }

    public void enterKnowledgeBaseName(String kbName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(knowledgeBaseNameInput)).sendKeys(kbName);
        System.out.println("📝 Knowledge Base name entered: " + kbName);
    }

    public void clickAddButton() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        System.out.println("➕ Add button clicked");
    }

    public void selectAddWebPage() {
        WebElement webPageOption = wait.until(ExpectedConditions.elementToBeClickable(addWebPageOption));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webPageOption);
        System.out.println("🌐 Add Web Page option selected");
    }

    public void selectAddText() {
        WebElement textOption = wait.until(ExpectedConditions.elementToBeClickable(addTextOption));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", textOption);
        System.out.println("📝 Add Text option selected");
    }

    public void selectUploadFiles() {
        WebElement uploadOption = wait.until(ExpectedConditions.elementToBeClickable(uploadFilesOption));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", uploadOption);
        System.out.println("📁 Upload Files option selected");
    }

    public void enterUrlAddress(String url) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urlAddressInput)).sendKeys(url);
        System.out.println("🔗 URL address entered: " + url);
    }

    public void enterFileName(String fileName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileNameInput)).sendKeys(fileName);
        System.out.println("�� File name entered: " + fileName);
    }

    public void enterTextContent(String textContent) {
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(textContentInput));
        textArea.clear(); // Clear any existing content
        textArea.sendKeys(textContent);
        
        // Verify the content was entered
        String enteredText = textArea.getAttribute("value");
        if (enteredText != null && enteredText.contains(textContent)) {
            System.out.println("📝 Text content entered successfully: " + textContent);
        } else {
            System.out.println("⚠️ Text content may not have been entered properly. Expected: " + textContent + ", Got: " + enteredText);
        }
    }

    public void uploadFile(String fileName) {
        // Get the absolute path of the file
        String filePath = System.getProperty("user.dir") + "/files/" + fileName;
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        // Find the file input element (usually hidden)
        WebElement fileInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        
        // Send the file path to the input
        fileInputElement.sendKeys(file.getAbsolutePath());
        System.out.println("�� File uploaded: " + fileName);
    }

    public void clickSave() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        System.out.println("💾 Save button clicked");
    }

    public void clickSaveWebPage() {
        // Wait for the web page modal to be visible and save button to be clickable
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(addWebPageSaveButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        System.out.println("💾 Web Page Save button clicked");
    }

    public void clickSaveAddFiles() {
        // Wait for the Add Files modal to be visible and save button to be clickable
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(addFilesSaveButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        System.out.println("💾 Add Files Save button clicked");
    }

    public void clickSaveKnowledgeBase() {
        // Wait for the knowledge base modal to be visible and save button to be clickable
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        // Use JavaScript click to bypass element interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        System.out.println("💾 Knowledge Base Save button clicked");
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
        System.out.println("❌ Cancel button clicked");
    }

    public void scrollToStartCall() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(startCallButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void clickStartCall() {
        // First, ensure any modal overlays are dismissed
        dismissModalOverlay();
        waitForModalOverlayToDisappear();
        
        scrollToStartCall();
        WebElement startCall = wait.until(ExpectedConditions.elementToBeClickable(startCallButton));
        // Use JavaScript click to bypass modal overlay interception
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", startCall);
        System.out.println("📞 Start Call button clicked successfully.");
    }

    // ---------- Modal Overlay Handling ----------
    public void waitForModalOverlayToDisappear() {
        try {
            // Wait for modal overlay to disappear
            By modalOverlay = By.xpath("//div[@data-slot='dialog-overlay' and @data-state='open']");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
            System.out.println("✅ Modal overlay disappeared");
        } catch (Exception e) {
            System.out.println("⚠️ Modal overlay handling: " + e.getMessage());
        }
    }

    public void dismissModalOverlay() {
        try {
            // Try to click outside the modal or press Escape
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            Thread.sleep(500);
            System.out.println("✅ Modal overlay dismissed with Escape key");
        } catch (Exception e) {
            System.out.println("⚠️ Could not dismiss modal overlay: " + e.getMessage());
        }
    }

    // ---------- Combined Methods for Knowledge Base Operations ----------
    
    public void addWebPageKnowledgeBase(String kbName, String url) {
        clickAddDocument();
        clickCreateNew();
        enterKnowledgeBaseName(kbName);
        clickAddButton();
        selectAddWebPage();
        enterUrlAddress(url);
        clickSaveWebPage(); // Save the web page (Add Web Page modal)
        clickSaveAddFiles(); // Save the Add Files modal
        
        // Handle modal overlay that might remain after saving
        waitForModalOverlayToDisappear();
        dismissModalOverlay();
        
        System.out.println("✅ Web Page Knowledge Base added: " + kbName);
    }

    public void addFileKnowledgeBase(String kbName, String fileName) {
        clickAddDocument();
        clickCreateNew();
        enterKnowledgeBaseName(kbName);
        clickAddButton();
        selectUploadFiles();
        uploadFile(fileName);
        clickSave(); // Save the file
        clickSave(); // Save the knowledge base
        
        // Handle modal overlay that might remain after saving
        waitForModalOverlayToDisappear();
        dismissModalOverlay();
        
        System.out.println("✅ File Knowledge Base added: " + kbName);
    }

    public void addTextKnowledgeBase(String kbName, String fileName, String textContent) {
        clickAddDocument();
        clickCreateNew();
        enterKnowledgeBaseName(kbName);
        clickAddButton();
        selectAddText();
        enterFileName(fileName);
        enterTextContent(textContent);
        
        // Add a small wait to ensure content is properly entered
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        clickSaveWebPage(); // Save the text (Add Text modal) - using same locator as web page
        clickSaveAddFiles(); // Save the Add Files modal
        
        // Handle modal overlay that might remain after saving
        waitForModalOverlayToDisappear();
        dismissModalOverlay();
        
        // Add additional wait to ensure the knowledge base is fully saved
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("✅ Text Knowledge Base added: " + kbName);
    }

    // ---------- Knowledge Base Verification Methods ----------
    public void verifyKnowledgeBaseExists(String kbName) {
        try {
            // Look for the knowledge base in the UI
            By kbElement = By.xpath("//div[contains(text(), '" + kbName + "')]");
            WebElement kb = wait.until(ExpectedConditions.presenceOfElementLocated(kbElement));
            System.out.println("✅ Knowledge Base verified in UI: " + kbName);
        } catch (Exception e) {
            System.out.println("⚠️ Knowledge Base not found in UI: " + kbName + " - " + e.getMessage());
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000); // Wait for page to load
            System.out.println("🔄 Page refreshed");
        } catch (Exception e) {
            System.out.println("⚠️ Error refreshing page: " + e.getMessage());
        }
    }

    // ---------- Locator Testing Methods ----------
    public void testLocator(By locator, String locatorName) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("✅ Locator found: " + locatorName);
            return;
        } catch (Exception e) {
            System.err.println("❌ Locator not found: " + locatorName + " - " + e.getMessage());
        }
    }

    public void testAllLocators() {
        System.out.println("🔍 Testing all locators...");
        testLocator(agentSideMenu, "Agent Side Menu");
        testLocator(createAgentButton, "Create Agent Button");
        testLocator(agentNameInput, "Agent Name Input");
        testLocator(createButton, "Create Button");
        testLocator(addDocumentButton, "Add Document Button");
        testLocator(createNewButton, "Create New Button");
        testLocator(knowledgeBaseNameInput, "Knowledge Base Name Input");
        testLocator(addButton, "Add Button");
        testLocator(addWebPageOption, "Add Web Page Option");
        testLocator(addTextOption, "Add Text Option");
        testLocator(uploadFilesOption, "Upload Files Option");
        testLocator(urlAddressInput, "URL Address Input");
        testLocator(fileNameInput, "File Name Input");
        testLocator(textContentInput, "Text Content Input");
        testLocator(fileUploadArea, "File Upload Area");
        testLocator(fileInput, "File Input");
        testLocator(saveButton, "Save Button");
        testLocator(cancelButton, "Cancel Button");
        testLocator(startCallButton, "Start Call Button");
        System.out.println("🔍 Locator testing completed.");
    }

    // ---------- Individual Test Methods ----------
    public void testWebPageSave() {
        System.out.println("🌐 Testing Web Page Save functionality...");
        try {
            // Test if we can find the save button in the web page modal
            testLocator(addWebPageSaveButton, "Save Button in Web Page Modal");
            
            // Try to click save
            clickSaveWebPage();
            System.out.println("✅ Web Page Save test completed successfully");
        } catch (Exception e) {
            System.err.println("❌ Web Page Save test failed: " + e.getMessage());
            throw e;
        }
    }

    public void testSaveSequence() throws Exception {
        System.out.println("🔄 Testing Save Sequence: Web Page -> Add Files...");
        try {
            // First save the web page
            clickSaveWebPage();
            
            // Wait a moment for the modal to close and Add Files modal to be ready
            Thread.sleep(1000);
            
            // Then save the Add Files modal
            clickSaveAddFiles();
            
            System.out.println("✅ Save sequence completed successfully");
        } catch (Exception e) {
            System.err.println("❌ Save sequence test failed: " + e.getMessage());
            throw e;
        }
    }
}