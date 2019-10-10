package com.selenium.libraries;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base {
	final static Logger logger = Logger.getLogger(Base.class);
	
	public static WebDriver driver;
	public static GlobalSeleniumLibrary myLibrary;
	public boolean isCaptureScreenshot = true;
	public String myBrowser;
	public String myDemo;
	public String myRemote;
	public String isAutoSendEmail;

	@BeforeClass
	public void beforeAllTest() {
		JavaPropertiesManager readingProperty = new JavaPropertiesManager("src/test/resources/config.properties");
		myBrowser = readingProperty.readProperty("browserType");
		myDemo = readingProperty.readProperty("isDemo");
		myRemote = readingProperty.readProperty("isRemote");
		isAutoSendEmail = readingProperty.readProperty("autoEmail");
		
		myLibrary = new GlobalSeleniumLibrary(driver);
		
		JavaPropertiesManager readingProperty2 = new JavaPropertiesManager("src/test/resources/dynamicConfig.properties");
		String tempTime = myLibrary.getCurrentTime();
		readingProperty2.setProperty("sessionTime", tempTime);
		logger.info("Session Time: " + tempTime);
		
		logger.info("Before all tests starts...");		
		if(myDemo.contains("true")){
			myLibrary.setDemoMode(true);
		}
		logger.info("demo variable value: " + myLibrary.getDemoMode());
	}

	@AfterClass
	public void afterAllTest() {
		logger.info("After all test completed...");
		if (driver != null) {
			// driver.close();
			driver.quit();
		}
		// Sending all the reports, screenshots, and log files via email
		List<String> screenshots = new ArrayList<>();
		EmailManager emailSender = new EmailManager();
		emailSender.attachmentFiles.add("target/logs/log4j-selenium.log");
		emailSender.attachmentFiles.add("target/logs/Selenium-Report.html");
		screenshots = myLibrary.automaticallyAttachErrorImgToEmail();
		if(screenshots.size() != 0){
			for(String attachFile : screenshots){
				emailSender.attachmentFiles.add(attachFile);
			}
		}
		
		emailSender.toAddress = "musabaytechcorp@gmail.com;training@musabaytechnologies.com";
		emailSender.ccAddress = "frank@musabaytechnologies.com";
		
		if(isAutoSendEmail.contains("true")){
			emailSender.sendEmail(emailSender.attachmentFiles);
		}
		
		
	}

	@BeforeMethod
	public void beforeEachTest() {
		logger.info("Before each test started ...");
		if(myRemote.contains("true")){
			driver = myLibrary.startRemoteBrowser("http://192.168.1.253:7788/wd/hub/", myBrowser);
		}else{
			driver = myLibrary.startLocalBrowser(myBrowser);
		}
		
	}

	@AfterMethod
	public void afterEachTest(ITestResult result) {
		logger.info("After each test completed ...");
		if (isCaptureScreenshot) {
			if (ITestResult.FAILURE == result.getStatus()) {
				logger.error("Error: Test Failed...",result.getThrowable());
				myLibrary.captureScreenshot(result.getName(), "");
			}
		}
		//myLibrary.customWait(30);
		driver.close();
	}
}













