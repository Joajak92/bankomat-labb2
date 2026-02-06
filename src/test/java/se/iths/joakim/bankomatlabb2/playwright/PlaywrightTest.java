package se.iths.joakim.bankomatlabb2.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlaywrightTest {
    static Playwright playwright;
    static Browser browser;

    // kommentar för att testa playwright tester i guthub actions

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true));
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @Test
    void canReachWebpage() {
        try (Page page = browser.newPage()) {
            page.navigate("http://localhost:8080/accounts");
            page.waitForLoadState(LoadState.LOAD);

            Locator accountSpan = page.locator("ul p span");
            String accountValue = accountSpan.textContent().trim();
            Assertions.assertEquals("0", accountValue);
        }
    }

    @Test
    void pageLoadingCorrectly() {
        try (Page page = browser.newPage()) {
            page.navigate("http://localhost:8080/accounts");
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Assertions.assertEquals("Saldo", page.title());
        }
    }
}
