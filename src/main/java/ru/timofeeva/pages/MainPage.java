package ru.timofeeva.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageRgs{
    @FindBy(xpath = "//ol/li/a[contains(text(),'Страхование')]")
    public WebElement openMenu;

    public BasePageRgs getNext(){
        System.out.println("2. Выбрать пункт меню - Страховани");
        clickElement(openMenu);
        return new RgsMenu();
    }

}
