package pageUIs;

public class RegisterPageUI {
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";

	public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String DYNAMIC_TEXTBOX = "//label[contains(text(),'%s')]//following-sibling::input";

	public static final String ERROR_MESSAGE_FIRST_NAME_LABEL = "//span[@id='FirstName-error']";
	public static final String ERROR_MESSAGE_LAST_NAME_LABEL = "//span[@id='LastName-error']";
	public static final String ERROR_MESSAGE_EMAIL_LABEL = "//span[@id='Email-error']";
	public static final String ERROR_MESSAGE_PASSWORD_LABEL = "//span[@id='Password-error']";
	public static final String ERROR_MESSAGE_CONFIRM_PASSWORD_LABEL = "//span[@id='ConfirmPassword-error']";
	public static final String ERROR_MESSAGE_EXISTED_EMAIL_LABEL = "//div[contains(@class,'message-error')]//li";

	public static final String SUCCESS_MESSAGE_REGISTER_LABEL = "//div[@class='result']";
}
