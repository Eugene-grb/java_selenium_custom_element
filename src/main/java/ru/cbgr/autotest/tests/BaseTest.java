package ru.cbgr.autotest.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import ru.cbgr.autotest.driver.Driver;
import ru.cbgr.autotest.enums.Browsers;

import java.time.Duration;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
@TestInstance(PER_CLASS)
public abstract class BaseTest {
    /** Установка ожидания, по истечении которого происходит исключение */
    private static final Duration DURATION_TIMEOUT = Duration.ofSeconds(5);

    protected WebDriver webDriver;

    @BeforeAll
    void setUp() {
        webDriver = Driver.getDriver(Browsers.CHROME);

        // Enable implicit wait
        webDriver.manage().timeouts().implicitlyWait(DURATION_TIMEOUT);
    }

    @AfterAll
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
