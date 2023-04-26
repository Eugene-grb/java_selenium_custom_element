package ru.cbgr.autotest.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.cbgr.autotest.elements.decorator.CustomElementFieldDecorator;
import ru.cbgr.autotest.pages.config.BasePage;
import ru.cbgr.autotest.steps.BingSearchSteps;

/**
 * Объект страницы Bing.
 **/
public class BingPage extends BasePage {
    /**
     * Включает шаги поиска bing в эту страницу.
     * Если пользовательский веб-элемент не использует заданный локатор,
     * то можно просто вставить любой текст.
     **/
    @FindBy(id = "steps")
    private BingSearchSteps searchSteps;

    /**
     * выполнить поиск исходя из заданного текста
     * @param searchText текст для поиска
     */
    public void search(String searchText) {
        searchSteps.search(searchText);
    }
}