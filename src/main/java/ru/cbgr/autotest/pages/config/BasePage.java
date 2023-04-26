package ru.cbgr.autotest.pages.config;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.cbgr.autotest.driver.Driver;
import ru.cbgr.autotest.elements.decorator.CustomElementFieldDecorator;

/**
 * Базовая страница. Все страницы наследуются от нее.
 * Если не наследоваться, то элементы страницы не будут инициализированы
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
public abstract class BasePage {

    protected WebDriver webDriver;

    public BasePage() {
        webDriver = Driver.getInstance(); // получить уже созданный экземпляр драйвера
        PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), this);
    }
}
