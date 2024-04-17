    package Selenium;

    import org.junit.Test;
    import org.junit.Before;
    import org.junit.After;
    import static org.junit.Assert.*;
    import static org.hamcrest.CoreMatchers.is;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.Dimension;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;
    import java.util.*;

    public class RegisterMemberTest {
        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void setUp() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait

        }

        @After
        public void tearDown() {
            driver.quit();
        }

        @Test
        public void registerMember() throws InterruptedException {
            driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
            driver.manage().window().setSize(new Dimension(1280, 984));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
            driver.findElement(By.id("dp")).sendKeys("11/02/1997");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys("R");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname"))).sendKeys("P");

            // Generate a random number and append it to the email address
            Random random = new Random();
            int randomNumber = random.nextInt(10000);
            String emailAddress = "RP" + randomNumber + "@email.com";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress"))).sendKeys(emailAddress);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress"))).sendKeys(emailAddress);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password"))).sendKeys("7777777");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword"))).sendKeys("7777777");
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) > label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(2) > label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(7) > label"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("join"))).click();
            assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div.page-content-wrapper > div > div > h5"))).getText(), is("Your Basketball England Membership Number is:"));
        }
    }