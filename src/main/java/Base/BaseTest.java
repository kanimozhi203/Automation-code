
package Base;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;

public class BaseTest {
    
    protected static WebDriver driver;   // make static so it reuses
    private static boolean keepBrowserOpen = true; // Set to false to close browser after tests
    private static boolean isLoggedIn = false; // Track login state
    
    @BeforeClass
    public void setUp() {
        if (driver == null) {   //  only create once
            // Configure Chrome options to handle microphone permissions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--use-fake-ui-for-media-stream"); // Auto-allow microphone
            options.addArguments("--use-fake-device-for-media-stream"); // Use fake microphone device
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-features=VizDisplayCompositor");
            options.addArguments("--disable-blink-features=AutomationControlled"); // Hide automation
            options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36");
            
            // Prevent multiple Chrome instances
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-default-apps");
            options.addArguments("--disable-popup-blocking");
            
            // Remove conflicting security settings that can cause authentication issues
            // options.addArguments("--disable-web-security"); // REMOVED - causes auth issues
            
            driver = new ChromeDriver(options); 
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.manage().window().maximize();
        }
        
        // Only clear cookies and navigate if not already logged in
        if (!isLoggedIn) {
            // Don't clear cookies immediately - let authentication flow complete first
            driver.get("https://app.uat.zudu.ai/login");
            
            // Wait for page to load completely
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @AfterClass
    public void tearDown() throws InterruptedException {
        // Don't close browser after each test class - let it stay open
        // This allows multiple test methods in the same class to reuse the same browser
        System.out.println("Test class completed. Browser will remain open for next test methods.");
    }
    
    @AfterSuite
    public void tearDownSuite() throws InterruptedException {
        // Only close browser after all tests in the suite are complete
        if(driver != null) {
            if (keepBrowserOpen) {
                System.out.println("All tests completed. Browser will remain open for inspection.");
                System.out.println("To close browser manually, call driver.quit() or set keepBrowserOpen = false");
            } else {
                Thread.sleep(5000); // Give time to see final state
                driver.quit();
                driver = null;
                System.out.println("Browser closed after all tests completed.");
            }
        }
    }
    
    // Method to manually close browser if needed
    public static void closeBrowser() {
        if(driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Browser closed manually.");
        }
    }
    
    // Method to set whether to keep browser open
    public static void setKeepBrowserOpen(boolean keepOpen) {
        keepBrowserOpen = keepOpen;
    }
    
    // Method to mark login as successful
    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    
    // Method to check if user is logged in
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    // Method to clear session and force re-login
    public static void forceReLogin() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            isLoggedIn = false;
            driver.get("https://app.uat.zudu.ai/login");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Method to reset to login page for test isolation
    public static void resetToLoginPage() {
        if (driver != null) {
            driver.get("https://app.uat.zudu.ai/login");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
