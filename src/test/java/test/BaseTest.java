package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.BasePage;

public class BaseTest extends BasePage {

	@BeforeSuite
	public void beforeClass() {
		navigateTo("https://knockrentals.github.io/doorway-v3/");
	}

	@AfterSuite
	public void afterClass() {
		exit();
	}
}
