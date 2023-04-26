package ru.cbgr.autotest.tests;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.cbgr.autotest.pages.BingPage;

import java.util.Locale;

public class FirstTest extends BaseTest {

    @Inject
    private BingPage bingPage;

    @Test
    void bingExampleTest() {
        // Открыть страницу Bing
        webDriver.get("https://www.bing.com/");

        // Выполните поиск и откройте результат
        bingPage.search("Test");
    }
}
