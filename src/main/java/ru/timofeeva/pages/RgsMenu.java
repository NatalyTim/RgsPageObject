package ru.timofeeva.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.timofeeva.Init;

public class RgsMenu extends BasePageRgs{

    @FindBy(xpath = "//a[contains(text(),'Выезжающим за рубеж')]")
    public WebElement insuranceCategory;

    public BasePageRgs getNext(){
        System.out.println("3. Путешествия – Страхование выезжающих за рубеж");
        clickElement(insuranceCategory);
        return new OnlineCalculate();
    }





}
