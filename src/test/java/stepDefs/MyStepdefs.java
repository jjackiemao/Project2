package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Random;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private String emailAddress;
    private String enteredPassword;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1280, 984));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I have entered my date of birth")
    public void iHaveEnteredMyDateOfBirth() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
    }

    @And("I have entered my first name {string}")
    public void iHaveEnteredMyFirstName(String firstName) {
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
    }

    @And("I have entered my last name {string}")
    public void iHaveEnteredMyLastName(String lastName) {
        if (!lastName.isEmpty()) {
            driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        }
    }

    @And("I have entered my email {string}")
    public void iHaveEnteredMyEmail(String email) {
        // Use the provided email argument
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        emailAddress = "RP" + randomNumber + "@example.com";
        driver.findElement(By.id("member_emailaddress")).sendKeys(emailAddress);
    }

    @And("I have confirmed my email {string}")
    public void iHaveConfirmedMyEmail(String email) {
        // Confirm the email address
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(emailAddress);
    }

    @And("I have entered my password {string}")
    public void iHaveEnteredMyPassword(String password) {
        // Generate and enter a random password
        enteredPassword = password;
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(enteredPassword);
    }

    @And("I have confirmed my password {string}")
    public void iHaveConfirmedMyPassword(String password) {
        // Confirm the password
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(enteredPassword);
    }

    @And("I have checked the Terms and Conditions")
    public void iHaveCheckedTheTermsAndConditions() {
        // Check the Terms and Conditions checkbox
        driver.findElement(By.id("#signup_form > div:nth-child(12) > div > div:nth-child(7) > label > span.check")).click();
    }

    @And("I have checked {string} and {string}")
    public void iHaveCheckedAnd(String checkbox1, String checkbox2) {
        // Check the specified checkboxes
        driver.findElement(By.id("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label > span.check")).click();
        driver.findElement(By.id("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.check")).click();
    }

    @When("I press the join button")
    public void iPressTheJoinButton() {
        // Click the join button
        driver.findElement(By.name("join")).click();
    }

    @Then("I should see {string}")
    public void iShouldSee(String outcome) {
        // Check the expected outcome message
        String expectedMessage = switch (outcome) {
            case "I should see a success message" -> "Your Basketball England Membership Number is:";
            case "I should see an error message" -> "Last Name is required";
            case "The registration was not successful" -> "Password did not match";
            case "The registration has failed for not accepting the terms and conditions" ->
                    "You must confirm that you have read, understood and agree to the Code of Ethics and Conduct";
            default -> "";
        };
        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div.page-content-wrapper > div > div > h5"))).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
