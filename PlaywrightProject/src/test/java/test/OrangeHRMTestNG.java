package test;
import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class OrangeHRMTestNG {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @Test
    public void loginToOrangeHRM() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.fill("input[name='username']", "Admin");
        page.fill("input[name='password']", "admin123");
        page.click("button[type='submit']");
        page.waitForURL("**/dashboard/**");
        assert page.url().contains("dashboard");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
