package Utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureAttachments {

    public static void attachScreenshot(WebDriver driver, String stepName) {
        try {
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                                .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            Allure.addAttachment("Screenshot - " + stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", "text/plain", e.getMessage());
        }
    }

    public static void attachLogs(String message) {
        Allure.addAttachment("Step Log", "text/plain", message);
    }

    // ✅ Implement this method to attach page source
    public static void attachPageSource(WebDriver driver) {
        try {
            String pageSource = driver.getPageSource();
            Allure.addAttachment("Page Source", "text/html", pageSource, ".html");
        } catch (Exception e) {
            Allure.addAttachment("Page Source Error", "text/plain", e.getMessage());
        }
    }
}
