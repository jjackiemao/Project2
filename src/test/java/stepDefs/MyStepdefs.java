package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MyStepdefs {
    private WebDriver driver;
    private String emailAddress;
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

    @Given("I have entered date of birth")
    public void i_have_entered_date_of_birth() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().setSize(new Dimension(1280, 984));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
    }

    @Given("I have entered first name {string}")
    public void i_have_entered_first_name(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys(firstName);
    }

    @Given("I have entered last name {string}")
    public void i_have_entered_last_name(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname"))).sendKeys(lastName);
    }

    @Given("I have entered Email and confirmed Email {string}")
    public void i_have_entered_email_and_confirmed_email(String email) {
        // Generate a random number and append it to the email address
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        emailAddress = "RP" + randomNumber + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress"))).sendKeys(emailAddress);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress"))).sendKeys(emailAddress);
    }

    @Given("I have entered password and confirmed password")
    public void i_have_entered_password_and_confirmed_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password"))).sendKeys("7777777");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword"))).sendKeys("7777777");
    }

    @Given("I have checked Terms and condition")
    public void i_have_checked_terms_and_condition() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) > label"))).click();
    }

    @Given("I have entered checked Over 18 and code of conduct")
    public void i_have_entered_checked_over_18_and_code_of_conduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(2) > label"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(7) > label"))).click();
    }

    @When("I press the join button")
    public void i_press_the_join_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("join"))).click();
    }

    @Then("I am registered")
    public void i_am_registered() {
        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div.page-content-wrapper > div > div > h5"))).getText(), is("Your Basketball England Membership Number is:"));
    }


    // Cucumber step definitions
    @Given("I have entered my date of birth")
    public void iHaveEnteredMyDateOfBirth() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().setSize(new Dimension(1280, 984));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
    }

    @And("I have entered my first name {string}")
    public void iHaveEnteredMyFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys(firstName);
    }

    @And("I have left last name empty")
    public void iHaveLeftLastNameEmpty() {
        // Implementation goes here
    }

    @And("I have entered my Email and confirmed the Email")
    public void iHaveEnteredMyEmailAndConfirmedTheEmail() {
        // Generate a random number and append it to the email address
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        emailAddress = "RP" + randomNumber + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress"))).sendKeys(emailAddress);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress"))).sendKeys(emailAddress);
    }

    @And("I have entered my password and confirmed password")
    public void iHaveEnteredMyPasswordAndConfirmedPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password"))).sendKeys("7777777");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword"))).sendKeys("7777777");
    }

    @And("I have checked the Terms and condition")
    public void iHaveCheckedTheTermsAndCondition() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) > label"))).click();
    }

    @And("I have checked that i am over {int} and code of conduct")
    public void iHaveEnteredCheckedThatIAmOverAndCodeOfConduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(2) > label"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(7) > label"))).click();
    }

    @When("I press the join button to register")
    public void iPressTheJoinButtonToRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("join"))).click();
    }

    @Then("The registration has failed")
    public void theRegistrationHasFailed() {
        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".warning > span"))).getText(), is("Last Name is required"));
    }



    @Given("I have filled in my date of birth")
    public void iHaveFilledInMyDateOfBirth() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        driver.manage().window().setSize(new Dimension(1280, 984));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
    }

    @And("I have filled in my first name {string}")
    public void iHaveFilledInMyFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys(firstName);
    }

    @And("I have filled in my last name {string}")
    public void iHaveFilledInMyLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname"))).sendKeys(lastName);
    }

    @And("I have filled in my Email and confirmed the Email {string}")
    public void iHaveFilledInMyEmailAndConfirmedTheEmail() {
        // Generate a random number and append it to the email address
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        emailAddress = "RP" + randomNumber + "@example.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress"))).sendKeys(emailAddress);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress"))).sendKeys(emailAddress);
    }

    @And("I have entered password")
    public void iHaveEnteredPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password"))).sendKeys("4574577");
    }

    @And("I have misspelled confirmation of password")
    public void iHaveMisspelledConfirmationOfPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword"))).sendKeys("845745");
    }

    @And("I have accepted the Terms and condition")
    public void iHaveAcceptedTheTermsAndCondition() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) > label"))).click();
    }

    @And("I have accepted that I'm over {int} and code of conduct")
    public void iHaveAcceptedThatImOverAndCodeOfConduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(2) > label"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(7) > label"))).click();
    }

    @When("I have press the join button to register")
    public void iHavePressTheJoinButtonToRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(By.name("join"))).click();
    }

    @Then("The registration was not successful")
    public void theRegistrationWasNotSuccessful() {
        assertThat(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".warning > span"))).getText(), is("Password match"));
    }
}
