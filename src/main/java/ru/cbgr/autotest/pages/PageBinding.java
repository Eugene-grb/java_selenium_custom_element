package ru.cbgr.autotest.pages;

import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;
import ru.cbgr.autotest.driver.Driver;
import ru.cbgr.autotest.enums.Browsers;

/**
 * @author Evgeny Gribanov
 * @version 26.04.2023
 */
public class PageBinding extends AbstractModule {
    protected void configure() {
        bind(WebDriver.class).toInstance(Driver.initDriver(Browsers.CHROME));
        bind(BingPage.class).toInstance(new BingPage());
    }
}
