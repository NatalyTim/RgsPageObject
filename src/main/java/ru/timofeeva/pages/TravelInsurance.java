package ru.timofeeva.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

public class TravelInsurance extends BasePageRgs {
    @FindBy(xpath = "//div/span[@class='h1']")
    public WebElement comparableElement;

    @FindBy(xpath = "//form[contains(@data-bind,'calculation')]" +
            "/div[contains(@data-bind,'validationApply')]/adaptive-checkbox/label")
    public WebElement checkBox;

    @FindBy(xpath = "//*[contains(text(),'Несколько')]")
    public WebElement numberOfTrips;

    @FindBy(id = "Countries")
    public WebElement direction;

    @FindBy(name = "ArrivalCountryList")
    public WebElement countryList;

    @FindBy(xpath = "//input[contains(@data-bind, 'FirstDepartureDate')]")
    public WebElement firstDate;

    @FindBy(xpath = "//div[contains(@class, 'datepicker-days')]/table/thead/tr/th[contains(@class, 'next')]")
    public WebElement nextMonth;

    @FindBy(xpath = "//*[contains(@data-bind,'btnRadioGroupValue: 90')]")
    public WebElement days;

    @FindBy(xpath = "//input[contains(@class,'form-control')][@data-test-name='FullName']")
    public WebElement fullName;

    @FindBy(xpath = "//*[@data-test-name='BirthDate']")
    public WebElement birthDay;

    @FindBy(xpath = "//div[contains(@data-bind,'activeRestOrSportsToggle')]/div[contains(@class,  'toggle-rgs')]")
    public WebElement activeOrNot;

    @FindBy(xpath = "//*[@data-test-name='NextButton'][contains(@data-bind,'Misc.NextButton')]")
    public WebElement buttonCalculate;

    public void setComparableElement() throws Exception {
        System.out.println("5. Проверить наличие заголовка – Страхование выезжающих за рубеж");
        compareText(comparableElement, "Страхование выезжающих за рубеж");
    }

    public void setCheckBox() throws Exception {
        System.out.println("5.5 Я согласен на обработку данных  - выбрать чекбокс");
        scrollToAndClickElement(checkBox);
    }

    public void setNumberOfTrips() throws Exception {
        System.out.println("6. Заполнить форму: Несколько поездок в течении года");
        scrollToAndClickElement(numberOfTrips);
    }

    public void setDirection(String direction) throws Exception{
        System.out.println("7.Куда едем – Шенген");
        scrollToAndClickElement(this.direction);
        this.direction.sendKeys(direction);
        this.direction.sendKeys(Keys.TAB);
    }

    public void setCountryList(String countryList) throws Exception {
        System.out.println("8.Страна въезда – Испания");
        for(int i = 0 ; i < 10; i++) {
            try {
                scrollToElement(this.countryList);
                new Select(this.countryList).selectByVisibleText(countryList);
                break;
            }catch(Exception ex){
                if(i>= 9)
                    throw new Exception("Невозможно найти элемент!");
                continue;
            }
        }
    }

    public void setFirstDate() throws Exception {
        System.out.println("9.Дата первой поездки");
        scrollToAndClickElement(this.firstDate);
        setFirstDateOfTrip(nextMonth);
    }

    public void setDays() throws Exception {
        System.out.println("10.Сколько дней планируете пробыть за рубежом за год – не более 90");
        scrollToAndClickElement(days);
    }

    public void setFullName(String fullNameString) throws Exception {
        System.out.println("11.ФИО");
 //       element = driver.findElement(By.xpath("//input[contains(@class,'form-control')][@data-test-name='FullName']"));
        scrollToAndClickElement(this.fullName);
        this.fullName.clear();
        String[] strNames = fullNameString.split(" ");
        Arrays.stream(strNames).forEach(s ->{
            this.fullName.sendKeys(s);
            this.fullName.sendKeys(" ");

        });
        //this.fullName.sendKeys(fullNameString);


    }

    public void setBirthDay(String birthDay) throws Exception {
        System.out.println("12.Дата рождения");
        for (int i = 0; i < 10; i++) {
            try {
                scrollToElement(this.birthDay);
                printElement(this.birthDay);
                this.birthDay.click();
                this.birthDay.clear();
                this.birthDay.sendKeys(birthDay);
                this.birthDay.sendKeys(Keys.TAB);
                break;
            } catch (Exception e) {
                if (i >= 9)
                    throw new Exception("Невозможно найти элемент!");
                continue;
            }
        }
    }

    public void setActiveOrNot() throws Exception {
        System.out.println("13.Планируется активный отдых");
        scrollToAndClickElement(this.activeOrNot);
    }

    public TravelInsurance fillForm() throws Exception {
        setComparableElement();
        return new TravelInsurance();
    }

    @Override
    public BasePageRgs getNext() throws Exception{
        System.out.println("15. Нажать рассчитать");
        scrollToAndClickElement(buttonCalculate);
        return new CheсkPage();
    }
}
