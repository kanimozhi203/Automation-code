package EnglishPages;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ModelSetting {
   private WebDriver driver;
   private WebDriverWait wait;
   // ---------- Locators ----------
   private By agentSideMenu = By.xpath("//span[normalize-space()='Agents']");
   private By createAgentButton = By.xpath("//button[normalize-space()='Create agent']");
   private By agentNameInput = By.xpath("//input[@id='agent_name']");
   private By saveAgentButton = By.xpath("//button[@type='submit']");
   private By addVariableButton = By.xpath("(//button[text()='Add Variable'])[1]");
   private By systemAgentIdOption = By.xpath("//p[normalize-space()='system_agent_id']");
   private By systemCallerIdOption = By.xpath("//p[normalize-space()='system_caller_id']");
   private By addedVariablesSection = By.xpath("//div[contains(@class,'variable-list')]");
   private By startCallButton = By.xpath("(//span[normalize-space()='Start call'])[1]");
   
   // ---------- System Prompt Locators ----------
   private By systemPromptTextarea = By.xpath("(//textarea[@id='systemPrompt'])[1]");
   public ModelSetting(WebDriver driver) {
       this.driver = driver;
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
   }
   // ---------- Actions ----------
   public void goToCreateAgent(String agentName) {
       wait.until(ExpectedConditions.elementToBeClickable(agentSideMenu)).click();
       wait.until(ExpectedConditions.elementToBeClickable(createAgentButton)).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(agentNameInput)).sendKeys(agentName);
       wait.until(ExpectedConditions.elementToBeClickable(saveAgentButton)).click();
   }
   public void scrollToAddVariable() {
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addVariableButton));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
   }
   public void clickAddVariable() {
       wait.until(ExpectedConditions.elementToBeClickable(addVariableButton)).click();
   }
   public void selectSystemAgentId() {
       wait.until(ExpectedConditions.elementToBeClickable(systemAgentIdOption)).click();
   }
   public void selectSystemCallerId() {
       wait.until(ExpectedConditions.elementToBeClickable(systemCallerIdOption)).click();
   }
   public void addRequiredVariables() {
       scrollToAddVariable();
       clickAddVariable();
       selectSystemAgentId();
       clickAddVariable();
       selectSystemCallerId();
   }
   // ---------- Save ----------
   public void clickSave() {
       WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(saveAgentButton));
       saveButton.click();
       System.out.println(" Save button clicked.");
   }
   // ---------- Start Call ----------
   public void scrollToStartCall() {
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(startCallButton));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
   }
   public void clickStartCall() {
       scrollToStartCall(); // ensure visible
       WebElement startCall = wait.until(ExpectedConditions.elementToBeClickable(startCallButton));
       startCall.click();
       System.out.println("Start Call button clicked successfully.");
   }
   
   // ---------- System Prompt Actions ----------
   public void scrollToSystemPrompt() {
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(systemPromptTextarea));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
       
       // Small delay to ensure scrolling is complete
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
   }
   
   public void clearSystemPrompt() {
       scrollToSystemPrompt();
       WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(systemPromptTextarea));
       textarea.clear();
       System.out.println(" System prompt cleared.");
   }
   
   public void enterSystemPromptGuidelines() {
       scrollToSystemPrompt();
       WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(systemPromptTextarea));
       
       // Clear existing content first
       textarea.clear();
       
       // Build the concise system prompt (2 lines per tool)
       StringBuilder systemPrompt = new StringBuilder();
       
       // Polite Communication Guidelines
       systemPrompt.append("Always greet customers warmly and use polite language with 'please', 'thank you' throughout conversations. ");
       systemPrompt.append("Listen actively, acknowledge concerns with empathy, and confirm understanding before taking actions.\n\n");
       
       // Knowledge Base Instructions
       systemPrompt.append("KNOWLEDGE BASE: Check company knowledge base first when answering questions and mention the source if found. ");
       systemPrompt.append("If not found, clearly state 'This information is not available in our knowledge base' and offer alternative help.\n\n");
       
       // MCP Server Usage
       systemPrompt.append("MCP SERVERS: Use MCP servers for advanced data processing and complex analytical requests with data privacy. ");
       systemPrompt.append("Always inform customers when using external processing tools for transparency.\n\n");
       
       // Calendar Management
       systemPrompt.append("CALENDAR AVAILABILITY: Check calendar availability first and verify customer's timezone before suggesting time slots. ");
       systemPrompt.append("Present 2-3 available options for customer selection.\n\n");
       
       systemPrompt.append("CALENDAR BOOKING: Confirm appointment details (date, time, timezone) with customer before booking. ");
       systemPrompt.append("Send SMS confirmation immediately after successful booking.\n\n");
       
       // Transfer Protocols
       systemPrompt.append("TRANSFERS: Transfer to human agents for customer frustration, manager requests, or technical issues beyond capabilities. ");
       systemPrompt.append("Always explain the reason for transfer to ensure smooth handoff.\n\n");
       
       // SMS Guidelines
       systemPrompt.append("SMS COMMUNICATION: Send SMS confirmations for bookings, updates, or when requested by customers. ");
       systemPrompt.append("Keep messages professional, concise with relevant details like appointment time, date, and contact information.\n\n");
       
       // Professional Standards
       systemPrompt.append("PROFESSIONAL TONE: Maintain professional, helpful, and empathetic tone throughout all interactions. ");
       systemPrompt.append("Be clear and concise in responses, always confirm understanding before taking actions.");
       
       // Enter the complete system prompt
       textarea.sendKeys(systemPrompt.toString());
       
       System.out.println(" System prompt guidelines entered successfully.");
   }
   
   // ---------- Validation ----------
   public boolean areVariablesAdded() {
       try {
           WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(addedVariablesSection));
           String text = section.getText();
           return text.contains("system_agent_id") && text.contains("system_caller_id");
       } catch (Exception e) {
           return false;
       }
   }
   
   public boolean isSystemPromptConfigured() {
       try {
           WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(systemPromptTextarea));
           String content = textarea.getAttribute("value");
           if (content == null) {
               content = textarea.getText();
           }
           return content != null && content.trim().length() > 0 && 
                  content.contains("KNOWLEDGE BASE") && 
                  content.contains("CALENDAR") && 
                  content.contains("PROFESSIONAL TONE");
       } catch (Exception e) {
           return false;
       }
   }
}
