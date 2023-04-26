package ru.cbgr.autotest.elements.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.By.*;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;

/**
 * Методы трансформации и доступ к локатору.
 * **/
public class WebElementTransformer {
    /** Различные локаторы selenium **/
    public enum LocatorType {
        ID, CSS, TAG_NAME, NAME, XPATH, LINK_TEXT, PARTIAL_LINK_TEXT, CLASS_NAME
    }

    /**
     * Преобразует аннотацию FindBy в локатор By.
     *
     * @param findBy Аннотация FindBy, которая должна быть преобразована в локатор By.
     * @return Локатор, который был создан на основе заданного параметра.
     **/
    public By transformFindByToBy(FindBy findBy) {
        if (findBy == null) {
            return null;
        } else if (findBy.id() != null && !findBy.id().isEmpty()) {
            return By.id(findBy.id());
        } else if (findBy.name() != null && !findBy.name().isEmpty()) {
            return By.name(findBy.name());
        } else if (findBy.xpath() != null && !findBy.xpath().isEmpty()) {
            return By.xpath(findBy.xpath());
        } else if (findBy.css() != null && !findBy.css().isEmpty()) {
            return By.cssSelector(findBy.css());
        } else if (findBy.className() != null && !findBy.className().isEmpty()) {
            return By.className(findBy.className());
        } else if (findBy.linkText() != null && !findBy.linkText().isEmpty()) {
            return By.linkText(findBy.linkText());
        } else if (findBy.partialLinkText() != null && !findBy.partialLinkText().isEmpty()) {
            return By.partialLinkText(findBy.partialLinkText());
        } else if (findBy.tagName() != null && !findBy.tagName().isEmpty()) {
            return By.tagName(findBy.tagName());
        }

        throw new IllegalArgumentException("FindBy не может быть сопоставлен с By: " + findBy.toString());
    }

    /**
     * Возвращает используемый тип заданного локатора.
     *
     * @param locator Локатор, для которого должен быть возвращен используемый тип.
     * @return Возвращает используемый тип заданного локатора.
     **/
    public LocatorType getLocatorType(By locator) {
        if (locator instanceof ById) {
            return LocatorType.ID;
        } else if (locator instanceof ByXPath) {
            return LocatorType.XPATH;
        } else if (locator instanceof ByClassName) {
            return LocatorType.CLASS_NAME;
        } else if (locator instanceof ByName) {
            return LocatorType.NAME;
        } else if (locator instanceof ByTagName) {
            return LocatorType.TAG_NAME;
        } else if (locator instanceof ByCssSelector) {
            return LocatorType.CSS;
        } else if (locator instanceof ByLinkText) {
            return LocatorType.LINK_TEXT;
        } else if (locator instanceof ByPartialLinkText) {
            return LocatorType.PARTIAL_LINK_TEXT;
        }

        throw new IllegalArgumentException("Не удалось распознать локатор. " + locator.toString());
    }

    /**
     * Возвращает значение локатора.
     *
     * @param locator Локатор, значение которого должно быть возвращено.
     * @param type    Тип локатора.
     * @return Значение локатора.
     **/
    public String getLocatorValue(By locator, LocatorType type) {
        Field locatorField;

        // Получить нужное поле
        try {
            switch (type) {
                case ID: locatorField = locator.getClass().getDeclaredField("id"); break;
                case CLASS_NAME: locatorField = locator.getClass().getDeclaredField("className"); break;
                case CSS: locatorField = locator.getClass().getDeclaredField("selector"); break;
                case LINK_TEXT: locatorField = locator.getClass().getDeclaredField("linkText"); break;
                case NAME: locatorField = locator.getClass().getDeclaredField("name"); break;
                case PARTIAL_LINK_TEXT: locatorField = locator.getClass().getDeclaredField("linkText"); break;
                case TAG_NAME: locatorField = locator.getClass().getDeclaredField("name"); break;
                case XPATH: locatorField = locator.getClass().getDeclaredField("xpathExpression"); break;
                default:
                    throw new IllegalArgumentException("Значение локатора для " + locator.toString() + " с типом " + type.toString() + " не найдено.");
            }
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("Возникла проблема с получением значения для локатора " + locator.toString() + " с типом " + type.toString(), e);
        }

        // Сделать приватное поле доступным, чтобы можно было получить доступ к его значению
        locatorField.setAccessible(true);

        // Возвращение значения локатора
        try {
            return (String) locatorField.get(locator);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Возникла проблема с получением значения для локатора "
                    + locator.toString() + " c типом " + type.toString(), e);
        }
    }
}
