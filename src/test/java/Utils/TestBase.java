package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.healenium.SelfHealingDriver;

public class TestBase {

    public WebDriver driver;
   // public SelfHealingDriver driver;

    public WebDriver browserlaunch() throws IOException {

        FileInputStream fi = new FileInputStream("C:\\Users\\skomaragiri\\eclipse-workspace\\saucedemo\\Resource\\Global.properties");

        Properties prop = new Properties();
        prop.load(fi);

        String URL = prop.getProperty("url");

        if (driver == null) {
        	

            String browserName = prop.getProperty("browser");

            if (browserName.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver", "C:\\Users\\skomaragiri\\eclipse-workspace\\saucedemo\\drivers\\chromedriver.exe");

                
                
                
     //chrome options class provided by  selenium
     //using this we can customise the chrome like run incognito,disable pasword,..e.t.c....
                ChromeOptions options = new ChromeOptions();

                // Fresh incognito session
                options.addArguments("--incognito");

                // Disable credential popups
                //map(key-value) to store Chrome preferences you want to apply.
                Map<String, Object> prefs = new HashMap<>();
                //Disables Chrome's internal credentials service. like want to save or never.....
                prefs.put("credentials_enable_service", false);
                //Disables Chrome's password manager UI — the one that pops up asking:
                prefs.put("profile.password_manager_enabled", false);
                //pref's is used to help customize the chrome settings
                options.setExperimentalOption("prefs", prefs);

                // Suppress "Chrome is being controlled by automated software"
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

               //driver = new ChromeDriver(options);
                
                WebDriver delegate = new ChromeDriver(options); // regular driver
                 driver = SelfHealingDriver.create(delegate); 
                 driver.manage().deleteAllCookies();
                 driver.manage().window().maximize();
                 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                 driver.get(URL);
            
            }
            else if (browserName.equalsIgnoreCase("firefox")) {

                System.setProperty("webdriver.gecko.driver", "C:\\Users\\skomaragiri\\eclipse-workspace\\saucedemo\\drivers\\geckodriver.exe");

                WebDriver delegate = new FirefoxDriver(); // regular driver
                 driver = SelfHealingDriver.create(delegate); 
            
                 driver.manage().deleteAllCookies();
                 driver.manage().window().maximize();
                 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                 driver.get(URL);
            
            }
        }

       

        return driver;
    }
}
