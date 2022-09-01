package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void getUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		 driver.navigate().forward();
	 }
	 
	public void refreshCurrentPage(WebDriver driver) {
		 driver.navigate().refresh();
	 }
	
	public Set<Cookie> getAllCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}
	 
	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for(Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}
	
	protected Alert waitForAlertPresence(WebDriver driver) {
		 WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		 return explicitWait.until(ExpectedConditions.alertIsPresent());
	 }
	 
	protected void acceptAlert(WebDriver driver) {
		 waitForAlertPresence(driver).accept();
	 }
	 
	protected void cancelAlert(WebDriver driver) {
		 waitForAlertPresence(driver).dismiss();
	 }
	 
	protected String getTextAlert(WebDriver driver) {
		 return waitForAlertPresence(driver).getText();
	 }
	 
	protected void sendKeyToAlert(WebDriver driver, String textValue) {
		 waitForAlertPresence(driver).sendKeys(textValue);
	 }
	 
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		 Set<String> allWindowIDs = driver.getWindowHandles();
		 for(String id : allWindowIDs) {
			 if(!id.equals(windowID)) {
				 driver.switchTo().window(id);
				 break;
			 }
		 }
	 }
	 
	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		 Set<String> allWindowIDs = driver.getWindowHandles();
		 for(String id : allWindowIDs) {
			 driver.switchTo().window(id);
			 if(driver.getTitle().equals(tabTitle))
				 break;
		 }
	 }
	 
	protected void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		 Set<String> allWindowIDs = driver.getWindowHandles();
		 for(String id : allWindowIDs) {
			 if(!id.equals(parentID)) {
				 driver.switchTo().window(id);
				 driver.close();
			 }
		 }
		 driver.switchTo().window(parentID);
	 }
	 
	protected WebElement getWebElement(WebDriver driver, String xpathLocator) {
		 return driver.findElement(getByXpath(xpathLocator));
	 }
	
	protected WebElement getWebElement(WebDriver driver, String xpathLocator, String...dynamicValue) {
		 return driver.findElement(getByXpath(getDynamicXpath(xpathLocator, dynamicValue)));
	 }
	 
	protected List<WebElement> getListWebElements(WebDriver driver, String xpathxpathLocator) {
		 return driver.findElements(getByXpath(xpathxpathLocator));
	 }
	 
	 private By getByXpath(String xpathxpathLocator) {
		 return By.xpath(xpathxpathLocator);
	 }
	 
	 private By getByLocator(String locatorType) {
		 By by = null;
		 if(locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			 by = By.id(locatorType.substring(3));
		 }else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class="))  {
			 by = By.className(locatorType.substring(6));
		 }else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name="))  {
			 by = By.name(locatorType.substring(5));
		 }else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css="))  {
			 by = By.cssSelector(locatorType.substring(4));
		 }else if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			 by = By.xpath(locatorType.substring(6));
		 }else {
			 throw new RuntimeException("Locator type is not supported.");
		 }
		 return by;
	 }
	 
	 private String getDynamicXpath(String locatorType, String...dynamicValues) {
		 locatorType = String.format(locatorType, (Object[]) dynamicValues);
		 return locatorType;
	 }
	 
	 protected void clickToElement(WebDriver driver, String xpathLocator) {
		 getWebElement(driver, xpathLocator).click();
	 }
	 
	 protected void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		 getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues)).click();
	 }
	 
	 protected void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 
	 protected void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		 WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues));
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 
	 protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator,String textItem) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 select.selectByVisibleText(textItem);
	 }
	 
	 protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator,String textItem, String...dynamicValues) {
		 Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		 select.selectByVisibleText(textItem);
	 }
	 
	 protected String getSelectedItemInDefaultDropdown(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.getFirstSelectedOption().getText();
	 }
	 
	 protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.isMultiple();
	 }
	 
	 protected void selectItemInCustomDropdown(WebDriver driver, String xpathLocator, String xpathItemLocators, String textItem) {
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		 
		 jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", getWebElement(driver, xpathLocator));
		 explicitWait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver, xpathLocator))).click();
		 getWebElement(driver, xpathLocator).click();
		 explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathItemLocators)));
		 List<WebElement> allItems =  getListWebElements(driver,xpathItemLocators);
		for(WebElement item : allItems) {
			 if(item.getText().equals(textItem)) {
				 jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				 sleepInSecond(1);
				 explicitWait.until(ExpectedConditions.elementToBeClickable(item)).click();
				 break;
			 }
		 }
	 }
	 
		protected void selectIemInEditDropdown(WebDriver driver, String parentLocator, String itemLocators, String expectedItem) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			
			jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", getWebElement(driver, parentLocator));
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(parentLocator)));
			WebElement parentElement = getWebElement(driver, parentLocator);
			parentElement.clear();
			parentElement.sendKeys(expectedItem);
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(itemLocators)));
			List<WebElement> allItems = getListWebElements(driver, itemLocators);
			for(WebElement item : allItems) {
				if(item.getText().equals(expectedItem)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", item);
					sleepInSecond(1);
					explicitWait.until(ExpectedConditions.elementToBeClickable(item));
					item.click();
					break;
				}
			}
		}
		
		protected void selectItemsInEditCustomDropdown(WebDriver driver, String parentLocator, String itemLocators, List<String> expectedItems) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			
			jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", getWebElement(driver, parentLocator));
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(parentLocator)));
			getWebElement(driver, parentLocator).click();
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(itemLocators)));
			List<WebElement> allItems = getListWebElements(driver, itemLocators);
			for(WebElement item : allItems) {
				for(String expectedItem : expectedItems) {
					if(item.getText().equals(expectedItem)) {
						jsExecutor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", item);
						sleepInSecond(1);
						explicitWait.until(ExpectedConditions.elementToBeClickable(item));
						item.click();
						break;
					}
				}
			}
		}
	 
	 protected String getElementText(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).getText();
	 }
	 
	 protected String getElementText(WebDriver driver, String xpathLocator, String...dynamicValues) {
		 return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getText();
	 }
	 
	 protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String...dynamicValues) {
		 return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getAttribute(attributeName);
	 }
	 
	 protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		 return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	 }
	 
	 protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		 return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	 }
	 
	 protected String getHexaColorFromRGBA(String rgbaValue) {
		 return Color.fromString(rgbaValue).asHex();
	 }

	 protected int getElementSize(WebDriver driver, String xpathLocator) {
		 return getListWebElements(driver, xpathLocator).size();
	 }
	 
	 protected int getElementSize(WebDriver driver, String xpathLocator, String...dynamicValues) {
		 return getListWebElements(driver, getDynamicXpath(xpathLocator, dynamicValues)).size();
	 }
	 
	 protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if(!element.isSelected()) {
			 element.click();
		 }
	 }
	 
	 protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String...dynamicValue) {
		 WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValue));
		 if(!element.isSelected()) {
			 element.click();
		 }
	 }
	 
	 protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if(element.isSelected()) {
			 element.click();
		 }
	 }
	 
	 protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String...dynamicValue) {
		 WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValue));
		 if(element.isSelected()) {
			 element.click();
		 }
	 }
	 
	 protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isDisplayed();
	 }
	 
	 protected boolean isElementDisplayed(WebDriver driver, String xpathLocator, String...dynamicValues) {
		 return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isDisplayed();
	 }
	 
	 protected boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		 boolean status = false;
		overrideGlobalTimeout(driver, 5);
		List<WebElement> elements = getListWebElements(driver, xpathLocator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if(elements.size() == 0) {
			status = true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()) {
			status = true;
		}
		return status;
	 }
	 
	 private void overrideGlobalTimeout(WebDriver driver, long timeoutInsecond) {
		driver.manage().timeouts().implicitlyWait(timeoutInsecond, TimeUnit.SECONDS);
	}

	protected boolean isElementEnable(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isEnabled();
	 }
	 
	 protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isSelected();
	 }
	 
	 protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		 driver.switchTo().frame(getWebElement(driver, xpathLocator));
	 }
	 
	 protected void switchToDefaultContent(WebDriver driver) {
		 driver.switchTo().defaultContent();
	 }
	 
	 protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		 Actions action = new Actions(driver);
		 action.moveToElement(getWebElement(driver, xpathLocator)).perform();;
	 }
	
	 protected void doubleClick(WebDriver driver, String xpathLocator) {
		 Actions action = new Actions(driver);
		 action.doubleClick(getWebElement(driver, xpathLocator)).perform();
	 }
	 
	 protected void rightClick(WebDriver driver, String xpathLocator) {
		 Actions action = new Actions(driver);
		 action.contextClick(getWebElement(driver, xpathLocator)).perform();
	 }
	 
	 protected void sendKeyToElementByAction(WebDriver driver, String xpathLocator, String textValue) {
		 Actions action = new Actions(driver);
		 action.sendKeys(getWebElement(driver, xpathLocator), textValue).perform();
	 }
	 
	 protected void pressKeyToElementByAction(WebDriver driver, String xpathLocator, Keys key) {
		 Actions action = new Actions(driver);
		 action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	 }
	 
	 protected void pressKeyToElementByAction(WebDriver driver, String xpathLocator, Keys key, String...dynamicValues) {
		 Actions action = new Actions(driver);
		 action.sendKeys(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	 }
	 
	 protected Object executeForBrowser(WebDriver driver, String javaScript) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return jsExecutor.executeScript(javaScript);
		}

	 protected String getInnerText(WebDriver driver) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		}

	 protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			String textActual = (String) jsExecutor
					.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			return textActual.equals(textExpected);
		}

	 protected void scrollToBottomPage(WebDriver driver) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		}

	 protected void navigateToUrlByJS(WebDriver driver, String url) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

	 protected void highlightElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement element = getWebElement(driver, xpathLocator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					"border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					originalStyle);
		}

	 protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
		}

	 protected void scrollToElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
		}

	 protected void sendkeyToElementByJS(WebDriver driver, String xpathLocator, String value) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
					getWebElement(driver, xpathLocator));
		}

	 protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
		}

	 protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						return true;
					}
				}
			};

			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
				}
			};

			return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		}

	 protected String getWebElementValidationMessage(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
					getWebElement(driver, xpathLocator));
		}

	 protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					getWebElement(driver, xpathLocator));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
	 
	 protected boolean isImageLoaded(WebDriver driver, String xpathLocator, String...dynamicValues) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
		
	 protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
		}
	 
	 protected void waitForElementVisible(WebDriver driver, String xpathLocator, String...dynameValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpath(xpathLocator, dynameValues))));
		}
		
	 protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
		}
	 
	 protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator, String...dynameValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicXpath(xpathLocator, dynameValues))));
		}
		
	 protected void waitForElementInVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		}
	 
	 /*
	  * Wait for ele undisplayed in DOM or not in DOM
	  * */
	 protected void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
			overrideGlobalTimeout(driver, shortTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
			overrideGlobalTimeout(driver, longTimeout);
		}
	 
	 protected void waitForElementInVisible(WebDriver driver, String xpathLocator, String...dynameValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicXpath(xpathLocator, dynameValues))));
		}
		
	 protected void waitForAllElementsInVisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, xpathLocator)));
		}
	 
	 protected void waitForAllElementsInVisible(WebDriver driver, String xpathLocator, String...dynameValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(xpathLocator, dynameValues))));
		}
		
	 protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		}
	 
	 protected void waitForElementClickable(WebDriver driver, String xpathLocator, String...dynameValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpath(xpathLocator, dynameValues))));
		}

		
		private long longTimeout = 30;
		private long shortTimeout = 5;
		
		protected void sleepInSecond(long timeInSecond) {
			try {
				Thread.sleep(timeInSecond * 1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

}
