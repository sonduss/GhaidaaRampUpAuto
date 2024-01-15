package githubSignIn;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
public class signInTest {
    //signInPage signInPage = new signInPage(driver.get());
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String baseUrl = "https://github.com/login";


    @BeforeMethod
    @Parameters("browser")
    public void broswerSetup(@Optional("chrome") String browser){
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver.set(new OperaDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Invalid browser specified");
        }
        driver.get().get(baseUrl);
    }


    @DataProvider (name = "githubCredintials")
    public Object[][] credintialsData() {
        return new Object [][] {
                {"asaltest19@gmail.com", "password123456789s*", true},
                { "asaltest1646449@gmail.com", "password123456789s*", false},
                {"asaltest19@gmail.com", "password123456789sgg", false},
                {"", "", false}
        };

    }

    public boolean isInvalidDataMessage(){
        WebElement errorMessage = driver.get().findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div"));
        return errorMessage.isDisplayed();
    }

    @Test
    public boolean isSuccessfulSignIn(){
        driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertEquals(driver.get().getTitle(), "GitHub · Where software is built");
        return true;
    }

    @Test(dataProvider = "githubCredintials")
    public void githubSignInTest(String userName, String password, boolean expectedResult){

        signInPage signInPage = new signInPage(driver.get());
        WebElement userNameOrEmail = driver.get().findElement(By.id("login_field"));
        WebElement passwordField = driver.get().findElement(By.id("password"));
        WebElement signInButton = driver.get().findElement(By.name("commit"));

        userNameOrEmail.sendKeys(userName);
        passwordField.sendKeys(password);
        signInButton.click();

        if (expectedResult) {
            Assert.assertTrue(isSuccessfulSignIn(), String.valueOf(true));
        }else{
            Assert.assertTrue(isInvalidDataMessage() , String.valueOf(false));
        }








    }

    @AfterMethod
    public void closeBrowser() {
        driver.get().quit();
    }




}
