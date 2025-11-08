package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import Base.BaseTest;
import EnglishPages.LoginPage;

public class DebugLoginTest extends BaseTest {

    @Test
    public void debugLoginProcess() {
        System.out.println("=== DEBUG LOGIN PROCESS ===");
        
        try {
            // Check initial state
            System.out.println("Initial URL: " + driver.getCurrentUrl());
            System.out.println("Initial Title: " + driver.getTitle());
            System.out.println("Page Source Length: " + driver.getPageSource().length());
            
            // Check if login page elements are present
            LoginPage loginPage = new LoginPage(driver);
            
            // Check for email field
            try {
                WebElement emailField = driver.findElement(loginPage.usernameTextBox);
                System.out.println("✓ Email field found");
                System.out.println("Email field visible: " + emailField.isDisplayed());
                System.out.println("Email field enabled: " + emailField.isEnabled());
            } catch (Exception e) {
                System.out.println("✗ Email field not found: " + e.getMessage());
            }
            
            // Check for continue button
            try {
                WebElement continueBtn = driver.findElement(loginPage.submitButton);
                System.out.println("✓ Continue button found");
                System.out.println("Continue button visible: " + continueBtn.isDisplayed());
                System.out.println("Continue button enabled: " + continueBtn.isEnabled());
                System.out.println("Continue button text: " + continueBtn.getText());
            } catch (Exception e) {
                System.out.println("✗ Continue button not found: " + e.getMessage());
            }
            
            // Try the login process step by step
            System.out.println("\n=== STARTING STEP-BY-STEP LOGIN ===");
            
            // Step 1: Enter email
            try {
                loginPage.enterEmail("kandan@zudu.ai");
                System.out.println("✓ Email entered successfully");
            } catch (Exception e) {
                System.out.println("✗ Failed to enter email: " + e.getMessage());
                return;
            }
            
            // Step 2: Click continue
            try {
                loginPage.clickContinue();
                System.out.println("✓ Continue button clicked");
                
                // Wait and check for password field
                Thread.sleep(3000);
                System.out.println("URL after continue: " + driver.getCurrentUrl());
                System.out.println("Title after continue: " + driver.getTitle());
                
            } catch (Exception e) {
                System.out.println("✗ Failed to click continue: " + e.getMessage());
                return;
            }
            
            // Step 3: Check for password field
            try {
                WebElement passwordField = driver.findElement(loginPage.passwordField);
                System.out.println("✓ Password field found");
                System.out.println("Password field visible: " + passwordField.isDisplayed());
                System.out.println("Password field enabled: " + passwordField.isEnabled());
            } catch (Exception e) {
                System.out.println("✗ Password field not found: " + e.getMessage());
                
                // Check for error messages
                try {
                    WebElement errorElement = driver.findElement(By.xpath("//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'invalid') or contains(text(),'Invalid')]"));
                    System.out.println("Error message found: " + errorElement.getText());
                } catch (Exception ex) {
                    System.out.println("No error message found");
                }
                
                // Print page source for debugging
                System.out.println("Current page source (first 500 chars):");
                System.out.println(driver.getPageSource().substring(0, Math.min(500, driver.getPageSource().length())));
                return;
            }
            
            // Step 4: Enter password
            try {
                loginPage.enterPassword("Qwerty@mlp123");
                System.out.println("✓ Password entered successfully");
            } catch (Exception e) {
                System.out.println("✗ Failed to enter password: " + e.getMessage());
                return;
            }
            
            // Step 5: Click sign in
            try {
                loginPage.clickSignIn();
                System.out.println("✓ Sign in button clicked");
                
                // Wait for authentication
                Thread.sleep(5000);
                System.out.println("URL after sign in: " + driver.getCurrentUrl());
                System.out.println("Title after sign in: " + driver.getTitle());
                
            } catch (Exception e) {
                System.out.println("✗ Failed to click sign in: " + e.getMessage());
                return;
            }
            
            // Step 6: Check final state
            System.out.println("\n=== FINAL STATE CHECK ===");
            String finalUrl = driver.getCurrentUrl();
            String finalTitle = driver.getTitle();
            
            System.out.println("Final URL: " + finalUrl);
            System.out.println("Final Title: " + finalTitle);
            
            if (finalUrl.contains("agents") || finalUrl.contains("dashboard")) {
                System.out.println("✓ Login successful - redirected to main app");
                Assert.assertTrue(true, "Login successful");
            } else if (finalUrl.contains("login")) {
                System.out.println("✗ Login failed - still on login page");
                
                // Check for error messages
                try {
                    WebElement errorElement = driver.findElement(By.xpath("//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'invalid') or contains(text(),'Invalid') or contains(text(),'incorrect') or contains(text(),'Incorrect')]"));
                    System.out.println("Login error message: " + errorElement.getText());
                } catch (Exception ex) {
                    System.out.println("No error message found");
                }
                
                Assert.fail("Login failed - still on login page");
            } else {
                System.out.println("? Unknown state - URL: " + finalUrl);
                Assert.fail("Unknown login state - URL: " + finalUrl);
            }
            
        } catch (Exception e) {
            System.out.println("Debug test failed with exception: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Debug test failed: " + e.getMessage());
        }
        
        System.out.println("=== DEBUG LOGIN PROCESS COMPLETED ===");
    }
}
