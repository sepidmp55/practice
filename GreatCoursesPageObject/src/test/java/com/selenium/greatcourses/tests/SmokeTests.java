package com.selenium.greatcourses.tests;

import org.testng.annotations.Test;

import com.selenium.greatcourses.pages.CheckOutPage;
import com.selenium.greatcourses.pages.ChooseAFormatPage;
import com.selenium.greatcourses.pages.HomePage;
import com.selenium.greatcourses.pages.SciencePage;
import com.selenium.greatcourses.pages.ViewCartPage;
import com.selenium.libraries.Base;

public class SmokeTests extends Base{

	HomePage myHomePage = new HomePage();
	SciencePage mySciencePage;
	ChooseAFormatPage myChooseAFPage;
	ViewCartPage myViewCPage;
	CheckOutPage myCheckOPage;
	
	@Test(enabled = false)
	public void checkingHomePageTest(){
		myHomePage.goto_theGreatCourseWebsite();
	}
	
	@Test(enabled = false)
	public void buyNighSkyCourseTest(){
		checkingHomePageTest();
		// locate and lick on Science category
		mySciencePage = myHomePage.selectScienceCategory();
		myChooseAFPage = mySciencePage.selectOurNightSkyCourse();
		myChooseAFPage.selectInstanceVideoRadioBtn();
		myViewCPage = myChooseAFPage.clickAddToCartButton();
		myCheckOPage = myViewCPage.click_ViewCartButton();
		myCheckOPage.click_CheckOutButton();
		
		
		
		
		myHomePage
		.selectScienceCategory()
		.selectOurNightSkyCourse()
		.selectInstanceVideoRadioBtn()
		.clickAddToCartButton()
		.click_ViewCartButton()
		.click_CheckOutButton();	
	}
	
	@Test
	public void buy_TheAgingBrainCoursTest(){
		checkingHomePageTest();
		
		/*myHomePage
		.selectScienceCategory()		
		.selectTheAgingBrainCourse()
		.selectInstanceVideoRadioBtn()
		.clickAddToCartButton()
		.click_ViewCartButton()
		.click_CheckOutButton();*/
	}
	
	
}







