package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebdriverUtil {

	protected static WebDriver driver = null;
	static ChromeOptions options;

	static {
		System.setProperty("webdriver.chrome.driver", "other/chromedriver");
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		//options.addArguments("--incognito");
	}

	public static WebDriver getInstance() {
		if (driver == null || driver.toString().contains("null")) {
			driver = new ChromeDriver(options);
		}
		return driver;
	}
}
