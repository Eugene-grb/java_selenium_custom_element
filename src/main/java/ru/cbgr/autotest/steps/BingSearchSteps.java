package ru.cbgr.autotest.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.cbgr.autotest.elements.elements.Button;
import ru.cbgr.autotest.elements.elements.CustomWebElement;
import ru.cbgr.autotest.elements.elements.TextField;

/**
 * Описываются элементы страницы и группируется общая логика для взаимодействия с ними
 */
public class BingSearchSteps extends CustomWebElement {
    @FindBy(id = "sb_form_q")
    private TextField searchField;

    @FindBy(id = "search_icon")
    private Button searchButton;

    public BingSearchSteps(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    /**
     * ввести в поисковую строку текст и нажать ENTER
     * @param searchText текст для поиска
     */
    public void search(String searchText) {
        searchField.setText(searchText);
        searchButton.click();
    }
}
