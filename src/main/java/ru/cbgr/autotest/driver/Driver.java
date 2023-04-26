package ru.cbgr.autotest.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.cbgr.autotest.enums.Browsers;

/**
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
public class Driver {
    protected static RemoteWebDriver webDriver;

    public static RemoteWebDriver initDriver(Browsers browserName) {

        switch (browserName) {
            case CHROME :
                Browser.getChromeRemoteWebDriver();
                break;
            case EDGE :
                Browser.getEdgeRemoteWebDriver();
                break;
            case FIREFOX :
                Browser.getFirefoxRemoteWebDriver();
                break;
        }

        return webDriver;
    }

    /** Получение уже созданного экземпляра вебдрайвера */
    public static RemoteWebDriver getInstance() {
        return webDriver;
    }
}
