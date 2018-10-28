package com.selenium.ddf.testcases;
import java.util.Hashtable;
import com.selenium.ddf.util.*;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.ddf.base.*;

public class DummyTestB extends Baseclass{
	
	
	String testCaseName="TestB";
	Xls_Reader xls;
		
	@Test(dataProvider="getData")
	
	public void testB(Hashtable<String,String> data) {
		softAssert=new SoftAssert();
		
		test=rep.startTest("DummyTestB");
		test.log(LogStatus.INFO, "Starting the test DummyTestB");
		test.log(LogStatus.INFO,data.toString());
		
		if(!DataUtil.isRunnable(testCaseName,xls)||data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test with rumode as N");
			throw new SkipException("Skipping the test with rumode as N");
		}
		
		
		openBrowser("Mozilla");
		
		test.log(LogStatus.INFO, "opened the browser");
		navigate("appurl");
		
		//check if email is present or not
		if(!isElementPresent("email_xpath"))
			reportFailure("Email Field is not present");//critical
		
		
		
		softAssert.assertTrue(verifyText("Heading_xpath","headingText"),"Text didnt match");
		
		
		type("email_xpath","ramya.addepalli1203@gmail.com");
		takeScreenShot();
		click("button_xpath");
		test.log(LogStatus.PASS, "test passed");
		takeScreenShot();
		//take screenshots
		//test.log(LogStatus.PASS, "screencaptured"+test.addScreenCapture("C:\\test.png"));//to add a sccreen shot
		
		//String fname = currTime+"_"+testName;
		
		//System.getProperty("user.dir") + File.separator + "/screenshots/"+fname+".jpeg";
			
		}
	
     @AfterMethod
        public void quit() {
 	
    	 
    	 try {
    		 softAssert.assertAll();
    	 }catch(Error e) {
    		 test.log(LogStatus.FAIL, e.getMessage());
    		 
    	 }
    	 
    	 rep.endTest(test);
    	 rep.flush();
        }
	
		
     @DataProvider
 	
 	public Object[][] getData(){
    	init();
 		Xls_Reader xls=new Xls_Reader(prop.getProperty("xlspath"));
 		return DataUtil.getTestData(xls,testCaseName);
 	}
	}


