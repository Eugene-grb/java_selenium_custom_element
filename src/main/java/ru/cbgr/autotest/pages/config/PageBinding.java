package ru.cbgr.autotest.pages.config;

import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;
import ru.cbgr.autotest.driver.Driver;
import ru.cbgr.autotest.enums.Browsers;
import ru.cbgr.autotest.pages.BingPage;

/**
 * Привязка классов к объектам для внедрения зависимостей с помощью guice
 * @author Evgeny Gribanov
 * @version 26.04.2023
 */
public class PageBinding extends AbstractModule {

    private static final String BROWSER = System.getProperty("browser", "chrome");

    /** описание привязок, согласно которым необходимо создать объекты */
    protected void configure() {
        bind(WebDriver.class).toInstance(Driver.initDriver(Browsers.fromString(BROWSER)));
        bind(BingPage.class).toInstance(new BingPage());
    }
}
