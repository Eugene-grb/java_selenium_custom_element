package ru.cbgr.autotest.pages;


import org.openqa.selenium.support.FindBy;
import ru.cbgr.autotest.steps.BingSearchSteps;

/**
 * The Bing page object.
 * In order to keep this example simple, this page object contains both the search logic and the result list logic.
 **/
public class BingPage extends BasePage {

    /**
     * Includes the bing search module in this page.
     * If the custom webelement does not use the given locator, you can simply insert what you want.
     **/
    @FindBy(id = "module")
    private BingSearchSteps searchSteps;


    public void search(String searchText) {
        searchSteps.search(searchText);
    }

}