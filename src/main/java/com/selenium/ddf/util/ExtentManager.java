package com.selenium.ddf.util;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		extent = new ExtentReports("C:\\report\\"+fileName, true, DisplayOrder.NEWEST_FIRST);
			
			extent.loadConfig(new File(System.getProperty("user.dir")+"//src//test//resources//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "3.4.0").addSystemInfo(
					"Environment", "QA");
		}
		return extent;
	}
}
