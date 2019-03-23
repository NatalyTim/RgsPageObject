import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.timofeeva.pages.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PageObjectTest extends BaseTest {

    public static TravelInsurance pageObject;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"IVAN NIKOLAEV", "15.02.1966", true, false},
                {"SVETA LI", "11.12.1991", false, false},
                {"PETR PETROV", "15.02.1966", true, true}});
    }

    @Parameterized.Parameter
    public String fullName;

    @Parameterized.Parameter(1)
    public String birthDay;

    @Parameterized.Parameter(2)
    public boolean needActiveSport;

    @Parameterized.Parameter(3)
    public boolean lastParameter;

    @BeforeClass
    public static void openForm() throws Exception {
        MainPage mainPage = new MainPage();
        RgsMenu rgsMenu = (RgsMenu) mainPage.getNext();
        OnlineCalculate buttonCalculate = (OnlineCalculate) rgsMenu.getNext();
        TravelInsurance travelInsurance = (TravelInsurance) buttonCalculate.getNext();
        pageObject = travelInsurance;
    }


    @Test
    public void rgsMenuTest() throws Exception {
        pageObject.setComparableElement();
        pageObject.setNumberOfTrips();
        pageObject.setDirection("Шен");
        pageObject.setCountryList("Испания");
        pageObject.setFirstDate();
        pageObject.setDays();
        pageObject.setFullName(fullName);
        pageObject.setBirthDay(birthDay);
        pageObject.setActiveOrNot();
        if (lastParameter) {
            pageObject.setCheckBox();
            CheсkPage cheсkPage = (CheсkPage)pageObject.getNext();
            cheсkPage.checkElements();
            cheсkPage.checkElementsWithParameter(fullName, birthDay);
        }
    }

}
