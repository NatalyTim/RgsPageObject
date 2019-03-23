import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import ru.timofeeva.Init;
import ru.timofeeva.config.ConfigProperties;
import ru.timofeeva.pages.Config;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public static Properties properties = ConfigProperties.getInstance().getProperties();

    @BeforeClass
    public static void beforeEach() {
        String driverName = properties.getProperty("driverPropertyName");
        String parh = properties.getProperty("pathToDriver");
        String url = properties.getProperty("url");
        String browser = properties.getProperty("browser");
        if (browser == null || "".equals(browser))
            browser = Config.CHROME;
        // Init.setBrowser(properties.getProperty("url"));
        System.setProperty(driverName, parh);
        Init.setBrowser(browser);

        switch (browser) {
            case Config.FIREFOX:
                Init.setDriver(new FirefoxDriver());
                break;
            case Config.IE:
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.requireWindowFocus();
                Init.setDriver(new InternetExplorerDriver());
                break;
            default:
                Init.setDriver(new ChromeDriver());
        }
        Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Init.getDriver().manage().window().maximize();
        Init.getDriver().get(url);
    }
//        if (FIREFOX.equalsIgnoreCase(Init.getBrowser())) {
//            System.out.println(" FireFox");
//            System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
//            Init.setDriver(new FirefoxDriver());
//            Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            Init.getDriver().manage().window().maximize();
//            Init.setBrowser(FIREFOX);
//            Init.getDriver().get(url);
//        } else if (IE.equalsIgnoreCase(Init.getBrowser())) {
//            System.out.println(" Internete Explorer");
//            System.setProperty("webdriver.ie.driver", "drv1/IEDriverServer.exe");
//            InternetExplorerOptions options = new InternetExplorerOptions();
//            options.requireWindowFocus();
//            Init.setDriver(new InternetExplorerDriver());
//            Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            Init.getDriver().manage().window().maximize();
//            Init.setBrowser(IE);
//            Init.getDriver().get(url);
//        } else {
//            System.out.println("browser = " + Init.getBrowser());
//            System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
//            Init.setDriver(new ChromeDriver());
//            Init.setBrowser(Config.CHROME);
//            Init.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            Init.getDriver().manage().window().maximize();
//            Init.getDriver().get(url);
//        }


    @AfterClass
    public static void afterEach() {
        Init.getDriver().close();
        Init.getDriver().quit();
    }
}
