package ru.timofeeva.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.timofeeva.Init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public abstract class BasePageRgs {
    public BasePageRgs() {
        PageFactory.initElements(Init.getDriver(), this);
    }

    public abstract BasePageRgs getNext() throws Exception;


    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToAndClickElement(WebElement element) throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
               scrollToElement(element);
                printElement(element);
                clickElement(element);
                return;
            } catch (Exception e) {
                if (i >= 9)
                    throw new Exception("Невозможно найти элемент!");
                continue;
            }

        }
    }

    public static void printElement(WebElement element) {
        if (element == null) {
            System.out.println("Element is null");
        } else {
            System.out.println(element.getTagName() + " " + element.getText());
        }
    }

    public void compareText(WebElement element, String expect) throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                scrollToElement(element);
                assertTrue("Исходного текста нет: ",
                        expect.contains(element.getText()));
                System.out.println("Исходный текст есть:" + expect);
                return;
            } catch (Exception e) {
                if (i >= 9)
                    throw new Exception("Невозможно найти элемент!");
                continue;
            }
        }
    }


    public void clickElement(WebElement element) {
        if (Config.IE.equalsIgnoreCase(Init.getBrowser())) {
            ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].click();", element);
        } else
            element.click();
    }

    public void setFirstDateOfTrip(WebElement nextMonth) {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateInTwoWeeks = dateNow.plusDays(14);
        DateFormat df = new SimpleDateFormat();
        int reportDay = dateInTwoWeeks.getDayOfMonth();
        if (dateNow.getMonth() != dateInTwoWeeks.getMonth()) {
            clickElement(nextMonth);
            clickElement(
                    Init.getDriver().findElement(By.xpath("//div[contains(@class,'datepicker-days')]" +
                            "/table/tbody/tr/td[@class='day'][contains(text(), " + reportDay + ")]")));
        } else {
            clickElement(
                    Init.getDriver().findElement(By.xpath("//div[contains(@class,'datepicker-days')]" +
                            "/table/tbody/tr/td[@class='day'][contains(text(), " + reportDay + ")]")));
        }
    }

}