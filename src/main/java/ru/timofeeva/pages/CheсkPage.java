package ru.timofeeva.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheсkPage extends BasePageRgs {
    //Проверить значения"
    @FindBy(xpath = "//span[contains(@class,'summary-value')][@data-bind='with: Trips']/span[@class='text-bold']")
    public WebElement comparableElementTrips;
    @FindBy(xpath = "//span/span[contains(@data-bind,'foreach: countries')]/strong")
    public WebElement comparableElementDirection;
    @FindBy(xpath = "//strong[contains(@data-bind,'LastName')]")
    public WebElement comparableElementFullName;
    @FindBy(xpath = "//strong[contains(@data-bind,' text: BirthDay.')]")
    public WebElement comparableElementBirthDay;
    @FindBy(xpath = "//div[contains(@data-bind, 'SelectedProgram.Options')]" +
            "/div[contains(@data-bind, 'Активный')]/div[@class='summary-row']/span[@class='summary-value']/span")
    public WebElement comparableElementActiveOrNot;

    public void checkElements() throws Exception {
        System.out.println("16.Проверить значения");
        System.out.println("Многократные поездки в течение года");
        compareText(comparableElementTrips, "Многократные поездки в течение года");
        System.out.println("16.1 Территория – Шенген");
        compareText(comparableElementDirection, "Шенген");
        compareText(comparableElementActiveOrNot, "Активный");
    }
    public void checkElementsWithParameter(String name,String birthday) throws Exception{
        System.out.println("16.2 Застрахованный");
        compareText(comparableElementFullName, name);
        System.out.println("16.3 Дата рождения");
        compareText(comparableElementBirthDay, birthday);
    }
    @Override
    public BasePageRgs getNext() {
        return null;
    }
}
