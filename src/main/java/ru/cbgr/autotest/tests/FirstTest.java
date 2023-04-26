package ru.cbgr.autotest.tests;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import ru.cbgr.autotest.pages.BingPage;

public class FirstTest extends BaseTest {

    @Inject
    private BingPage bingPage;

    @Test
    void bingExampleTest() {
        // Open the Bing page
        webDriver.get("https://www.bing.com/");

        // Do the search and open result stuff
        bingPage.search("Test");
    }
}
