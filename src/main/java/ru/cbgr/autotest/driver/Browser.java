package ru.cbgr.autotest.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.OutputStream;

/**
 * Настройки браузеров
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
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        // выключаем лишнее логирование драйвера
        var chromeDriverService = new ChromeDriverService.Builder()
                .withSilent(true).build();
        chromeDriverService.sendOutputTo(new OutputStream() {
            @Override public void write(int b){}
        });

        webDriver = new ChromeDriver(chromeDriverService, options);
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
