package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
/**
 * Hello world!
 *
 */
public class App 
{WebDriver driver;
        WebDriverManager.chromedriver().setup();
    // driver.chromedriver().setup();
    driver = new ChromeDriver();

        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    WebElement googleSearch = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]"));
        googleSearch.sendKeys("Selenium Tutorials");
        googleSearch.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(driver.getTitle().equals("Selenium Tutorials")){
    System.out.println("Test Pass");
}
        driver.close();

    }
}
