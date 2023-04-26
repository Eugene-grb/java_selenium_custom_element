package ru.cbgr.autotest.tests;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import ru.cbgr.autotest.pages.config.PageBinding;

import java.time.Duration;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * Базовый класс для всех тестов, необходимо наследоваться от него для запуска теста
 * @author Evgeny Gribanov
 * @version 20.03.2023
 */
@TestInstance(PER_CLASS)
public abstract class BaseTest {
    /** Установка ожидания, по истечении которого происходит исключение */
    private static final Duration DURATION_TIMEOUT = Duration.ofSeconds(5);

    @Inject
    protected WebDriver webDriver;
    private Injector injector;

    /**
     * Создать и привязать все объекты, необходимые для тестирования и установить таймаут на время поиска объектов.
     * Вызывается в тестовом цикле перед всеми тестами
     */
    @BeforeAll
    void setUp() {
        var module = new PageBinding(); // привязать все объекты
        injector = Guice.createInjector(module); // создать инжектор для внедрения
        injector.injectMembers(this); // внедрить в текущий тест необходимые объекты
        webDriver.manage().timeouts().implicitlyWait(DURATION_TIMEOUT);  // Включить неявное ожидание
    }

    /** Выполнить закрытие веб драйвера по окончании всех тестов */
    @AfterAll
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
