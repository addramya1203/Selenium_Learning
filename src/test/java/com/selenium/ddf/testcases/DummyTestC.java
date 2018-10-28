package com.selenium.ddf.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.ddf.base.Baseclass;
import com.selenium.ddf.util.DataUtil;

public class DummyTestC extends Baseclass {
	String testCaseName="TestC";
	Xls_Reader xls;
	@Test(dataProvider="getData")

public void testC(Hashtable<String,String> data) {
		softAssert=new SoftAssert();
		test=rep.startTest("DummyTestC");
		test.log(LogStatus.INFO, "Starting the test DummyTestC");
		test.log(LogStatus.INFO,data.toString());
		
		if(!DataUtil.isRunnable(testCaseName,xls)||data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test with rumode as N");
			throw new SkipException("Skipping the test with rumode as N");
		}
       openBrowser("Mozilla");
		
		test.log(LogStatus.INFO, "opened the browser");
		
		
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
