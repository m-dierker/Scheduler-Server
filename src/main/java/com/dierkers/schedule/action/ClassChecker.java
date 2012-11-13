package com.dierkers.schedule.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClassChecker extends Action {

	public ClassChecker(String owner, String data) {
		super(owner, data);
	}

	@Override
	public int getID() {
		return ActionType.CLASS_CHECKER;
	}

	@Override
	public void process() {

		WebDriver driver = new FirefoxDriver();

		driver.get("http://www.google.com");

		// Search bar
		WebElement element = driver.findElement(By.id("gbqfq"));

		element.sendKeys("Matthew Dierker");

		System.out.println(driver.getPageSource());

	}

	public static void main(String... args) {
		ClassChecker checker = new ClassChecker("owner", "{}");
		checker.process();
	}

}
