package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageUIs.HomePageUI;

public class HomePageSteps extends BasePage {
	WebDriver driver;
	String homePageUrl;
	
	public HomePageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
	}
	
	@Given("^Get home page Url$")
	public String openHomePage()  {
		return getCurrentUrl(driver);
	}

	@When("^Click to Register link$")
	public void clickToRegisterButton()  {
	    waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
	    clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

}
