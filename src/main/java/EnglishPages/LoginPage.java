package EnglishPages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    public WebDriver driver;
    public WebDriverWait wait;

    // Locators
    public By usernameTextBox = By.xpath("(//input[@type='email'])[1]");
    public By submitButton = By.xpath("//button[contains(text(), 'Continue')]");
    public By passwordField = By.xpath("//input[@type='password']");
    public By signInButton = By.xpath("//button[contains(text(), 'Sign in')]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void enterEmail(String email) {
        WebElement emailField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(usernameTextBox));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    
    

    public void enterPassword(String password) {
        WebElement pwdField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        pwdField.clear();
        pwdField.sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    // Validate successful login
    public boolean isLoginSuccessful() {
        try {
            // Wait for redirect to dashboard/agents page or check for success indicators
            WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return extendedWait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("agents"),
                ExpectedConditions.urlContains("dashboard"),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Agents']"))
            ));
        } catch (Exception e) {
            System.out.println("Login validation failed: " + e.getMessage());
            return false;
        }
    }

    // Utility: complete login in one step with validation
    public boolean loginAs(String email, String password) {
        try {
            System.out.println("Starting login process...");
            
            // Check if we're already on the login page
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            
            // Check if we're already logged in and on a valid page
            if (currentUrl.contains("agents") || currentUrl.contains("dashboard") || currentUrl.contains("home")) {
                System.out.println("Already logged in and on valid page: " + currentUrl);
                return true;
            }
            
            // If not on login page, navigate to it
            if (!currentUrl.contains("login")) {
                System.out.println("Navigating to login page...");
                driver.get("https://app.uat.zudu.ai/login");
                Thread.sleep(3000);
            }
            
            enterEmail(email);
            System.out.println("Email entered successfully");
            
            clickContinue();
            System.out.println("Continue button clicked");
            
            // Wait for password field to appear with better error handling
            WebDriverWait passwordWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                passwordWait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
                System.out.println("Password field is visible");
            } catch (Exception e) {
                System.out.println("Password field not found. Checking for error messages...");
                // Check for error messages
                try {
                    WebElement errorElement = driver.findElement(By.xpath("//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'invalid') or contains(text(),'Invalid')]"));
                    System.out.println("Error message found: " + errorElement.getText());
                } catch (Exception ex) {
                    System.out.println("No error message found");
                }
                throw new RuntimeException("Password field not visible after clicking continue");
            }
            
            enterPassword(password);
            System.out.println("Password entered successfully");
            
            clickSignIn();
            System.out.println("Sign in button clicked");
            
            // Wait for authentication to complete with multiple validation checks
            System.out.println("Waiting for authentication to complete...");
            WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            
            // Check for successful login indicators
            boolean loginSuccess = false;
            try {
                loginSuccess = extendedWait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("agents"),
                    ExpectedConditions.urlContains("dashboard"),
                    ExpectedConditions.urlContains("home"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Agents']")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Agents')]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Create agent')]"))
                ));
            } catch (Exception e) {
                System.out.println("Login validation timeout. Checking current state...");
                System.out.println("Current URL: " + driver.getCurrentUrl());
                System.out.println("Page title: " + driver.getTitle());
                
                // Check if we're still on login page
                if (driver.getCurrentUrl().contains("login")) {
                    System.out.println("Still on login page - authentication may have failed");
                    
                    // Check for error messages
                    try {
                        WebElement errorElement = driver.findElement(By.xpath("//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'invalid') or contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'Incorrect')]"));
                        System.out.println("Login error message: " + errorElement.getText());
                    } catch (Exception ex) {
                        System.out.println("No error message found");
                    }
                    return false;
                }
            }
            
            if (loginSuccess) {
                System.out.println("Login successful! Current URL: " + driver.getCurrentUrl());
                
                // Additional wait to ensure all API calls and session setup is complete
                Thread.sleep(5000);
                
                // Mark as logged in in BaseTest
                try {
                    Class<?> baseTestClass = Class.forName("Base.BaseTest");
                    baseTestClass.getMethod("setLoggedIn", boolean.class).invoke(null, true);
                    System.out.println("Login state updated in BaseTest");
                } catch (Exception e) {
                    System.out.println("Could not update login state in BaseTest: " + e.getMessage());
                }
                
                return true;
            } else {
                System.out.println("Login validation failed");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("Login failed with error: " + e.getMessage());
            e.printStackTrace();
            
            // Take screenshot for debugging
            try {
                // You can add screenshot functionality here if needed
                System.out.println("Current page source length: " + driver.getPageSource().length());
            } catch (Exception ex) {
                System.out.println("Could not get page source");
            }
            
            return false;
        }
    }
}
