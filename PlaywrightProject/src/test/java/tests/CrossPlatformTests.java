package tests;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import drivers.AppiumDriverManager;
import drivers.PlaywrightDriverManager;
import drivers.SeleniumDriverManager;

public class CrossPlatformTests {

    private String platform;

    @BeforeMethod
    @Parameters("platform")
    public void setup(@Optional("playwright") String platform) throws Exception {
        System.out.println("Setups on platform: " + platform);
    	this.platform = platform;

        switch (platform.toLowerCase()) {
            case "selenium":
                SeleniumDriverManager.initDriver();
                break;
            case "playwright":
                PlaywrightDriverManager.initDriver();
                break;
            case "appium":
                AppiumDriverManager.initDriver();
                break;
            default:
                throw new SkipException("Unsupported platform: " + platform);
        }
    }

    @Test
    public void loginTest() {
    			System.out.println("Running login test on platform: " + platform);
        switch (platform.toLowerCase()) {
            case "selenium":
                SeleniumDriverManager.getDriver().get("https://example.com");
                break;
            case "playwright":
                PlaywrightDriverManager.getPage().navigate("https://example.com");
                break;
            case "appium":
                // Appium steps
                break;
        }
    }

    @AfterMethod
    public void tearDown() {
    			System.out.println("Tearing down on platform: " + platform);
        switch (platform.toLowerCase()) {
            case "selenium":
                SeleniumDriverManager.quitDriver();
                break;
            case "playwright":
                PlaywrightDriverManager.quitDriver();
                break;
            case "appium":
                AppiumDriverManager.quitDriver();
                break;
        }
    }
}



