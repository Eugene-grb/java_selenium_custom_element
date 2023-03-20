package ru.cbgr.autotest.tests;

import org.junit.jupiter.api.Test;
import ru.cbgr.autotest.pages.BingPage;

public class FirstTest extends BaseTest {


    @Test
    void bingExampleTest() {
        BingPage bingPage = new BingPage();

        // Open the Bing page
        webDriver.get("https://www.bing.com/");

        // Do the search and open result stuff
        bingPage.search("Test");
    }
}
