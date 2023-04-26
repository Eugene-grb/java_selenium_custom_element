package ru.cbgr.autotest.elements.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Текстовое поле.
 **/
public class TextField extends CustomWebElement {
    /**
     * @param webDriver Веб-драйвер, используемый для взаимодействия с веб-браузером.
     * @param by        Локатор, используемый для идентификации элемента(ов) на сайте.
     **/
    public TextField(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /** @return Возвращает текст данного текстового поля **/
    public String getText() {
        return getAttribute("value");
    }

    /**
     * Вводит текст в текстовое поле.
     *
     * @param text Текст, который должен быть вставлен в это текстовое поле.
     **/
    public void setText(String text) {
        getWebDriver().findElement(getBy()).clear();
        getWebDriver().findElement(getBy()).sendKeys(text);
    }

    /** Пытается отправить сообщение, нажав клавишу Enter */
    public void submit() {
        getWebDriver().findElement(getBy()).sendKeys(Keys.ENTER);
    }
}
