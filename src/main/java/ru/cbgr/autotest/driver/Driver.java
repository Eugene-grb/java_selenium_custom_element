package ru.cbgr.autotest.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.cbgr.autotest.enums.Browsers;

/**
 * Экземпляр веб-драйвера
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
public class Driver {
    /** веб-драйвер **/
    protected static RemoteWebDriver webDriver;

    /** инициализация веб-драйвера
     * @param browserName название браузера, который будет запущен **/
    public static RemoteWebDriver initDriver(Browsers browserName) {
        switch (browserName) {
            case CHROME : Browser.getChromeRemoteWebDriver(); break;
            case EDGE : Browser.getEdgeRemoteWebDriver(); break;
            case FIREFOX : Browser.getFirefoxRemoteWebDriver(); break;
        }

        return webDriver;
    }

    /** Получение уже созданного экземпляра вебдрайвера */
    public static RemoteWebDriver getInstance() {
        return webDriver;
    }
}
