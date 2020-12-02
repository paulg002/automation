package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public static WebDriver driver = WebdriverUtil.getInstance();
	protected static WebDriverWait wait;
	protected static JavascriptExecutor js;
	protected static Actions build;
	protected static final long SECOND = 60;

	public void navigateTo(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void exit() {
		driver.close();
	}


	public void waitForVisibility(String xpath) {
		wait = new WebDriverWait(driver, SECOND);
		js = (JavascriptExecutor) driver;
		build = new Actions(driver);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath( xpath))));
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
	}

	public void gotoDefault() {
		driver.switchTo().defaultContent();
	}

	public static void scrollTo( WebElement element ) {
		((JavascriptExecutor) driver).
				executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickElement( WebElement element) {
		try {
			element.click();
		} catch (ElementNotInteractableException error) {
			((JavascriptExecutor) driver).
					executeScript("arguments[0].click();", element);

		}
	}

}
