package drivers;

import com.microsoft.playwright.*;

public class PlaywrightDriverManager {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initDriver() {
        playwright.set(Playwright.create());
        browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void quitDriver() {
        if (page.get() != null) {
            context.get().close();
            browser.get().close();
            playwright.get().close();
            playwright.remove();
            browser.remove();
            context.remove();
            page.remove();
        }
    }
}

