package ru.cbgr.autotest.elements.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.cbgr.autotest.elements.decorator.CustomElementFieldDecorator;
import ru.cbgr.autotest.elements.decorator.WebElementTransformer;

/**
 * Родительский класс для всех кастомных веб-элементов.
 **/
public abstract class CustomWebElement {
    /** Веб-драйвер, который может быть использован в наследниках **/
    private WebDriver webDriver;

    /** Локатор, с помощью которого будет идентифицирован элемент(ы), используемый(ые) для действия **/
    private By locator;

    /** Используется для доступа к локаторам веб-элемента **/
    private WebElementTransformer transformer;

    /**
     * @param webDriver Веб-драйвер, используемый для взаимодействия с веб-браузером.
     * @param by        Локатор, используемый для идентификации элемента(ов) на сайте.
     **/
    public CustomWebElement(WebDriver webDriver, By by) {
        this.webDriver = webDriver;
        locator = by;
        transformer = new WebElementTransformer();

        // Вызов фабрики страниц на этом объекте для инициализации пользовательских
        // веб-элементов в пользовательских веб-элементах (так называемая вложенность)
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), this);
    }

    /** @return Возвращает локатор для идентификации элемента(ов) на сайте **/
    public By getBy() {
        return locator;
    }

    /**
     * Получить значение заданного атрибута элемента. Возвращает текущее значение, даже если
     * оно было изменено после загрузки страницы.
     *
     * @param attributeName Имя атрибута.
     * @return Текущее значение атрибута/свойства или null, если значение не установлено.
     */
    public String getAttribute(String attributeName) {
        return getWebDriver().findElement(getBy()).getAttribute(attributeName);
    }

    /** @return Возвращает модуль для преобразования элемента **/
    protected WebElementTransformer transformer() {
        return transformer;
    }

    /** @return Возвращает веб-драйвер **/
    protected WebDriver getWebDriver() {
        return webDriver;
    }

    /** @return Возвращает используемый тип заданного локатора **/
    protected WebElementTransformer.LocatorType getLocatorType() {
        return transformer.getLocatorType(getBy());
    }

    /**
     * Возвращает значение локатора.
     *
     * @param type Тип локатора.
     * @return Значение локатора.
     **/
    protected String getLocatorValue(WebElementTransformer.LocatorType type) {
        return transformer.getLocatorValue(getBy(), type);
    }
}