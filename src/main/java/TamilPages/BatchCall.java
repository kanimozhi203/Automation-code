package TamilPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BatchCall {
	
	private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
    private By outboundCallSideMenu = By.xpath("//span[normalize-space()='Outbound Call']");
    private By createAgentButton = By.xpath("//button[normalize-space()='Create agent']");
    private By blankTemplateCard = By.xpath("//div[contains(text(), 'Start with a blank template')]//following-sibling::button");
    private By blankTemplateCardAlt = By.xpath("/html/body/div[1]/main/div[2]/div/div/div/div[3]/div[1]/div/div/button");
    private By agentNameInput = By.xpath("//input[@placeholder='Enter agent name']");
    private By createButton = By.xpath("//button[normalize-space()='Create Agent']");
    
    // ---------- Batch Call Locators ----------
    private By createBatchCallButton = By.xpath("//button[contains(text(), '+ Create Batch Call') or contains(text(), 'Create Batch Call')]");
    private By batchCallNameInput = By.xpath("//input[@placeholder='Enter batch call name']");
    private By fromNumberDropdown = By.xpath("(//label[contains(text(), 'From Number')]/following-sibling::div//button | //label[contains(text(), 'From Number')]/following-sibling::div//div[@role='button'] | //label[contains(text(), 'From Number')]/following-sibling::div//div[contains(@class, 'select')])[1]");
    private By selectAgentDropdown = By.xpath("(//label[contains(text(), 'Select Agent')]/following-sibling::div//button | //label[contains(text(), 'Select Agent')]/following-sibling::div//div[@role='button'] | //label[contains(text(), 'Select Agent')]/following-sibling::div//div[contains(@class, 'select')])[1]");
    private By sendNowRadioButton = By.xpath("(//label[contains(text(), 'Send Now')] | //input[@type='radio' and following-sibling::label[contains(text(), 'Send Now')]]/following-sibling::label | //span[contains(text(), 'Send Now')]/ancestor::label | //label[contains(., 'Send Now')])[1]");
    private By createBatchCallSubmitButton = By.xpath("//button[normalize-space()='Create Batch Call' and not(contains(text(), '+'))]");

    // ---------- Constructor ----------
    public BatchCall(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- Actions ----------
    public void navigateToDashboard() {
        // This method can be used if additional navigation is needed
        System.out.println(" Navigated to dashboard");
    }

    public void clickAgentFromSideMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
        System.out.println(" Clicked 'Agents' from side menu");
    }

    public void clickCreateAgentOrBlankTemplate() {
        try {
            Thread.sleep(2000);
            
            // First try to find and click "Create agent" button
            WebElement createAgentBtn = wait.until(ExpectedConditions.elementToBeClickable(createAgentButton));
            createAgentBtn.click();
            System.out.println(" Clicked 'Create agent' button");
            
            // Wait for the create agent modal to appear
            Thread.sleep(1000);
            
        } catch (Exception e) {
            try {
                // If "Create agent" button not found, try clicking Blank Template card (first path)
                WebElement blankTemplateBtn = wait.until(ExpectedConditions.elementToBeClickable(blankTemplateCard));
                blankTemplateBtn.click();
                System.out.println("Clicked 'Blank Template' card (first path)");
                
                // Wait for the create agent modal to appear
                Thread.sleep(1000);
                
            } catch (Exception e2) {
                try {
                    // If first Blank Template path fails, try alternative path
                    WebElement blankTemplateBtnAlt = wait.until(ExpectedConditions.elementToBeClickable(blankTemplateCardAlt));
                    blankTemplateBtnAlt.click();
                    System.out.println(" Clicked 'Blank Template' card (alternative path)");
                    
                    // Wait for the create agent modal to appear
                    Thread.sleep(1000);
                    
                } catch (Exception e3) {
                    System.out.println(" Neither 'Create agent' button nor any 'Blank Template' card found");
                    throw new RuntimeException("Unable to find agent creation option", e3);
                }
            }
        }
    }

    public void enterAgentName(String agentName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput)).sendKeys(agentName);
        System.out.println(" Entered agent name: " + agentName);
    }

    public void clickCreateAgentSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        System.out.println(" Clicked 'Create Agent' submit button");
    }

    // ---------- Complete Flow Method ----------
    public void createTamilAgent() {
        navigateToDashboard();
        clickAgentFromSideMenu();
        clickCreateAgentOrBlankTemplate();
        enterAgentName("batch call");
        clickCreateAgentSubmitButton();
        System.out.println(" Tamil Agent created successfully!");
    }
    
    
    // ---------- Agent Configuration Methods ----------
    public void configureTamilAgent() {
        // Configure agent language to Tamil
        configureAgentLanguage();
        
        // Configure agent voice to Kani
        configureAgentVoice();
        
        // Configure first message
        configureFirstMessage();
        
        // Configure system prompt
        configureSystemPrompt();
        
        // Configure dynamic variables
        configureDynamicVariables();
        
        // Configure tools
        configureTools();
        
        // Save the changes
        saveAgentConfiguration();
        
        // Click Outbound Call from side menu and navigate to batch call page
        clickOutboundCallFromSideMenu();
        
        // Navigate to batch call page
        navigateToBatchCallPage();
       
    }
    
    private void configureAgentLanguage() {
        try {
            Thread.sleep(2000);
            
            // Click language dropdown
            By languageDropdown = By.xpath("//div[@class='flex items-center gap-2 w-full']");
            wait.until(ExpectedConditions.elementToBeClickable(languageDropdown)).click();
            
            Thread.sleep(1000);
            
            // Search for Tamil
            By searchInput = By.xpath("(//input[@type='text'])[1]");
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            searchField.sendKeys("Tamil");
            
            Thread.sleep(1000);
            
            // Click Tamil option
            By tamilOption = By.xpath("//div[@data-slot='command-item'][1]");
            wait.until(ExpectedConditions.elementToBeClickable(tamilOption)).click();
            
            System.out.println(" Language set to Tamil");
            
        } catch (Exception e) {
            System.out.println(" Language configuration failed: " + e.getMessage());
        }
    }
    
    private void configureAgentVoice() {
        try {
            Thread.sleep(2000);
            
            // Click voice field/button to open voice selection dialog
            By voiceField = By.xpath("(//*[name()='svg'][@class='lucide lucide-plus h-4 w-4'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(voiceField)).click();
            
            Thread.sleep(1000);
            
            // Search for Kani in the voice search field
            By voiceSearchInput = By.xpath("(//input[@placeholder='Search Voices'])[1]");
            WebElement voiceSearchField = wait.until(ExpectedConditions.visibilityOfElementLocated(voiceSearchInput));
            voiceSearchField.sendKeys("Kani");
            
            Thread.sleep(1000);
            
            // Click on the Kani voice option from the list
            By kaniVoiceOption = By.xpath("(//div[@class='font-medium voice-name'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(kaniVoiceOption)).click();
            System.out.println(" Clicked on Kani voice option");
            
            Thread.sleep(500);
            
            // Click the "Use Voice" button
            By useVoiceButton = By.xpath("(//button[normalize-space()='Use Voice'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(useVoiceButton)).click();
            System.out.println(" Clicked 'Use Voice' button for Kani");
            
            Thread.sleep(1000);
            
            System.out.println(" Voice set to Kani");
            
        } catch (Exception e) {
            System.out.println(" Voice configuration failed: " + e.getMessage());
        }
    }
    
    private void configureFirstMessage() {
        try {
            Thread.sleep(2000);
            
            // Find and click on the first message input field
            By firstMessageInput = By.xpath("(//textarea[@id='firstMessage'])[1]");
            WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstMessageInput));
            
            // Clear the existing text
            messageField.clear();
            System.out.println(" Cleared default message");
            
            Thread.sleep(500);
            
            // Enter the new Tamil message with variables
            String tamilMessage = "Hello {{patient_name}} {{address}}, நா நித்யா, Aarthi Scans ல இருந்து call பண்றேன்.";
            messageField.sendKeys(tamilMessage);
            System.out.println(" Entered Tamil message with variables");
            
            Thread.sleep(1000);
            
            System.out.println(" First message configured successfully");
            
        } catch (Exception e) {
            System.out.println(" First message configuration failed: " + e.getMessage());
        }
    }
    
    private void configureSystemPrompt() {
        try {
            Thread.sleep(2000);
            
            // Find the system prompt textarea
            By systemPromptInput = By.xpath("(//textarea[@id='systemPrompt'])[1]");
            WebElement systemPromptField = wait.until(ExpectedConditions.visibilityOfElementLocated(systemPromptInput));
            
            // Clear the existing system prompt
            systemPromptField.clear();
            System.out.println(" Cleared default system prompt");
            
            Thread.sleep(500);
            
            // Enter the detailed Tamil agent system prompt
            StringBuilder systemPromptBuilder = new StringBuilder();
            systemPromptBuilder.append("Role & Tone :\n");
            systemPromptBuilder.append("You are நித்யா, a polite and professional outgoing voice agent from Aarthi Scans and Labs. ");
            systemPromptBuilder.append("Call patients who missed their scheduled scan appointments. ");
            systemPromptBuilder.append("Speak clearly, calmly, and empathetically, addressing them respectfully as \"{{address}}\" (Sir / Ma'am). ");
            systemPromptBuilder.append("Use Tanglish — mostly Tamil with key words like appointment, scan, verification in English. ");
            systemPromptBuilder.append("Never speak fully in English or in any other language.\n\n");
            systemPromptBuilder.append("Objective :\n");
            systemPromptBuilder.append("* Confirm if the patient attended {{scan_type}} on {{scan_date}}.\n");
            systemPromptBuilder.append("* If not, help reschedule using available tools.\n");
            systemPromptBuilder.append("* Maintain courteous closure.\n");
            systemPromptBuilder.append("* Do not answer medical, technical, or unrelated questions.\n\n");
            systemPromptBuilder.append("Dynamic Variables : \n");
            systemPromptBuilder.append("{{patient_name}}, {{address}}, {{scan_type}}, {{scan_part}}, {{scan_date}}, {{email_id}}\n\n");
            systemPromptBuilder.append("Tools\n");
            systemPromptBuilder.append("* Check_Calendar_Availability → to check open slots\n");
            systemPromptBuilder.append("* Book_appointment → to book or reschedule\n");
            systemPromptBuilder.append("* Timezone: Asia/Kolkata\n\n");
            systemPromptBuilder.append("Instructions :\n");
            systemPromptBuilder.append("1. Greet the patient using {{patient_name}}.\n");
            systemPromptBuilder.append("2. Confirm attendance:\n");
            systemPromptBuilder.append("     a. If Yes – Apologize for system error → confirm → close politely.\n");
            systemPromptBuilder.append("     b. If No – Offer to reschedule → get convenient date/time → check availability → book →       confirm.\n");
            systemPromptBuilder.append("     c. If Declined – Close politely.\n");
            systemPromptBuilder.append("3. Always confirm the new date and time (காலை 9 – மாலை 6).\n\n");
            systemPromptBuilder.append("Date & Time Pronunciation :\n");
            systemPromptBuilder.append("* Use dd/mm/yyyy format (e.g., அக்டோபர் ஐந்தாம் தேதி).\n");
            systemPromptBuilder.append("* Time ranges: காலை 6–11:59, மதியம் 12–3:59, மாலை 4–6:59.\n");
            systemPromptBuilder.append("    Examples: 02:15 → ரெண்டே கால், 06:30 → ஆறரை\n\n");
            systemPromptBuilder.append("Call Flow Examples :\n");
            systemPromptBuilder.append("Confirmation:\n");
            systemPromptBuilder.append("\"உங்களுக்கு {{scan_part}}க்கு {{scan_type}}, {{scan_date}} ஆம் தேதி appointment இருந்தது. அந்த scan வந்தீங்களா?\"\n\n");
            systemPromptBuilder.append("If Yes:\n");
            systemPromptBuilder.append("\"Confirm பண்ணதுக்கு thank you, {{address}}. record update ஆகலைன்னு நினைக்கிறன். மறுபடியும் confirm பண்ணிக்க, நீங்க {{scan_date}} ல {{scan_type}} complete பண்ணிட்டீங்க, correct ஆ?\"\n\n");
            systemPromptBuilder.append("If No:\n");
            systemPromptBuilder.append("\"No problem, {{address}}. உங்க {{scan_type}}- reschedule பண்ண help பண்ணவா?\" → get date/time → check slot → book → confirm\n\n");
            systemPromptBuilder.append("Notes :\n");
            systemPromptBuilder.append("Speak all numbers digit by digit.\n");
            systemPromptBuilder.append("Always use the provided {{email_id}} without asking.");
            
            String systemPrompt = systemPromptBuilder.toString();
            systemPromptField.sendKeys(systemPrompt);
            System.out.println(" Entered detailed Tamil agent system prompt");
            
            Thread.sleep(1000);
            
            System.out.println(" System prompt configured successfully");
            
        } catch (Exception e) {
            System.out.println(" System prompt configuration failed: " + e.getMessage());
        }
    }
    
    private void configureDynamicVariables() {
        try {
            Thread.sleep(2000);
            
            // Enter patient_name
            By patientNameInput = By.xpath("(//input[@placeholder='Enter value for patient_name'])[1]");
            WebElement patientNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(patientNameInput));
            patientNameField.clear();
            patientNameField.sendKeys("kani");
            System.out.println(" Entered patient_name: kani");
            
            Thread.sleep(500);
            
            // Enter address
            By addressInput = By.xpath("(//input[@placeholder='Enter value for address'])[1]");
            WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
            addressField.clear();
            addressField.sendKeys("mam");
            System.out.println(" Entered address: mam");
            
            Thread.sleep(500);
            
            // Enter scan_type
            By scanTypeInput = By.xpath("(//input[@placeholder='Enter value for scan_type'])[1]");
            WebElement scanTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(scanTypeInput));
            scanTypeField.clear();
            scanTypeField.sendKeys("CT Scan");
            System.out.println(" Entered scan_type: CT Scan");
            
            Thread.sleep(500);
            
            // Enter scan_date
            By scanDateInput = By.xpath("(//input[@placeholder='Enter value for scan_date'])[1]");
            WebElement scanDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(scanDateInput));
            scanDateField.clear();
            scanDateField.sendKeys("25/10/2025");
            System.out.println(" Entered scan_date: 25/10/2025");
            
            Thread.sleep(500);
            
            // Enter scan_part
            By scanPartInput = By.xpath("(//input[@placeholder='Enter value for scan_part'])[1]");
            WebElement scanPartField = wait.until(ExpectedConditions.visibilityOfElementLocated(scanPartInput));
            scanPartField.clear();
            scanPartField.sendKeys("shoulder");
            System.out.println(" Entered scan_part: shoulder");
            
            Thread.sleep(500);
         
        } catch (Exception e) {
            System.out.println(" Dynamic variables configuration failed: " + e.getMessage());
        }
    }
    
    private void configureTools() {
        try {
            Thread.sleep(2000);
            
            // Scroll to the tools section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement toolsSection = driver.findElement(By.xpath("//button[contains(text(), 'Add tool')]"));
            js.executeScript("arguments[0].scrollIntoView(true);", toolsSection);
            
            Thread.sleep(1000);
            
            // Click "Add tool" button
            By addToolButton = By.xpath("//button[contains(text(), 'Add tool')]");
            wait.until(ExpectedConditions.elementToBeClickable(addToolButton)).click();
            System.out.println(" Clicked 'Add tool' button");
            
            Thread.sleep(1000);
            
            // Click "end call" option from the dropdown
            By endCallOption = By.xpath("//div[normalize-space()='end call']");
            wait.until(ExpectedConditions.elementToBeClickable(endCallOption)).click();
            System.out.println(" Selected 'end call' tool");
            
            Thread.sleep(1000);
            
            // Click "Add tool" button in the dialog to confirm
            By addToolDialogButton = By.xpath("(//button[@class=\"inline-flex items-center cursor-pointer justify-center gap-2 whitespace-nowrap text-sm font-medium transition-all disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4 shrink-0 [&_svg]:shrink-0 outline-none focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:ring-[3px] aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive h-9 has-[>svg]:px-3 wtbtn1 bg-black text-white hover:bg-black/90 px-6 py-2 rounded-xl\"])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(addToolDialogButton)).click();
            System.out.println(" Added 'end call' tool successfully");
            
            Thread.sleep(1000);
            
            System.out.println(" Tools configured successfully");
            
        } catch (Exception e) {
            System.out.println(" Tools configuration failed: " + e.getMessage());
        }
    }
    
    private void saveAgentConfiguration() {
        try {
            Thread.sleep(2000);
            
            // Click Save button
            By saveButton = By.xpath("//button[contains(text(), 'Save')]");
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            
            System.out.println(" Configuration saved");
            
        } catch (Exception e) {
            System.out.println(" Save failed: " + e.getMessage());
        }
    }
    
    // ---------- Outbound Call Methods ----------
    public void clickOutboundCallFromSideMenu() {
        try {
            Thread.sleep(2000);
            
            // Click on "Outbound Call" from side menu
            wait.until(ExpectedConditions.elementToBeClickable(outboundCallSideMenu)).click();
            System.out.println(" Clicked 'Outbound Call' from side menu");
            
        } catch (Exception e) {
            System.out.println(" Failed to click 'Outbound Call': " + e.getMessage());
            throw new RuntimeException("Failed to click 'Outbound Call' from side menu", e);
        }
    }
    
    // ---------- Batch Call Navigation Method ----------
    private void navigateToBatchCallPage() {
        try {
            Thread.sleep(2000);
            
            System.out.println(" Navigating to batch call page...");
            
            // Wait for the page to load after clicking Outbound Call
            Thread.sleep(1000);
            
            System.out.println(" Successfully navigated to batch call page");
            
        } catch (Exception e) {
            System.out.println(" Navigation to batch call page failed: " + e.getMessage());
        }
    }
    
    // ---------- Batch Call Creation Methods ----------
    public void clickCreateBatchCallButton() {
        try {
            Thread.sleep(2000);
            
            // Wait for and click the "+ Create Batch Call" button
            wait.until(ExpectedConditions.elementToBeClickable(createBatchCallButton)).click();
            System.out.println(" Clicked '+ Create Batch Call' button");
            
            Thread.sleep(1000);
            
        } catch (Exception e) {
            System.out.println(" Failed to click 'Create Batch Call' button: " + e.getMessage());
            throw new RuntimeException("Failed to click 'Create Batch Call' button", e);
        }
    }
    
    public void enterBatchCallName(String batchCallName) {
        try {
            Thread.sleep(1000);
            
            // Enter batch call name
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(batchCallNameInput));
            nameField.clear();
            nameField.sendKeys(batchCallName);
            System.out.println(" Entered batch call name: " + batchCallName);
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println(" Failed to enter batch call name: " + e.getMessage());
            throw new RuntimeException("Failed to enter batch call name", e);
        }
    }
    
    public void selectFromNumber() {
        try {
            Thread.sleep(1000);
            
            // Click on "From Number" dropdown
            WebElement fromNumberDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(fromNumberDropdown));
            fromNumberDropdownElement.click();
            System.out.println(" Clicked 'From Number' dropdown");
            
            Thread.sleep(1500);
            
            // Select the first available number from the dropdown
            // Try multiple common locators for dropdown options
            By firstNumberOption = By.xpath("(//div[@role='option'] | //li[@role='option'] | //div[contains(@class, 'option')] | //div[contains(@class, 'item')] | //li[contains(@class, 'item')])[1]");
            
            try {
                WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firstNumberOption));
                firstOption.click();
                System.out.println(" Selected first available number from dropdown");
            } catch (Exception e1) {
                // Try alternative approach - use keyboard navigation or click by index
                try {
                    // Try pressing Enter or clicking the first visible option
                    fromNumberDropdownElement.sendKeys(Keys.ARROW_DOWN);
                    Thread.sleep(300);
                    fromNumberDropdownElement.sendKeys(Keys.ENTER);
                    System.out.println(" Selected number using keyboard navigation");
                } catch (Exception e2) {
                    // Last resort - try finding any clickable option
                    By anyNumberOption = By.xpath("(//div[@role='option' or contains(@class, 'option')] | //li[@role='option' or contains(@class, 'option')])[1]");
                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(anyNumberOption));
                    option.click();
                    System.out.println(" Selected number from dropdown (last resort method)");
                }
            }
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println(" Failed to select from number: " + e.getMessage());
            throw new RuntimeException("Failed to select from number", e);
        }
    }
    
    public void selectTamilAgent() {
        try {
            Thread.sleep(1000);
            
            // Click on "Select Agent" dropdown
            WebElement agentDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(selectAgentDropdown));
            agentDropdownElement.click();
            System.out.println(" Clicked 'Select Agent' dropdown");
            
            Thread.sleep(1500);
            
            // Search for and select "Tamil agent"
            // First, try to find an input field to search
            try {
                By searchInput = By.xpath("(//input[@placeholder='Search' or contains(@placeholder, 'search') or @type='text'])[1]");
                WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
                searchField.clear();
                searchField.sendKeys("Tamil agent");
                Thread.sleep(800);
                System.out.println(" Entered 'Tamil agent' in search field");
            } catch (Exception e) {
                // Search field might not be available, continue to select directly
                System.out.println(" Search field not found, trying to select directly");
            }
            
            // Select "Tamil agent" from the dropdown - try multiple approaches
            By tamilAgentOption = By.xpath("//div[contains(text(), 'Tamil agent') or contains(text(), 'Tamil Agent')] | //li[contains(text(), 'Tamil agent') or contains(text(), 'Tamil Agent')] | //div[@role='option' and (contains(text(), 'Tamil') or contains(text(), 'tamil'))] | //li[@role='option' and (contains(text(), 'Tamil') or contains(text(), 'tamil'))]");
            
            try {
                WebElement tamilAgent = wait.until(ExpectedConditions.elementToBeClickable(tamilAgentOption));
                tamilAgent.click();
                System.out.println(" Selected 'Tamil agent' from dropdown");
            } catch (Exception e1) {
                try {
                    // Try case-insensitive search with more variations
                    By tamilAgentOptionCaseInsensitive = By.xpath("//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'tamil')] | //li[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'tamil')] | //div[@role='option' and contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'tamil')]");
                    WebElement tamilAgent = wait.until(ExpectedConditions.elementToBeClickable(tamilAgentOptionCaseInsensitive));
                    tamilAgent.click();
                    System.out.println(" Selected 'Tamil agent' from dropdown (case-insensitive)");
                } catch (Exception e2) {
                    // Try keyboard navigation as last resort
                    try {
                        agentDropdownElement.sendKeys("Tamil");
                        Thread.sleep(500);
                        agentDropdownElement.sendKeys(Keys.ENTER);
                        System.out.println(" Selected 'Tamil agent' using keyboard navigation");
                    } catch (Exception e3) {
                        System.out.println(" Failed to select Tamil agent with all methods");
                        throw new RuntimeException("Failed to select Tamil agent", e3);
                    }
                }
            }
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println(" Failed to select Tamil agent: " + e.getMessage());
            throw new RuntimeException("Failed to select Tamil agent", e);
        }
    }
    
    public void selectSendNow() {
        try {
            Thread.sleep(1000);
            
            // Click on "Send Now" radio button
            WebElement sendNowOption = wait.until(ExpectedConditions.elementToBeClickable(sendNowRadioButton));
            
            // Scroll to the element if needed
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", sendNowOption);
            Thread.sleep(500);
            
            sendNowOption.click();
            System.out.println(" Selected 'Send Now' option");
            
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println(" Failed to select 'Send Now': " + e.getMessage());
            throw new RuntimeException("Failed to select 'Send Now' option", e);
        }
    }
    
    public void clickCreateBatchCallSubmit() {
        try {
            Thread.sleep(1000);
            
            // Scroll to the button if needed
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(createBatchCallSubmitButton));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
            Thread.sleep(500);
            
            // Click "Create Batch Call" submit button
            submitButton.click();
            System.out.println(" Clicked 'Create Batch Call' submit button");
            
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println(" Failed to click 'Create Batch Call' submit button: " + e.getMessage());
            throw new RuntimeException("Failed to click 'Create Batch Call' submit button", e);
        }
    }
    
    // ---------- Complete Batch Call Creation Flow ----------
    public void createBatchCall() {
        try {
            // Click "Outbound Call" from side menu
            clickOutboundCallFromSideMenu();
            
            // Click "+ Create Batch Call" button
            clickCreateBatchCallButton();
            
            // Enter batch call name "Calls"
            enterBatchCallName("Calls");
            
            // Select any number from dropdown
            selectFromNumber();
            
            // Select "Tamil agent" from dropdown
            selectTamilAgent();
            
            // Skip CSV file upload (no action needed)
            System.out.println(" Skipping CSV file upload as per requirement");
            
            // Select "Send Now" option
            selectSendNow();
            
            // Click "Create Batch Call" button
            clickCreateBatchCallSubmit();
            
            System.out.println(" Batch Call created successfully!");
            
        } catch (Exception e) {
            System.out.println(" Batch Call creation failed: " + e.getMessage());
            throw new RuntimeException("Batch Call creation failed", e);
        }
    }
}