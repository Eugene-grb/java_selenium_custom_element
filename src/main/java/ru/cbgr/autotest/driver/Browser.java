package ru.cbgr.autotest.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Evgeny Gribanov
 * @version 30.01.2023
 * @link egribanov@cbgr.ru
 */
public abstract class Browser extends Driver {


    /** Возвращает экземпляр вебдрайвера для запуска Google Chrome */
    public static DesiredCapabilities getChromeRemoteWebDriver() {
        // инициализация нужной версии вебдрайвера в зависимости от установленной версии браузера
        WebDriverManager.chromedriver().setup();

        // установка состояния браузера
        // установка аргументов запуска
        var options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        webDriver = new ChromeDriver(options);
        return capabilities;
    }

    /** Возвращает экземпляр вебдрайвера для запуска Microsoft Edge */
    public static DesiredCapabilities getEdgeRemoteWebDriver() {
        // инициализация нужной версии вебдрайвера в зависимости от установленной версии браузера
        WebDriverManager.edgedriver().setup();

        // установка состояния браузера
        // установка аргументов запуска
        var options = new EdgeOptions();
        options.addArguments("--incognito");

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(EdgeOptions.CAPABILITY, options);

        webDriver = new EdgeDriver(options);
        return capabilities;
    }

    /** Возвращает экземпляр вебдрайвера для запуска Google Chrome */
    public static DesiredCapabilities getFirefoxRemoteWebDriver() {
        // инициализация нужной версии вебдрайвера в зависимости от установленной версии браузера
        WebDriverManager.firefoxdriver().setup();

        // установка состояния браузера
        // установка аргументов запуска
        var options = new FirefoxOptions();
        options.addArguments("-private");

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

        webDriver = new FirefoxDriver(options);
        return capabilities;
    }
}
