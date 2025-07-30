package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
       // WebDriverManager.chromedriver().setup(); // This auto-downloads & sets path
        driver.set(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

