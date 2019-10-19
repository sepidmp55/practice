package com.oranghrm.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


	@RunWith(Cucumber.class)
	@CucumberOptions(features = "src/test/resources/login.feature", glue = "com.orangehrm.steodefinition.Test.java")
	public class Testrunner extends AbstractTestNGCucumberTests {
	    
	}


