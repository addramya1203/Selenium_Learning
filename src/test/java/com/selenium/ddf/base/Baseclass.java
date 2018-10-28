package com.selenium.ddf.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.ddf.util.ExtentManager;



public class Baseclass {

	public WebDriver driver;
	public static Properties prop;
	public ExtentReports rep=ExtentManager.getInstance();
	public ExtentTest test;
	public SoftAssert softAssert=new SoftAssert();
	public void init() {
		if(prop==null) {
			prop=new Properties();
			try {
				FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties");
				prop.load(fs);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	public void openBrowser(String btype) {
		//inti prop file
		
		if(btype.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",prop.getProperty("Mozilla_exe"));
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
		}
	}
	
	public void navigate(String urlKey) {
		
		driver.get(prop.getProperty(urlKey));
	}
	
	public void click(String locatorKey) {
		
		getElement(locatorKey).click();
	}
	
	public void type(String locatorKey,String data) {
		getElement(locatorKey).sendKeys(data);
		
	}
	
	public WebElement getElement(String locatorKey) {
		WebElement e=null;
		try {
		if(locatorKey.endsWith("_id")) {
			e=driver.findElement(By.id(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_xpath")) {
			e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_name")) {
			e=driver.findElement(By.name(prop.getProperty(locatorKey)));
		}
		//if the locator key is not ending with _id,-xpath,_name then print
		else {
			reportFailure("Locator passed is not ending with _id,-xpath,_name"+locatorKey);
			Assert.fail("Locator passed is not ending with _id,-xpath,_name"+locatorKey);
		}
		
	}catch(Exception ex) {
		//to fail the test and report error
		reportFailure(ex.getMessage());
		ex.printStackTrace();
		Assert.fail("Failed the test"+ex.getMessage());
		
	}
		
		return e;
	}
	
	
	/******************validation function*******************************/
	
	public boolean verifyTitle() {
		
		return false;
	}
	
	public boolean isElementPresent(String locatorKey) {
		List<WebElement> elementList=null;
		if(locatorKey.endsWith("_id")) {
			elementList=driver.findElements(By.id(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_xpath")) {
			elementList=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		}
		else if(locatorKey.endsWith("_name")) {
			elementList=driver.findElements(By.name(prop.getProperty(locatorKey)));
		}
		//if the locator key is not ending with _id,-xpath,_name then print
		else {
			reportFailure("Locator passed is not ending with _id,-xpath,_name"+locatorKey);
			Assert.fail("Locator passed is not ending with _id,-xpath,_name"+locatorKey);
		}
		if(elementList.size()==0) {
		
			return false;
		}
		else 
			return true;
		
		
	}
	public boolean verifyText(String locatorKey,String expectedTextKey) {
		String actualText=getElement(locatorKey).getText().trim();
		String expectedText=prop.getProperty(expectedTextKey);
		if(actualText.equals(expectedText)) {
			
			test.log(LogStatus.INFO, "expected text and actual text are matching");
			
			return true;
		}
		else
		
		return false;
		
	}
	
	/**********************************reporting function*****************************/
	
	public void reportPass(String msg) {
		
		test.log(LogStatus.PASS, msg);
		
	}
	
   public void reportFailure(String msg) {
	   test.log(LogStatus.FAIL, msg);
	   takeScreenShot();
	   Assert.fail(msg);
	   
   }
	public void takeScreenShot() {
		//file name of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".jpeg";
		//store that screenshot in  that file
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
			
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\screenshots"+screenshotFile));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		//put sccreen shot file in reports
		test.log(LogStatus.INFO, "Screenshot"+test.addScreenCapture(System.getProperty("user.dir")+"//screenshots"+screenshotFile));
	}
	
}
