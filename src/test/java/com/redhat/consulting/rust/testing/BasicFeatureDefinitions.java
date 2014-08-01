package com.redhat.consulting.rust.testing;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mrice on 8/1/14.
 */
@SuppressWarnings("unused")
public class BasicFeatureDefinitions {

    WebDriver driver;

    @Given("^that I have a web browser$")
    public void that_I_have_a_web_browser() throws Throwable {
        driver = SharedFirefoxDriver.currentDriver();
    }

    @When("^I access \"(.*?)\"$")
    public void i_access(String url) throws Throwable {
        assertNotNull(url);
        driver.get(url);
    }

    @Then("^the browser should load a page titled \"(.*?)\"$")
    public void the_browser_should_load_a_page_titled(String expectedTitle) throws Throwable {

        String actualTitle = driver.getTitle();
        assertNotNull("The title should not be null", actualTitle);

        String testMessage = String.format("The title should be \"%s\" but was actually \"%s\"", expectedTitle, actualTitle);
        assertTrue(testMessage, actualTitle.equals(expectedTitle));

        SharedFirefoxDriver.close();

    }

}
