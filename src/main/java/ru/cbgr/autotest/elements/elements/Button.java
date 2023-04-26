package ru.cbgr.autotest.elements.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Кнопка
 **/
public class Button extends CustomWebElement {
    /**
     * @param webDriver Веб-драйвер, используемый для взаимодействия с веб-браузером.
     * @param by        Локатор, используемый для идентификации элемента(ов) на сайте.
     **/
    public Button(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

     /**
     * Нажатие на кнопку.
     **/
    public void click() {
        getWebDriver().findElement(getBy()).click();
    }
}
