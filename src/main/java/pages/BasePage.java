package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	static List<WebElement> monthsOfDueDate;

	public static List<String> selectFromDropdown(WebElement element) {
		Select sel = new Select(element);
		monthsOfDueDate = sel.getOptions();
		List<String> months = new ArrayList<>();
		for (WebElement eachMonth : monthsOfDueDate) {
			if (!eachMonth.getText().contains("None"))
				months.add(eachMonth.getText());
		}
		return months;
	}

	public int geneRandomNum(int boundary) {
		Random rnd = new Random();
		int generatedNum = rnd.nextInt(boundary);
		return generatedNum;
	}
}
