package Selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterMemberTest2 {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void registerMember() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().setSize(new Dimension(1280, 984));
        driver.findElement(By.id("dp")).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
        driver.findElement(By.id("member_firstname")).click();
        driver.findElement(By.id("member_firstname")).sendKeys("J");
        driver.findElement(By.cssSelector(".row:nth-child(7) > .col-sm-6:nth-child(1)")).click();
        driver.findElement(By.id("member_emailaddress")).sendKeys("RP6346@email.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("RP6346@email.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("754754");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("754754");
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) > label")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) > label")).click();
        driver.findElement(By.name("join")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".warning > span"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        assertThat(driver.findElement(By.cssSelector("#signup_form > div:nth-child(6) > div:nth-child(2) > div > span > span")).getText(), is("Last Name is required"));
    }
}
