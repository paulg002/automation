package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;

public class Homepage extends BasePage {


	String SCHEDULE_A_TOUR = "//button[text()='Schedule a Tour']";

	@FindBy(className = "doorway-notification-frame")
	private WebElement iframe;


	public Homepage() {
		PageFactory.initElements(driver, this);
	}

	public void clickScheduleTourButton() {
		driver.switchTo().frame(iframe);

		scrollTo(driver.findElement(By.xpath( SCHEDULE_A_TOUR )));

		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf((iframe)));
		}catch(TimeoutException e){
			//if gets here, anyway should be loaded by now, continue
			System.out.println(e.getStackTrace()[0]);
		}

		driver.findElement(By.xpath( SCHEDULE_A_TOUR )).click();
		driver.switchTo().defaultContent();
	}


}
