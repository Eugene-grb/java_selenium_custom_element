package ru.cbgr.autotest.elements.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A button.
 **/
public class Button extends CustomWebElement {

    /**
     * Constructor.
     *
     * @param webDriver The webdriver usd to interact with the webbrowser.
     * @param by        The locator used to identify the element(s) on the website.
     **/
    public Button(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

     /**
     * Clicks on the button.
     **/
    public void click() {
        getWebDriver().findElement(getBy()).click();
    }
}
