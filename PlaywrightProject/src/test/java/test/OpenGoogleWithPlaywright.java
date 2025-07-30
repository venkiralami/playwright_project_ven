package test;

import com.microsoft.playwright.*;

public class OpenGoogleWithPlaywright {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.google.com");
            System.out.println("Page title: " + page.title());
            // Wait for 5 seconds
            page.waitForTimeout(5000);

            browser.close();
        }
    }
}
