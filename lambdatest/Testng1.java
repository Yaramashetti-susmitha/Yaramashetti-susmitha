package com.lambdatest;

//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

public class Testng1 {

   // public static RemoteWebDriver driver;
    public RemoteWebDriver driver = null;
	  

  @Test(priority=0)
  public void scenarioOne() throws Exception {
		  
			  WebDriverWait wait = new WebDriverWait(driver, 20);
		         //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-size-50.text-white.font-bold")));
		         //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".text-size-50.text-white.font-bold"))));
		        // wait.until(ExpectedConditions.titleContains("Selenium Grid Online | Run Selenium Test On Cloud"));
		         String title = driver.getTitle();
		         SoftAssert softAssert = new SoftAssert();
		         softAssert.assertEquals(title, "LambdaTest");
		         System.out.println(title);
		         System.out.println("First test case passed");
			
		
  }
  @Test(priority=1)
  public void secondScenario() throws InterruptedException
  {
	  driver.findElement(By.xpath("//a[normalize-space()='Checkbox Demo']")).click();
      driver.findElement(By.xpath("//input[@id='isAgeSelected']")).click();
      String successmsg = driver.findElement(By.xpath("//div[@id='txtAge']")).getText();
      Assert.assertEquals("Success - Check box is checked",successmsg);
      driver.findElement(By.xpath("//input[@id='isAgeSelected']")).click();
      Thread.sleep(2000);
      Boolean checkbox =  driver.findElement(By.xpath("//div[@id='txtAge']")).isDisplayed();
      if(checkbox == true){
         System.out.println("checkbox text is visible");
      }else{
         System.out.println("Check box text is not visible");
      }
      System.out.println("Second test case passed");
  }
  @Test(priority=2)
  public void thirdScenario()
  {
	  driver.findElement(By.xpath("//a[normalize-space()='Javascript Alerts']")).click();
      driver.findElement(By.cssSelector("button[class='btn btn-dark my-30 mx-10 hover:bg-lambda-900 hover:border-lambda-900']")).click();
      String alert = driver.switchTo().alert().getText();
      Assert.assertEquals(alert,"I am an alert box!" );
      driver.switchTo().alert().accept();
      System.out.println("Third test case passed");
  }
  @BeforeMethod
  @Parameters({ "browser", "version", "platform" })
  public void setUpClass(String browser, String version, String platform) throws Exception {

  	String username = "nagac2169"; 
		String accesskey = "IIePN9nm9jPIfh208a00ZiaZO8Bk1NiIvZK8Y19oCfAnTdNBHA"; 

  		DesiredCapabilities capability = new DesiredCapabilities();    	
        
  		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
  		capability.setCapability(CapabilityType.VERSION,version);
  		capability.setCapability(CapabilityType.PLATFORM, platform);		
  		
          capability.setCapability("build", "New TestNG");
  		capability.setCapability("network", true);
  		capability.setCapability("video", true);
  		capability.setCapability("console", true);
  		capability.setCapability("visual", true);

  		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
  		//System.out.println(gridURL);
  		driver = new RemoteWebDriver(new URL(gridURL), capability);
  		System.out.println(capability);
  		//System.out.println(driver.getSessionId());
          driver.get("https://www.lambdatest.com/selenium-playground/");
          driver.manage().deleteAllCookies();
	 
  }
  @AfterMethod
  public void close()
  {
	  driver.quit();
  }

}