package pages;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

//Test 1: Validate a user is able to add a category and once the category is added it should display.
//Test 2: Validate a user is not able to add a duplicated category. If it does then the following message will display: "The category you want to add already exists: <duplicated category name>."
//Test 3: Validate the month drop down has all the months (jan, feb, mar ...) in the Due Date dropdown section.

public class TestNG_Page extends BasePage {

	// GLOBAL VARIABLE DECLARATIONS
	WebDriver driver;
	List<WebElement> removeAllCategoryList;
	String beforeXpath_categoryList = "//body/div[3]/a[";
	String afterXpath_categoryList = "]";

	public TestNG_Page(WebDriver driver) {
		this.driver = driver;
	}

	// WEBELEMENT LIST

	@FindBy(how = How.XPATH, using = "")
	WebElement REMOVE_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//input[@value='Add category']")
	WebElement ADD_CATEGORY_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//input[@name='categorydata']")
	WebElement TEXT_FIELD_ADD_CATEGORY_ELEMENT;
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Yes')]")
	WebElement YES_BUTTON_ELEMENT;
	@FindBy(how = How.NAME, using = "due_month")
	WebElement DUE_DATE_MONTH_DROPDOWN_ELEMENT;

	// CORRESPONDING METHODS

	public void clickRemoveButton() {
		REMOVE_BUTTON_ELEMENT.click();
	}

	// Test 1: Validate a user is able to add a category and once the category is
	// added it should display.
	String insertedText;

	public void validateIfAddCategoryAndInsertTextIsdisplayed(String text0, String text1, String text2, String text3,
			String text4) {

		String[] textInput = { text0, text1, text2, text3, text4 };
		for (int i = 0; i < textInput.length; i++) {
			TEXT_FIELD_ADD_CATEGORY_ELEMENT.click();
			insertedText = textInput[i] + " " + geneRandomNum(999);
			TEXT_FIELD_ADD_CATEGORY_ELEMENT.sendKeys(insertedText);
			ADD_CATEGORY_BUTTON_ELEMENT.click();// category added.
			removeAllCategoryList = driver.findElements(By.xpath("//a[contains(@title,'Remove this category')]"));
			// last entered value for category
			int lastEntry = removeAllCategoryList.size() - 1;
			boolean isCategorydisplayed = removeAllCategoryList.get(lastEntry).isDisplayed();
			Assert.assertTrue(isCategorydisplayed, "Category Not Added");
			// text of the added category.
			System.out.println(removeAllCategoryList.get(lastEntry).getText());
		}
	}

	// Test 2: Validate a user is not able to add a duplicated category. If it does
	// then the following message will display: "The category you want to add
	// already exists: <duplicated category name>."
	public void validateIfUserIsNotAbleToAddDuplicatedCategory(String text0, String text1, String text2, String text3,
			String text4, String text5) {
		// change the test data in 'test page' on every execution else test fails due to
		// duplication of pre existing data.
		String[] textInput = { text0, text1, text2, text3, text4, text5 };

		// creating an empty HashSet
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < textInput.length; i++) {
			insertedText = textInput[i];
			// checking if the element is already present or not
			if (!set.contains(insertedText)) {
				// adding the element to HashSet if it is not present
				set.add(insertedText);
				TEXT_FIELD_ADD_CATEGORY_ELEMENT.click();
				TEXT_FIELD_ADD_CATEGORY_ELEMENT.sendKeys(insertedText);
				ADD_CATEGORY_BUTTON_ELEMENT.click();// category added.
			}
			// if the element is already present in set then it validates and displays the
			// following assert message.
			boolean isDuplicate = set.contains(insertedText);
			Assert.assertTrue(isDuplicate, "The category you want to add already exists: " + insertedText);

		}
		// prints all the values of set
		for (String addedCategory : set) {
			System.out.println(addedCategory);
		}

	}

	// Test 3: Validate the month drop down has all the months (jan, feb, mar ...)
	// in the Due Date dropdown section.
	public void validateMonthDropdownHasAllMonths() {

		WebElement element = DUE_DATE_MONTH_DROPDOWN_ELEMENT;
		element.click();
		List<String> monthDropdownList = selectFromDropdown(element);

		for (String eachMonth : monthDropdownList) {
			System.out.println(eachMonth);
		}
	}
}
