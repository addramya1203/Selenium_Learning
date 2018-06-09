import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HandlingAjax {
   static WebDriver driver;
	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\IBM_ADMIN\\Desktop\\selenium_practical\\geckdriver\\geckodriver.exe");
        
	   driver=new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("Hello there");
		
		 String part1=" //div[@class='sbsb_a']/ul/li[";
		 String part2="]/div/div[2]";
		 
		 int i=2;
		
			while(isElementPresent(part1+i+part2)) {
				
				String text= driver.findElement(By.xpath(part1+i+part2)).getText();
				System.out.println(text);
				
				i++;

			}
			
			}
			public static boolean isElementPresent(String xpathExp) {
				
		       List<WebElement> allElements=driver.findElements(By.xpath(xpathExp));
		     //  System.out.println(allElements.size());
			 if(allElements.size()==0) 
		    	   return false;
		    	   else
		    		   return true;
}
		 
}


