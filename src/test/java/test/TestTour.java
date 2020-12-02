package test;

import PageFactory.SchedulingTourPage;
import org.testng.annotations.Test;

import PageFactory.Homepage;

public class TestTour extends BaseTest {

	Homepage home_page = new Homepage();
	SchedulingTourPage result_page = new SchedulingTourPage();

	@Test
	public void scenario1() {

		// Given the Knock home page is displayed
		home_page.clickScheduleTourButton();

  		// choose In-Person Tour or Live Video Tour
		result_page.chooseInPersonTour();

  		// choose date, include number in month and how many months to move right
		result_page.chooseAnEnabledDateFromCalendar();

  		// choose time
		String time =result_page.chooseAFreeTime();

  		// enter first name and last name
		result_page.enterFirstName("John");

		result_page.enterLastName("Smith");

		//enter email
		result_page.enterEmail("djfhskjfhsjdfhksdfjhs@yahoo.com");

  		// enter phone
		result_page.enterPhone("206-372-1234");

  		// enter message
		result_page.enterMessage("The quick brown fox jumps over the lazy dog");

  		// Choose how many bedrooms
		result_page.enterBedroooms("Studio");

  		// Select Unit type
		result_page.enterUnitType("$1020 - 1bd 1ba - Unit 1255, Avail Today");

  		// click Book tour!
		result_page.clickBookTour();

		//assert time we requested is confirmed on result
		result_page.assertBookTime(time);
	}

}
