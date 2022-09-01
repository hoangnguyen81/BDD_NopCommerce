package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class RegisterPageSteps extends BasePage{
	WebDriver driver;
	String errorMessageRequiredFirstNameTextbox = "First name is required.";
	String errorMessageRequiredLastNameTextbox = "Last name is required.";
	String errorMessageRequiredEmailTextbox ="Email is required.";
	String errorMessageRequiredPasswordTextbox ="Password is required.";
	String errorMessageRequiredConfirmPasswordTextbox ="Password is required.";
	String errorMessageWrongEmail = "Wrong email";
	String successRegisterMessage = "Your registration completed";
	
	public RegisterPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
	}
	@When("^Click to Register button$")
	public void clickToRegisterButton()  {
	    waitForElementClickable(driver,RegisterPageUI.REGISTER_BUTTON);
	    clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	@Then("^Verify error message at First name textbox$")
	public void verifyErrorMessageAtFirstNameTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_FIRST_NAME_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_FIRST_NAME_LABEL),errorMessageRequiredFirstNameTextbox);
	}
	
	@Then("^Verify error message at Last name textbox$")
	public void verifyErrorMessageAtLastNameTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_LAST_NAME_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_LAST_NAME_LABEL),errorMessageRequiredLastNameTextbox);
	}

	@Then("^Verify error message at Email textbox$")
	public void verifyErrorMessageAtEmailTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_LABEL),errorMessageRequiredEmailTextbox);
	}

	@Then("^Verify error message at Password textbox$")
	public void verifyErrorMessageAtPasswordTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_PASSWORD_LABEL),errorMessageRequiredPasswordTextbox);
	}
	
	@Then("^Verify error message at Confirm password textbox$")
	public void verifyErrorMessageAtConfirmPasswordTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_CONFIRM_PASSWORD_LABEL),errorMessageRequiredConfirmPasswordTextbox);
	}
	
	@Given("^Open Register page$")
	public void openRegisterPage()  {
	    waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
	    clickToElement(driver,HomePageUI.REGISTER_LINK);
	}
	
	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String fieldName, String value)  {
	   waitForElementVisible(driver, RegisterPageUI.DYNAMIC_TEXTBOX, fieldName);
	   sendKeyToElement(driver, RegisterPageUI.DYNAMIC_TEXTBOX, value, fieldName);
	}
	
	@Then("^Verify error message invalid email at Email textbox$")
	public void verifyErrorMessageInvalidEmailAtEmailTextbox()  {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_LABEL);
	    Assert.assertEquals(getElementText(driver, RegisterPageUI.ERROR_MESSAGE_EMAIL_LABEL), errorMessageWrongEmail);
	}
	
	@Then("^Verify error message register successfully$")
	public void verifyErrorMessageRegisterSuccessfully() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE_REGISTER_LABEL);
		Assert.assertEquals(getElementText(driver, RegisterPageUI.SUCCESS_MESSAGE_REGISTER_LABEL), successRegisterMessage);
	}
	
}
