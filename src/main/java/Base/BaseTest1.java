package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest1 {
	  protected static WebDriver driver;   // make static so it reuses
	    
	    @BeforeClass
	    public void setUp() {
	        if (driver == null) {   //  only create once
	            // Configure Chrome options to handle microphone permissions
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--use-fake-ui-for-media-stream"); // Auto-allow microphone
	            options.addArguments("--use-fake-device-for-media-stream"); // Use fake microphone device
	            options.addArguments("--allow-running-insecure-content");
	            options.addArguments("--disable-web-security");
	            options.addArguments("--disable-features=VizDisplayCompositor");
	            
	            driver = new ChromeDriver(options); 
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            driver.manage().window().maximize();
	            driver.get("https://app.dev.zudu.ai//login");
	        }
	    }
	    
	    @AfterClass
	    public void tearDown() throws InterruptedException {
	        if(driver != null) {
	            Thread.sleep(10000);
	            driver.quit();
	            driver = null;  
	        }
	    }
	}

