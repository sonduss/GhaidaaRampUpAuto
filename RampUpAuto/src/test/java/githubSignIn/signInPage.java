package githubSignIn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

public class signInPage {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @DataProvider(name = "browserProvider", parallel = true)
    public Object[][] BrowserData() {
        return new Object[][] { { "chrome" }, { "firefox" }, { "edge" }, { "opera" }, { "safari" } };
    }

    @BeforeTest
    public void beforeAllTest(String broswer){
        WebDriver driverInstance = null;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driverInstance = new EdgeDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driverInstance = new OperaDriver();
                break;
            case "safari":
                driverInstance = new SafariDriver();
                break;
            default:
                System.out.println("Error");
        }
        driver.set(driverInstance);
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://github.com/login");
//
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void elementsSetup(){
        WebElement userNameOrEmail = driver.findElement(By.id("login_field"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement signInButton = driver.findElement(By.class("btn btn-primary btn-block js-sign-in-button"));

    }

    public boolean isInvalidDataMessage(){
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div"));
        return errorMessage.isDisplayed();
    }

    @Test
    public boolean isSuccessfulSignIn(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(), "GitHub");
        return true;
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }




}
