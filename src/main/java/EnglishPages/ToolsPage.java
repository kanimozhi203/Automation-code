package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Navigation Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By createAgentButton = By.xpath("(//button[normalize-space()='Create agent'])[1]");
    private By agentNameInput = By.xpath("//input[@id='agent_name']");
    private By createButton = By.xpath("//button[contains(@type,'submit')]");
    
    // ---------- Tools Section Locators ----------
    private By addToolButton = By.xpath("(//button[normalize-space()='Add tool'])[1]");
    private By toolsSection = By.xpath("//body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[5]/div[2]");
    
    // ---------- End Call Tool Locators ----------
    private By endCallTool = By.xpath("(//div[normalize-space()='end call'])[1]");
    private By endCallNameInput = By.xpath("(//input[contains(@placeholder,'end_call')])[1]");
    private By endCallDescriptionInput = By.xpath("(//textarea[@placeholder='Enter Description'])[1]");
    private By addToolInEndCallButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Voicemail Detection Tool Locators ----------
    private By voicemailDetectionTool = By.xpath("(//div[normalize-space()='voicemail detection'])[1]");
    private By voicemailNameInput = By.xpath("(//input[contains(@placeholder,'voicemail_detection')])[1]");
    private By voicemailDescriptionInput = By.xpath("//textarea[@placeholder='Detects when the call has reached a voicemail']");
    // Voicemail Configuration (Predefined) locators
    private By voicemailConfigPredefinedTab = By.xpath("(//*[normalize-space()='Predefined'])[1]");
    private By voicemailPredefinedMessageTextarea = By.xpath("//textarea[@placeholder='Enter a fixed voicemail message. This will be used consistently for all voicemails.']");
    private By addToolInVoicemailButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Language Detection Tool Locators ----------
    private By languageDetectionTool = By.xpath("(//div[normalize-space()='language detection'])[1]");
    private By languageNameInput = By.xpath("(//input[contains(@placeholder,'Language Detector')])[1]");
    private By languageDescriptionInput = By.xpath("(//textarea[@placeholder='Detects the language being spoken in the conversation'])[1]");
    private By addToolInLanguageButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Press Digit Tool Locators ----------
    private By pressDigitTool = By.xpath("(//div[normalize-space()='press digit'])[1]");
    private By pressDigitNameInput = By.xpath("(//input[@placeholder='press_digit'])[1]");
    private By pressDigitDescriptionInput = By.xpath("(//textarea[@placeholder='Enter Description'])[1]");
    private By pauseDetectionDelayInput = By.xpath("(//input[@placeholder='1000'])[1]");
    private By addToolInPressDigitButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Webhook Tool Locators ----------
    private By webhookTool = By.xpath("(//div[normalize-space()='Webhook'])[1]");
    private By webhookNameInput = By.xpath("(//input[@placeholder='custom_tool'])[1]");
    private By webhookDescriptionInput = By.xpath("(//textarea[@placeholder='Enter webhook description'])[1]");
    private By webhookMethodDropdown = By.xpath("(//*[name()='svg'][@class='lucide lucide-chevron-down h-4 w-4 transition-transform duration-200'])[3]");
    private By webhookMethodPOST = By.xpath("//div[text()='POST']");
    private By webhookMethodGET = By.xpath("//div[text()='GET']");
    private By webhookMethodPUT = By.xpath("//div[text()='PUT']");
    private By webhookMethodDELETE = By.xpath("//div[text()='DELETE']");
    private By webhookUrlInput = By.xpath("(//input[@placeholder='https://api.example.com/webhook'])[1]");
    private By webhookTimeoutInput = By.xpath("(//input[@placeholder='30'])[1]");
    
    // Parameters section
    private By addParameterButton = By.xpath("(//button[@type='button'][normalize-space()='Add Parameter'])[1]");
    private By parameter1NameInput = By.xpath("(//input[@placeholder='parameter_name'])[1]");
    private By parameter1TypeDropdown = By.xpath("(//select[@class='w-full h-8 px-3 bg-background border border-input rounded-md text-sm'])[1]");
    private By parameter1RequiredCheckbox = By.xpath("(//input[@class='h-4 w-4'])[1]");
    private By parameter1DescriptionInput = By.xpath("(//input[@placeholder='Describe what this parameter does'])[1]");
    private By addValueButton1 = By.xpath("(//button[normalize-space()='Add Value'])[1]");
    private By parameter1ValueInput = By.xpath("(//input[@placeholder='allowed_value'])[1]");
    private By parameter2NameInput = By.xpath("(//input[@placeholder='parameter_name'])[2]");
    private By parameter2TypeDropdown = By.xpath("(//select)[2]");
    private By parameter2RequiredCheckbox = By.xpath("(//input[@type='checkbox'])[2]");
    private By parameter2DescriptionInput = By.xpath("(//input[@placeholder='Describe what this parameter does'])[2]");
    private By addValueButton2 = By.xpath("(//button[@type='button'][normalize-space()='Add Value'])[2]");
    private By parameter2ValueInput = By.xpath("(//input[@placeholder='allowed_value'])[2]");
    
    // Execution Settings
    private By speakDuringExecutionCheckbox = By.xpath("//div[@role='dialog']//label[.//span[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'speak during execution')]]//input[@type='checkbox']");
    private By speakAfterExecutionCheckbox = By.xpath("(//input[@type='checkbox'])[3]");
    
    // Headers section
    private By addHeaderButton = By.xpath("//button[normalize-space()='Add Header']");
    private By headerKeyInput = By.xpath("(//input[@placeholder='Header Key'])[1]");
    private By headerValueInput = By.xpath("(//input[@placeholder='Header Value'])[1]");
    
    // Query Parameters section
    private By addQueryParameterButton = By.xpath("//button[normalize-space()='Add Parameter' and ancestor::*[contains(text(), 'Query Parameters')]]");
    private By queryParamKeyInput = By.xpath("(//input[@placeholder='Parameter Key'])[1]");
    private By queryParamValueInput = By.xpath("(//input[@placeholder='Parameter Value'])[1]");
    
    private By webhookDialog = By.xpath("//div[@role='dialog' or @role='alertdialog']");
    private By addToolInWebhookButton = By.xpath("//div[@role='dialog']//button[normalize-space()='Add Tool']");
    
    // ---------- Transfer to AI Agent Tool Locators ----------
    private By transferToAIAgentTool = By.xpath("(//div[normalize-space()='Transfer to AI Agent'])[1]");
    private By transferToAINameInput = By.xpath("(//input[@placeholder='Enter tool name'])[1]");
    private By transferToAIDescriptionInput = By.xpath("(//textarea[@placeholder='Enter description'])[1]");
    private By preserveContextCheckbox = By.xpath("(//input[@type='checkbox'])[1]");
    private By addRuleButton = By.xpath("(//button[normalize-space()='Add Rule'])[1]");
    private By agentDropdown = By.xpath("(//button[@role='combobox'])[1]");
    private By conditionInput = By.xpath("(//textarea[@placeholder='Enter the condition for transferring to this agent'])[1]");
    private By addToolInTransferToAIButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl disabled:opacity-50 disabled:cursor-not-allowed\"])[1]");
    
    // ---------- Transfer to Human Tool Locators ----------
    private By transferToHumanTool = By.xpath("//div[contains(text(), 'Transfer to Human') or contains(text(), 'transfer to human')]");
    private By transferToHumanNameInput = By.xpath("(//input[@placeholder='Enter tool name'])[1]");
    private By transferToHumanDescriptionInput = By.xpath("(//textarea[@placeholder='Enter description'])[1]");
    private By destinationTypeDropdown = By.xpath("(//button[@role='combobox'])[1]");
    private By phoneNumberInput = By.xpath("(//input[@type='text'])[1]");
    private By transferTypeDropdown = By.xpath("(//button[@role='combobox'])[2]");
    private By addToolInTransferToHumanButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Check Calendar Availability Tool Locators ----------
    private By checkCalendarTool = By.xpath("//div[contains(text(), 'Check Calendar Availability') or contains(text(), 'check calendar availability') or contains(text(), 'Calendar Availability')]");
    private By checkCalendarNameInput = By.xpath("(//input[@placeholder='Check Calendar Availability'])[1]");
    private By checkCalendarDescriptionInput = By.xpath("(//textarea[@placeholder='Enter Description'])[1]");
    private By calApiKeyInput = By.xpath("(//input[@placeholder='Enter Cal.com API Key'])[1]");
    private By eventTypeIdInput = By.xpath("(//input[@placeholder='Enter Event Type ID'])[1]");
    private By timezoneInput = By.xpath("(//input[@placeholder='Timezone'])[1]");
    private By addToolInCheckCalendarButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Book on Calendar Tool Locators ----------
    private By bookOnCalendarTool = By.xpath("//div[contains(text(), 'Book on the Calendar') or contains(text(), 'book on the calendar') or contains(text(), 'Book Calendar')]");
    private By bookOnCalendarNameInput = By.xpath("(//input[@placeholder='Book_appointment'])[1]");
    private By bookOnCalendarDescriptionInput = By.xpath("(//textarea[@placeholder='Enter Description'])[1]");
    private By bookCalApiKeyInput = By.xpath("(//input[@placeholder='Enter Cal.com API Key'])[1]");
    private By bookEventTypeIdInput = By.xpath("(//input[@placeholder='Enter Event Type ID'])[1]");
    private By bookTimezoneInput = By.xpath("(//input[@placeholder='Timezone'])[1]");
    private By addToolInBookOnCalendarButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Send SMS Tool Locators ----------
    private By sendSMSTool = By.xpath("//div[contains(text(), 'Send SMS') or contains(text(), 'send sms') or contains(text(), 'SMS')]");
    private By sendSMSNameInput = By.xpath("(//input[@placeholder='send_sms'])[1]");
    private By sendSMSDescriptionInput = By.xpath("(//textarea[@placeholder='Enter Description'])[1]");
    private By contentTypeDropdown = By.xpath("(//*[name()='svg'][@class='lucide lucide-chevron-down h-4 w-4 transition-transform duration-200'])[3]");
    private By staticContentInput = By.xpath("(//textarea[@placeholder='e.g. Your appointment has been booked successfully.'])[1]");
    private By addToolInSendSMSButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Schedule Call Tool Locators ----------
    private By scheduleCallTool = By.xpath("(//div[normalize-space()='Schedule Call'])[1]");
    private By scheduleCallNameInput = By.xpath("(//input[@placeholder='schedule_call'])[1]");
    private By scheduleCallDescriptionInput = By.xpath("(//textarea[@placeholder='Tool to schedule follow-up calls'])[1]");
    private By addToolInScheduleCallButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
    
    // ---------- Start Call Button Locator ----------
    private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");
    
    // ---------- Save Button Locator ----------
    private By saveButton = By.xpath("//button[normalize-space()='Save' and not(@disabled)]");

    // ---------- Constructor ----------
    public ToolsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- Navigation Actions ----------
    public void createAgent(String agentName) {
        wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createAgentButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput)).sendKeys(agentName);
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        System.out.println(" Agent created: " + agentName);
        
        // Wait for agent creation to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void scrollToToolsSection() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(toolsSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        
        // Small delay to ensure scrolling is complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickAddTool() {
        scrollToToolsSection();
        
        // Wait for the button to be clickable and not intercepted
        WebElement addToolElement = wait.until(ExpectedConditions.elementToBeClickable(addToolButton));
        
        // Try to click normally first, if it fails, use JavaScript click
        try {
            addToolElement.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("️ Normal click failed, using JavaScript click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToolElement);
        }
        
        System.out.println(" Add Tool button clicked");
        
        // Wait for the tool selection modal to appear and list available tools
        try {
            Thread.sleep(2000); // Increased wait time
            listAvailableTools(); // Debug: list what tools are available
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Debug method to list available tools
    private void listAvailableTools() {
        try {
            // Look for tool options in the modal
            java.util.List<WebElement> toolOptions = driver.findElements(By.xpath("//div[contains(@class, 'cursor-pointer') or contains(@class, 'hover:bg-gray-50')]"));
            System.out.println(" Available tools in modal:");
            for (int i = 0; i < toolOptions.size(); i++) {
                WebElement tool = toolOptions.get(i);
                String toolText = tool.getText().trim();
                if (!toolText.isEmpty()) {
                    System.out.println("   " + (i + 1) + ". " + toolText);
                }
            }
        } catch (Exception e) {
            System.out.println(" Could not list available tools: " + e.getMessage());
        }
    }

    // ---------- End Call Tool Actions ----------
    public void addEndCallTool() {
        clickAddTool();
        wait.until(ExpectedConditions.elementToBeClickable(endCallTool)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToolInEndCallButton)).click();
        System.out.println(" End Call tool added");
        waitForToolAddition();
    }

    // ---------- Voicemail Detection Tool Actions ----------
    public void addVoicemailDetectionTool() {
        clickAddTool();
        wait.until(ExpectedConditions.elementToBeClickable(voicemailDetectionTool)).click();
        // Select Predefined configuration and enter the fixed voicemail message
        wait.until(ExpectedConditions.elementToBeClickable(voicemailConfigPredefinedTab)).click();
        String predefinedMsg = "Hi, this is [Your Company Name]. We tried reaching you but missed you today. Please give us a call back at [Your Phone Number] or visit our website at [Your Website]. Have a great day!";
        wait.until(ExpectedConditions.visibilityOfElementLocated(voicemailPredefinedMessageTextarea)).sendKeys(predefinedMsg);

        wait.until(ExpectedConditions.elementToBeClickable(addToolInVoicemailButton)).click();
        System.out.println(" Voicemail Detection tool added");
        waitForToolAddition();
    }

    // ---------- Language Detection Tool Actions ----------
    public void addLanguageDetectionTool() {
        clickAddTool();
        wait.until(ExpectedConditions.elementToBeClickable(languageDetectionTool)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToolInLanguageButton)).click();
        System.out.println(" Language Detection tool added");
        waitForToolAddition();
    }

    // ---------- Press Digit Tool Actions ----------
    public void addPressDigitTool() {
        clickAddTool();
        wait.until(ExpectedConditions.elementToBeClickable(pressDigitTool)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToolInPressDigitButton)).click();
        System.out.println(" Press Digit tool added");
        waitForToolAddition();
    }

    // ---------- Webhook Tool Actions ----------
    public void addWebhookTool(String description, String url) {
        clickAddTool();
        wait.until(ExpectedConditions.elementToBeClickable(webhookTool)).click();
        
        // Fill webhook details
        wait.until(ExpectedConditions.visibilityOfElementLocated(webhookDescriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(webhookUrlInput)).sendKeys(url);
        
        // Add first parameter (email)
        wait.until(ExpectedConditions.elementToBeClickable(addParameterButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter1NameInput)).sendKeys("email");
        wait.until(ExpectedConditions.elementToBeClickable(parameter1RequiredCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter1DescriptionInput)).sendKeys("Recipient email address");
        wait.until(ExpectedConditions.elementToBeClickable(addValueButton1)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter1ValueInput)).sendKeys("kanimozhi@zudu.ai");
        
        // Add second parameter (message)
        wait.until(ExpectedConditions.elementToBeClickable(addParameterButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter2NameInput)).sendKeys("message");
        wait.until(ExpectedConditions.elementToBeClickable(parameter2RequiredCheckbox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter2DescriptionInput)).sendKeys("The message content to send");
        wait.until(ExpectedConditions.elementToBeClickable(addValueButton2)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(parameter2ValueInput)).sendKeys("Hello from n8n");
        
        // Ensure modal content is scrolled to the bottom so the checkbox and button are interactable
        try {
            WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(webhookDialog));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", dialog);
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Select speak after execution and add the tool from inside the dialog
        wait.until(ExpectedConditions.elementToBeClickable(speakAfterExecutionCheckbox)).click();
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToolInWebhookButton));
        try {
            addBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        }
        System.out.println(" Webhook tool added");
        waitForToolAddition();
    }

    // ---------- Transfer to AI Agent Tool Actions ----------
    public void addTransferToAIAgentTool(String description, String condition) {
        clickAddTool();
        WebElement toolElement = findToolByPartialText("Transfer to AI Agent");
        toolElement.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferToAIDescriptionInput)).sendKeys(description);
        
        // Select an agent from the dropdown
        WebElement agentDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(agentDropdown));
        agentDropdownElement.click();
        
        // Wait for dropdown options to appear and select the first available agent
        try {
            Thread.sleep(1000); // Wait for dropdown to open
            // Try to find and select the first available agent option
            java.util.List<WebElement> agentOptions = driver.findElements(By.xpath("//div[@role='option' or contains(@class, 'option')]"));
            if (!agentOptions.isEmpty()) {
                agentOptions.get(0).click(); // Select first available agent
                System.out.println(" Agent selected from dropdown");
            } else {
                System.out.println(" No agent options found in dropdown");
            }
        } catch (Exception e) {
            System.out.println("️ Could not select agent from dropdown: " + e.getMessage());
        }
        
        // Enter the condition
        wait.until(ExpectedConditions.visibilityOfElementLocated(conditionInput)).sendKeys(condition);
        
        wait.until(ExpectedConditions.elementToBeClickable(addToolInTransferToAIButton)).click();
        System.out.println(" Transfer to AI Agent tool added");
        waitForToolAddition();
    }

    // ---------- Transfer to Human Tool Actions ----------
    public void addTransferToHumanTool(String description, String phoneNumber) {
        clickAddTool();
        WebElement toolElement = findToolByPartialText("Transfer to Human");
        toolElement.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferToHumanDescriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput)).sendKeys(phoneNumber);
        
        wait.until(ExpectedConditions.elementToBeClickable(addToolInTransferToHumanButton)).click();
        System.out.println(" Transfer to Human tool added");
        waitForToolAddition();
    }

    // ---------- Check Calendar Availability Tool Actions ----------
    public void addCheckCalendarTool(String description, String apiKey, String eventTypeId, String timezone) {
        clickAddTool();
        WebElement toolElement = findToolByPartialText("Check Calendar Availability");
        toolElement.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkCalendarDescriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(calApiKeyInput)).sendKeys(apiKey);
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventTypeIdInput)).sendKeys(eventTypeId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(timezoneInput)).sendKeys(timezone);
        
        wait.until(ExpectedConditions.elementToBeClickable(addToolInCheckCalendarButton)).click();
        System.out.println(" Check Calendar Availability tool added");
        waitForToolAddition();
    }

    // ---------- Book on Calendar Tool Actions ----------
    public void addBookOnCalendarTool(String description, String apiKey, String eventTypeId, String timezone) {
        clickAddTool();
        WebElement toolElement = findToolByPartialText("Book on the Calendar");
        toolElement.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookOnCalendarDescriptionInput)).sendKeys(description);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookCalApiKeyInput)).sendKeys(apiKey);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookEventTypeIdInput)).sendKeys(eventTypeId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookTimezoneInput)).sendKeys(timezone);
        
        wait.until(ExpectedConditions.elementToBeClickable(addToolInBookOnCalendarButton)).click();
        System.out.println(" Book on Calendar tool added");
        waitForToolAddition();
    }

    // ---------- Send SMS Tool Actions ----------
    public void addSendSMSTool(String staticContent) {
        clickAddTool();
        
        // Try multiple possible tool names since the available tools change
        WebElement toolElement = null;
        String[] possibleToolNames = {"Static Content", "Send SMS", "SMS", "send_sms"};
        
        for (String toolName : possibleToolNames) {
            try {
                toolElement = findToolByPartialText(toolName);
                if (toolElement != null) {
                    System.out.println(" Found tool with name: '" + toolName + "'");
                    break;
                }
            } catch (Exception e) {
                System.out.println(" Tool '" + toolName + "' not found, trying next...");
            }
        }
        
        if (toolElement == null) {
            throw new RuntimeException("Could not find Send SMS tool with any of the expected names");
        }
        
        toolElement.click();
        
        // Wait for the textarea to be visible and enter the static content
        wait.until(ExpectedConditions.visibilityOfElementLocated(staticContentInput)).sendKeys(staticContent);
        
        wait.until(ExpectedConditions.elementToBeClickable(addToolInSendSMSButton)).click();
        System.out.println(" Send SMS tool added");
        waitForToolAddition();
    }

    // ---------- Schedule Call Tool Actions ----------
    public void addScheduleCallTool() {
        clickAddTool();
        
        // Try multiple possible tool names since the available tools change
        WebElement toolElement = null;
        String[] possibleToolNames = {"Schedule Call", "Schedule", "schedule_call", "Call Schedule"};
        
        for (String toolName : possibleToolNames) {
            try {
                toolElement = findToolByPartialText(toolName);
                if (toolElement != null) {
                    System.out.println(" Found tool with name: '" + toolName + "'");
                    break;
                }
            } catch (Exception e) {
                System.out.println(" Tool '" + toolName + "' not found, trying next...");
            }
        }
        
        if (toolElement == null) {
            throw new RuntimeException("Could not find Schedule Call tool with any of the expected names");
        }
        
        toolElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(addToolInScheduleCallButton)).click();
        System.out.println(" Schedule Call tool added");
        waitForToolAddition();
    }

    // ---------- Start Call Action ----------
    public void scrollToStartCall() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(startCallButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        
        // Small delay to ensure scrolling is complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickStartCall() {
        scrollToStartCall();
        WebElement startCall = wait.until(ExpectedConditions.elementToBeClickable(startCallButton));
        
        // Try to click normally first, if it fails, use JavaScript click
        try {
            startCall.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("️ Normal click failed, using JavaScript click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", startCall);
        }
        
        System.out.println(" Start Call button clicked successfully.");
    }

    // ---------- Save Action ----------
    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        System.out.println(" Save button clicked.");
    }
    
    // ---------- Helper Methods ----------
    private void waitForToolAddition() {
        // Wait for tool addition to complete and page to stabilize
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Scroll back to tools section to ensure it's visible
        scrollToToolsSection();
    }
    
    // Flexible tool selection method
    private WebElement findToolByPartialText(String partialText) {
        try {
            System.out.println(" Searching for tool containing text: '" + partialText + "'");
            
            // Try multiple approaches to find the tool
            String[] xpathPatterns = {
                "//div[contains(text(), '" + partialText + "')]",
                "//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + partialText.toLowerCase() + "')]",
                "//*[contains(text(), '" + partialText + "')]"
            };
            
            for (String xpath : xpathPatterns) {
                try {
                    java.util.List<WebElement> elements = driver.findElements(By.xpath(xpath));
                    System.out.println("   Found " + elements.size() + " elements with xpath: " + xpath);
                    
                    for (WebElement element : elements) {
                        if (element.isDisplayed() && element.isEnabled()) {
                            String elementText = element.getText().trim();
                            if (!elementText.isEmpty()) {
                                System.out.println("   Found clickable tool: '" + elementText + "'");
                                return element;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("   XPath pattern failed: " + e.getMessage());
                }
            }
            
            throw new RuntimeException("Tool with text containing '" + partialText + "' not found");
        } catch (Exception e) {
            System.err.println(" Error finding tool with text containing '" + partialText + "': " + e.getMessage());
            throw e;
        }
    }
} 