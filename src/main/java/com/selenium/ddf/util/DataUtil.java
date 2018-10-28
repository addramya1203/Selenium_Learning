package com.selenium.ddf.util;

import java.util.Hashtable;

import com.selenium.ddf.base.Baseclass;
import com.selenium.ddf.testcases.Xls_Reader;

public class DataUtil extends Baseclass{


	public static Object[][] getTestData(Xls_Reader xls,String testCaseName){
    
		String sheetName="Data";
 		
 		
 		int testStartRowNum=1;
 		
 		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)) {
 			testStartRowNum++;
 		}
 		System.out.println("Test start rownum - "+testStartRowNum);
 		
 		int colStartRowNum=testStartRowNum+1;
 		int dataStartNum=testStartRowNum+2;
 		
 		//calculate total rows for the selected test
 		int rows=0;
 		while(!xls.getCellData(sheetName, 0, dataStartNum+rows).equals("")) {
 			rows++;
 		
 		
 		}
 		System.out.println("Total rows for the selected test-"+rows);
 		//calculate total cols for the selected test
 		
 		int cols=0;
 		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")) {
 			cols++;
 		
 		
 		}
 		System.out.println("Total cols for the selected test-"+cols);
 		Object[][] data=new Object[rows][1];
 		
 		int dataRow=0;
 		Hashtable<String,String> table=null;
 		
 		
 		for(int rNum=dataStartNum;rNum<dataStartNum+rows;rNum++) {
 			table=new Hashtable<String,String>();
 			for(int cNum=0;cNum<cols;cNum++)
 			{
 				String key=xls.getCellData(sheetName, cNum,colStartRowNum );
 				String value=xls.getCellData(sheetName, cNum, rNum);
 				table.put(key, value);
 				}
 			data[dataRow][0]=table;
 			dataRow++;
 		}
 		
 		return data;	
		
	}
	
	public static boolean isRunnable(String testCasename,Xls_Reader xls) {
		 xls=new Xls_Reader(prop.getProperty("xlspath"));
		String sheetName="TestCases";
		int rows=xls.getRowCount(sheetName);
		System.out.println("No of rows"+rows);
		
		for(int r=2;r<=rows;r++) {
			String tName=xls.getCellData(sheetName,"TestcaseID", r);
			if(tName.equals(testCasename)) {
				String runmode=xls.getCellData(sheetName, "Runmode",r);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
				
			}
		}
		
		
		return false;
		
		
	}
}
