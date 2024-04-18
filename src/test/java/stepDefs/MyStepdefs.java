package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.*;

public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private String emailAddress;
    private String enteredPassword;
    private String confirmPassword;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Increase the timeout to 10 seconds
        driver.manage().window().setSize(new Dimension(1280, 984));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I have entered my date of birth")
    public void iHaveEnteredMyDateOfBirth() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dp"))).click();
        driver.findElement(By.id("dp")).sendKeys("11/02/1997");
    }

    @And("I have entered my first name {string}")
    public void iHaveEnteredMyFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_firstname"))).sendKeys(firstName);
    }

    @And("I have entered my last name {string}")
    public void iHaveEnteredMyLastName(String lastName) {
        if (!lastName.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_lastname"))).sendKeys(lastName);
        }
    }

    @And("I have entered my email {string}")
    public void iHaveEnteredMyEmail(String email) {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        emailAddress = "RP" + randomNumber + "@email.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_emailaddress"))).sendKeys(emailAddress);
    }

    @And("I have confirmed my email {string}")
    public void iHaveConfirmedMyEmail(String email) {
        if (emailAddress != null) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("member_confirmemailaddress"))).sendKeys(emailAddress);
        } else {
            throw new IllegalStateException("Email address is not initialized.");
        }
    }

    @And("I have entered my password {string}")
    public void iHaveEnteredMyPassword(String password) {
        enteredPassword = password;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_password"))).sendKeys(enteredPassword);
    }

    @And("I have confirmed my password {string}")
    public void iHaveConfirmedMyPassword(String password) {
        confirmPassword = password;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signupunlicenced_confirmpassword"))).sendKeys(confirmPassword);
    }

    @And("I have checked the {string} and {string} and {string} for example {int}")
    public void iHaveCheckedThe(String checkbox1, String checkbox2, String checkbox3, int exampleIndex) {
        System.out.println("Received ExampleIndex: " + exampleIndex);

        // Check the "Terms and Conditions" checkbox if applicable
        if (exampleIndex != 4 && !checkbox1.equalsIgnoreCase("Do not click") && !checkbox1.equalsIgnoreCase("Code of Conduct")) {
            // Click the "Terms and Conditions" checkbox for test runs 1, 2, and 3
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label"))).click();
        }

        // Check the "Over 18" checkbox
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(2) > label"))).click();

        // Check the "Code of Conduct" checkbox
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".md-checkbox:nth-child(7) > label"))).click();
    }







    @When("I press the join button")
    public void iPressTheJoinButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("join")))
                .click();
    }


    @Then("I should see {string}")
    public void iShouldSee(String outcome) {
        switch (outcome) {
            case "I should see a success message":
                String successMessage = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
                String actualSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40"))).getText();
                assertEquals(successMessage, actualSuccessMessage);
                break;
            case "I should see an error message":
                String errorMessage = "Last Name is required";
                assertEquals(errorMessage, driver.findElement(By.cssSelector("#signup_form > div:nth-child(6) > div:nth-child(2) > div > span")).getText());
                break;
            case "The registration was not successful":
                String expectedMismatchMessage = "Password did not match";
                String actualMismatchMessage = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']")).getText();
                assertEquals(expectedMismatchMessage, actualMismatchMessage);
                break;
            case "The registration has failed for not accepting the terms and conditions":
                String expectedErrorMessage = "You must confirm that you have read and accepted our Terms and Conditions";
                String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span > span"))).getText();
                assertEquals(expectedErrorMessage, actualErrorMessage);
                break;
            default:
                throw new IllegalArgumentException("Unexpected outcome: " + outcome);
        }
    }
}
