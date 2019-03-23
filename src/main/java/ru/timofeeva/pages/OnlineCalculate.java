package ru.timofeeva.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnlineCalculate extends BasePageRgs {
    @FindBy(xpath = "//*[contains(text(),'Рассчитать')][contains(@class,'btn-attention')]")
    public WebElement buttonCalculate;

    public BasePageRgs getNext() throws Exception {
        System.out.println("4. Нажать рассчитать – Онлайн");
        scrollToAndClickElement(buttonCalculate);
        return new TravelInsurance();
    }
}
