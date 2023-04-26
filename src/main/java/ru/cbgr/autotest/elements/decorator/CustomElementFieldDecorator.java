package ru.cbgr.autotest.elements.decorator;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import ru.cbgr.autotest.elements.elements.CustomWebElement;

import java.lang.reflect.Field;

/*
 * Краткое описание:
 * Фабрика страниц проходит по всем полям класса и вызывает метод "decorate()" интерфейса "FieldDecorator".
 * Метод "decorate()" должен возвращать объект, имеющий правильный тип.
 *
 * Для этого ему нужен локатор поля, чтобы веб-элемент можно было идентифицировать.
 * 
 * Это должно работать следующим образом:
 * 
 * Класс Page должен быть инстанцирован -> PageFactory.init() -> decorate() для каждого поля
 * выбор 1: декорировать для веб-элемента по умолчанию: DefaultFieldDecorator используется для поведения по умолчанию
 * выбор 2: декорирование создает соответствующий пользовательский веб-элемент -> getLocator(), чтобы иметь возможность получить веб-элемент
 * -> getEnhancedObject() для добавления метода обратного вызова для ленивой инициализации и настройки пользовательского веб-элемента
 * -> getElementHandler(), который реализует метод обратного вызова, чтобы добавить метод обратного вызова для ленивой инициализации и настройки пользовательского веб-элемента.
 * */

/**
 * Реализация FieldDecorator для обеспечения возможности использования пользовательских веб-элементов через фабрику страниц.
 * Пользовательские веб-элементы будут создаваться с помощью ленивой инициализации.
 **/
public class CustomElementFieldDecorator implements FieldDecorator {
    /** Декоратор, который используется, когда используется WebElement и поведение по умолчанию */
    private final DefaultFieldDecorator defaultFieldDecorator;

    /** Контекст поиска пользовательского веб-элемента. В основном это веб-драйвер **/
    private final SearchContext searchContext;

    /** веб-драйвер **/
    private final WebDriver webDriver;

    /**
     * Конструктор
     *
     * @param searchContext Контекст поиска для (пользовательского) веб-элемента. В основном просто объект webdriver.
     *                      Используется для поиска веб-элементов на веб-странице.
     * @param webDriver     Веб-драйвер, который будет использоваться для создания веб-элемента.
     **/
    public CustomElementFieldDecorator(SearchContext searchContext, WebDriver webDriver) {
        this.searchContext = searchContext;
        this.webDriver = webDriver;
        defaultFieldDecorator = new DefaultFieldDecorator(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Этот метод вызывается Selenium PageFactory для всех полей, чтобы решить, как декорировать поле.
     *
     * @param loader Загрузчик класса, который был использован для объекта страницы
     * @param field  Поле, которое должно быть декорировано. Это должен быть аннотированный (пользовательский) веб-элемент FindBy.
     * @return Значение, которым будет украшено поле.
     **/
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        // Если это пользовательский аннотированный веб-элемент, вызвать инициализацию обертки
        if (CustomWebElement.class.isAssignableFrom(field.getType())  && field.isAnnotationPresent(FindBy.class)) {
            return getEnhancedObject(field.getType(), getElementHandler(field), field.getAnnotation(FindBy.class));
        }
        // Если это обычный веб-элемент, то используйте реализацию FieldDecorator по умолчанию
        else {
            return defaultFieldDecorator.decorate(loader, field);
        }
    }

    /**
     * Создает класс с методом обратного вызова. Метод будет вызываться для
     * данного объекта поля (например, вызов метода click() на кнопке).
     *
     * @return Класс, содержащий метод обратного вызова.
     **/
    private CustomElementLocator getElementHandler(Field field) {
        return new CustomElementLocator(getLocator(field));
    }

    /**
     * Возвращает обработчик элемента для поля, который объединяет поле и локатор (он же FindBy) для дальнейшего использования.
     * Локатор ElementLocator может найти веб-элемент на веб-странице без каких-либо параметров, так как вся необходимая информация
     * уже есть.
     *
     * @param field Аннотированное поле, на основе которого будет создан локатор элемента.
     * @return Объект локатора элемента.
     **/
    private ElementLocator getLocator(Field field) {
        return new DefaultElementLocatorFactory(searchContext).createLocator(field);
    }

    /**
     * Класс для вызова обертки для кастомных элементов.
     * Пример: Кнопка нажата -> Поскольку класс может быть пользовательским веб-элементом,
     * метод вызывает внутренний метод этой обертки.
     *
     * @param clzz              Класс, который должен быть дополнен методом обратного вызова (например, кнопка).
     *                          Если метод этого класса будет вызван, то выполнится метод этой кнопки.
     * @param methodInterceptor Класс, реализующий метод обратного вызова.
     * @param locator           Локатор, который был использован для идентификации веб-элемента с помощью аннотации FindBy.
     **/
    private Object getEnhancedObject(Class<?> clzz, MethodInterceptor methodInterceptor, FindBy locator) {
        Enhancer e = new Enhancer();
        WebElementTransformer transformer = new WebElementTransformer();

        e.setSuperclass(clzz);
        e.setCallback(methodInterceptor);

        return e.create(new Class[]{WebDriver.class, By.class}, new Object[]{webDriver, transformer.transformFindByToBy(locator)});
    }
}
