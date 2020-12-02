package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BasePage;

import java.util.List;

public class SchedulingTourPage extends BasePage {

	String IN_PERSON_TOUR ="inPersonTour";
	String VIDEO_TOUR ="liveVideoTour";
	String DATE_PICKER ="date";
	String NEXT_MONTH ="//div[@aria-label='Move forward to switch to the next month.']";   // TIME_PREFIX ="//div[@class="doorway-date-selector"]/div[@class="doorway-input-row"]/*div[@class="split-col"]/select/option[0]"
	String CALENDAR = "//table[@class='CalendarMonth_table CalendarMonth_table_1']";
	String POSTFIX ="']";
	String FIRST_NAME ="//input[@placeholder='First name']";
	String LAST_NAME ="//input[@placeholder='Last name']";
	String EMAIL ="//input[@placeholder='Email']";
	String PHONE ="//input[@placeholder='Phone']";
	String MESSAGE ="//textarea[@placeholder='Add your message']";
	String BEDROOM_DROPDOWN ="//select/option[text()='Select Bedrooms']";
	String UNIT_TYPE ="//option[text()='Select Unit (optional)']";
	String BOOK_TOUR ="//button[text()='Book tour!']";

	@FindBy(className = "doorway-plugin-frame")
	private WebElement iframe;

	public SchedulingTourPage() {
		PageFactory.initElements(driver, this);
	}


	public void chooseInPersonTour() {
		loadIframe();

		WebElement element = driver.findElement(By.id( IN_PERSON_TOUR));
		clickElement(element);
	}

	public void chooseVideoTour() {
		loadIframe();
		WebElement element = driver.findElement(By.id( VIDEO_TOUR));
		clickElement(element);
	}

	/*
	chooses first available date as shown on datepicker
	assumes date is available
	todo: not sure if calendar only shows month when it has open dates- if not, can't assume dates available
	 */
	public void chooseAnEnabledDateFromCalendar(){
		driver.findElement(By.id( DATE_PICKER)).click();

		List<WebElement> list = driver.findElements(By.xpath
				("//td[starts-with(@class, 'CalendarDay CalendarDay_1') and @aria-disabled='false']"));
		list.get(1).click();
	}

	public void chooseSpecificDateOnCalendar(String dateString, int monthsToMoveRight ) {
		driver.findElement(By.id( DATE_PICKER)).click();

		for (int i = 0; i < monthsToMoveRight; i++) {
			driver.findElement(By.xpath( NEXT_MONTH )).click();
		}
		//choose specific date given i.e. 31
		driver.findElement(By.xpath( CALENDAR + "/tbody/tr/td[text()='" + dateString + POSTFIX )).click();
	}

	//convenience method to pick any time: picks first free time it finds from dropdown
	public String chooseAFreeTime() {
		// all the time options have values ending in -00-08:00. This is one way to collect them
		//not ideal, should add locator with id or otherwise.
		List<WebElement> list = driver.findElements(By.xpath("//option[contains(@value, '00-08:00')]"));
		list.get(0).click();
		return list.get(0).getText();
	}

	/*
	picks time matching given time
	(requires properly formatted time as seen in dropdown as it
	assumes/expects it exists (relies on nonvolatile data)
	 */
	public void chooseSpecificTime(String timeString) {
		driver.findElement(By.xpath( "//option[(text()= '" + timeString + "')]")).click();
	}

	public void enterFirstName(String name) {
		driver.findElement( By.xpath(FIRST_NAME)).sendKeys(name);
	}

	public void enterLastName(String name) {
		driver.findElement( By.xpath(LAST_NAME)).sendKeys(name);
	}

	public void enterEmail(String email) {
		driver.findElement( By.xpath(EMAIL)).sendKeys(email);
	}

	public void enterPhone(String phone) {
		driver.findElement( By.xpath(PHONE)).sendKeys(phone);
	}

	public void enterMessage(String message) {
		driver.findElement( By.xpath(MESSAGE)).sendKeys(message);
	}

	public void enterBedroooms(String bedroomString) {
		driver.findElement(By.xpath( BEDROOM_DROPDOWN)).click();
		driver.findElement(By.xpath("//option[text()='" + bedroomString + POSTFIX));
	}

	/*
	picks unit matching given string
	assumes it exists, user must know expected option exists
	 */
	public void enterUnitType(String unitType) {
		driver.findElement(By.xpath(UNIT_TYPE)).click();

		driver.findElement(By.xpath( "//option[text()='" + unitType + POSTFIX)).click();
	}

	public void clickBookTour() {
		driver.findElement(By.xpath(BOOK_TOUR)).click();
	}

	/*
	automatically verifies time of appointment on result page matches time selected by test.
	trims out whitespaces same as it is done on UI.
	 */
	public void assertBookTime(String expectedTime){

		expectedTime = expectedTime.replace(" ","");
		waitForVisibility ("//div[starts-with(.,'"+ expectedTime +"')]");
		System.out.println("Expected time confirmation of:" + expectedTime );
		WebElement timeconfirmation = driver.findElement(By.xpath("//div[starts-with(.,'"+ expectedTime +"')]"));
		//timeconfirmation text: if isn't found, test will be failed above, but we can also assert element is not null just in case...
		assert timeconfirmation != null;
		System.out.print("Test assert passed");
	}

	// when page is reloaded with content in iframe, have to switch to it, selenium cannot search within iframe.
	public void loadIframe(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(iframe);
	}
}
