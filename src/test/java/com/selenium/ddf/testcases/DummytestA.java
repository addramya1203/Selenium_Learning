package com.selenium.ddf.testcases;

import org.testng.annotations.AfterMethod;
//import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.ddf.base.Baseclass;

public class DummytestA extends Baseclass{
	
	@Test(priority=1)
	public void testA1() {
		//Assert.fail();
		test=rep.startTest("DummyTestA");
	}
	@Test(priority=2,dependsOnMethods= {"testA1"})
	public void testA2() {
		
		
	}
	@Test(priority=3,dependsOnMethods= {"testA2","testA1"})
	public void testA3() {
		
		
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
}
