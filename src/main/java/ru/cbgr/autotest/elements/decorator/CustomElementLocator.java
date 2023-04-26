package ru.cbgr.autotest.elements.decorator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import ru.cbgr.autotest.elements.elements.CustomWebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Этот класс обрабатывает вызовы методов пользовательских веб-элементов.
 **/
public class CustomElementLocator implements MethodInterceptor {
    /** Локатор для получения веб-элемента с веб-страницы **/
    private final ElementLocator locator;

    public CustomElementLocator(ElementLocator locator) {
        this.locator = locator;
    }

    /**
     * Обрабатывает вызовы методов для пользовательского веб-элемента.
     *
     * @param o           Объект, из которого был вызван метод.
     * @param method      Вызванный метод.
     * @param objects     Объект параметра, содержащий значение.
     * @param methodProxy Используется для вызова метода суперкласса.
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // Настройка пользовательского веб-элемента (WebButton и т.д.)
        if (o instanceof CustomWebElement) {
            // Вызывает метод исходного объекта
            try {
                return methodProxy.invokeSuper(o, objects);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
        // Настройка обычного веб-элемента
        // Никогда не должен вызываться в текущем сценарии использования, поскольку он обрабатывается в классе CustomElementFieldDecorator
        else if (o instanceof WebElement) {
            // Обработка только первого отображаемого элемента
            // Получение первого веб-элемента по умолчанию, который соответствует локатору
            var displayedElement = locateElement();

            if (displayedElement != null) return method.invoke(displayedElement, objects);
            else return methodProxy.invokeSuper(o, objects);
        }

        return null;
    }

    /**
     * Получение экземпляра веб-элемента.
     *
     * @return Возвращает прокси-элемент, который реализует данный веб-элемент.
     *  Это необходимо для вызова isVisible и других методов на самом себе (пользовательском веб-элементе) без получения исключения.
     **/
    private WebElement locateElement() {
        return proxyForLocator(ElementLocator.class.getClassLoader(), locator);
    }

    /**
     * Создает динамический прокси-элемент для веб-элемента.
     * Украден из класса DefaultFieldDecorator.class в Selenium lib.
     * @link <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Proxy.html">Proxy</a>
     *
     * @param loader  Загрузчик классов, используемый для создания прокси.
     * @param locator Локатор элемента, используемый для определения местоположения веб-элемента.
     * @return Веб-элемент прокси.
     **/
    private WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementHandler(locator);
        WebElement proxy;

        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);

        return proxy;
    }
}
