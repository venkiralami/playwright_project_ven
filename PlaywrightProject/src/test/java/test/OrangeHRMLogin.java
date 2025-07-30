package test;
import com.microsoft.playwright.*;

public class OrangeHRMLogin {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Navigate to OrangeHRM login page
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            // Wait for page to load
            page.waitForSelector("input[name='username']");

            // Fill username and password
            page.fill("input[name='username']", "Admin");
            page.fill("input[name='password']", "admin123");

            // Click login button
            page.click("button[type='submit']");

            // Wait for dashboard to load
            page.waitForURL("**/dashboard/**", new Page.WaitForURLOptions().setTimeout(5000));

            // Optional: Print page title
            System.out.println("Logged in. Title: " + page.title());

            page.waitForTimeout(3000);
            browser.close();
        }
    }
}
