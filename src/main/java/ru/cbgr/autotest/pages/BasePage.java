package ru.cbgr.autotest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.cbgr.autotest.driver.Driver;
import ru.cbgr.autotest.elements.decorator.CustomElementFieldDecorator;

/**
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
public abstract class BasePage {

    protected WebDriver webDriver;

    public BasePage() {
        webDriver = Driver.getInstance();
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), this);
    }
}
