package com.selenium.greatcourses.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.libraries.Base;
import com.selenium.libraries.ExcelManager;

public class DataDrivenTests extends Base{

	private int counter = 0;
	
	@DataProvider(name = "MortgageTestData")
	private Object[][] calculatorData() {
		ExcelManager excelReader = new ExcelManager("src/test/resources/TestData/CalculaterTestData2.xls");
		Object[][] data;
		data = excelReader.getExcelData("Sheet1");
		return data;
	}
	
	@Test(dataProvider = "MortgageTestData")
	public void dataDrivenTests(String amount, String Myear, String Mmonth, String intYear, String intMonth,
			String intType, String intRate, String startMonth, String startYear, String paymentPeriod, 
			String expectedResult) {
		try {		
			counter ++;
			
			driver.get("https://www.mortgagecalculator.net/");
			
			myLibrary.enterText(By.id("amount"), amount);

			myLibrary.enterText(By.xpath("//*[@id='amortizationYears']"), Myear);

			myLibrary.enterText(By.cssSelector("#amortizationMonths"), Mmonth);

			myLibrary.enterText(By.id("interestTermYears"), intYear);

			myLibrary.enterText(By.cssSelector("#interestTermMonths"), intMonth);

			myLibrary.selectDropDown(By.id("interestType"), intType);

			myLibrary.enterText(By.xpath("//*[@id='rate']"), intRate);

			myLibrary.selectDropDown(By.id("startMonth"), Integer.valueOf(startMonth));

			myLibrary.selectDropDown( By.id("startYear"), startYear);

			myLibrary.selectDropDown(By.cssSelector("#paymentMode"), paymentPeriod);

			myLibrary.clickBtn(By.cssSelector("#button"));

			Thread.sleep(5 * 1000);

			WebElement monthlyPaymentResult = driver.findElement(By.id("summaryMonthly"));
			// String monthlyPaymentTxt = monthlyPaymentResult.getText();
			String monthlyPaymentTxt = monthlyPaymentResult.getAttribute("value");

			System.out.println("Test Scenario: " + counter + ",  Monthly payment amount is: " + monthlyPaymentTxt + ", Expected: ["+expectedResult+"]");
			assertEquals(monthlyPaymentTxt, expectedResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
