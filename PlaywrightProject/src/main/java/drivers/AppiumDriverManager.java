package drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AppiumDriverManager {
    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static void initDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app", "path/to/app.apk");
        caps.setCapability("automationName", "UiAutomator2");

        driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps));
    }

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
