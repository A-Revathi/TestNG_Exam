package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.TestNG_Page;
import util.BrowserFactory;

//Test 1: Validate a user is able to add a category and once the category is added it should display.
//Test 2: Validate a user is not able to add a duplicated category. If it does then the following message will display: "The category you want to add already exists: <duplicated category name>."
//Test 3: Validate the month drop down has all the months (jan, feb, mar ...) in the Due Date dropdown section.
public class TestNG_Test {

	WebDriver driver;
	TestNG_Page testNGpage;
	String text0 = "Revathi SDET";
	String text1 = "Revathi JAVA";
	String text2 = "Revathi PYTHON";
	String text3 = "Revathi RUBY";
	String text4 = "Revathi C#";

	String category0 = "Java";
	String category1 = "Python";
	String category2 = "Java";
	String category3 = "Ruby";
	String category4 = "Python";
	String category5 = "C#";

	// Test 1: Validate a user is able to add a category and once the category is
	// added it should display.
	 @Test(priority = 1)
	public void test1() throws Exception {

		driver = BrowserFactory.init();

		testNGpage = PageFactory.initElements(driver, TestNG_Page.class);
		// testNGpage.removePreExistingCategoryList();
		testNGpage.validateIfAddCategoryAndInsertTextIsdisplayed(text0, text1, text2, text3, text4);
		// BrowserFactory.tearDown();

	}

	// Test 2: Validate a user is not able to add a duplicated category. If it does
	// then the following message will display: "The category you want to add
	// already exists: <duplicated category name>."
	// @Test(priority = 2)
	public void test2() {

		driver = BrowserFactory.init();

		testNGpage = PageFactory.initElements(driver, TestNG_Page.class);
		testNGpage.validateIfUserIsNotAbleToAddDuplicatedCategory(category0, category1, category2, category3, category4,
				category5);
		// BrowserFactory.tearDown();
	}

	// Test 3: Validate the month drop down has all the months (jan, feb, mar ...)
	// in the Due Date dropdown section.
	@Test(priority = 3)
	public void test3() {

		driver = BrowserFactory.init();

		testNGpage = PageFactory.initElements(driver, TestNG_Page.class);
		testNGpage.validateMonthDropdownHasAllMonths();
		// BrowserFactory.tearDown();
	}

}
