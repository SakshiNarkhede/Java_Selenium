package lambdatest;

//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import org.openqa.selenium.*;

public class LambdaTest {

   // public static RemoteWebDriver driver;
    public RemoteWebDriver driver = null;
	  

  @Test(priority=0)
  public void testScenarioOne() throws Exception {
		  
			  WebDriverWait wait = new WebDriverWait(driver, 20);
		         //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-size-50.text-white.font-bold")));
		         //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".text-size-50.text-white.font-bold"))));
				//opening page
				((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
				driver.manage().window().maximize();
				Thread.sleep(4000);
				
				WebElement s = driver.findElement(By.xpath("//a[normalize-space()='Simple Form Demo']"));
				s.click();
				Thread.sleep(5000);
				//validating URL
				String url = driver.getCurrentUrl();
				if(url.contains("simple-form-demo")) {
					System.out.println("Url validated successfully");
				}
				
				
				//Entering and verfying the input text 
				String s1 = "Welcome to LambdaTest";
				driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(s1);
				Thread.sleep(2000);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				driver.findElement(By.id("showInput")).click();
				Thread.sleep(2000);
				String es = driver.findElement(By.id("message")).getText();
				System.out.println(es);
				if(s.equals(es)) {
					System.out.print("validated");
				}
			
		
  }
  @Test(priority=1)
  public void  testScenarioTwo() throws InterruptedException
  {
	  ((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		WebElement sliderr = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]"));
		sliderr.click();
		Thread.sleep(2000);
		
		//changing value to 95
		Actions a = new Actions(driver);
		a.dragAndDropBy(sliderr, 120,0).perform();
		
		String value = driver.findElement(By.cssSelector("#rangeSuccess")).getText();
		if(value.equals("95")){  //verifying the value
			System.out.println("Range value validated and shows 95");
		}
		Thread.sleep(3000);
  }
  @Test(priority=2)
  public void  testScenarioThree() throws InterruptedException
  {
	  driver.manage().window().maximize();
		((WebDriver) driver).get("https://www.lambdatest.com/selenium-playground");
		Thread.sleep(3000);
		
		//submitting without filling up the data
		driver.findElement(By.xpath("//a[normalize-space()='Input Form Submit']")).click();
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(2000);
		String message = driver.findElement(By.id("name")).getAttribute("validationMessage");
		System.out.println(message);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertSame(message, "Please fill in the fields");
		
		//filling out data
		driver.findElement(By.name("name")).sendKeys("Sakshi");
		driver.findElement(By.name("email")).sendKeys("narkhedesakshi0703@gm");
		driver.findElement(By.name("password")).sendKeys("11234");
		driver.findElement(By.name("company")).sendKeys("cogni");
		driver.findElement(By.name("website")).sendKeys("www.worldturnover.org");
		
		driver.findElement(By.name("city")).sendKeys("tumsar");
		driver.findElement(By.name("address_line1")).sendKeys("1 street");
		driver.findElement(By.name("address_line2")).sendKeys("2 house");
		driver.findElement(By.id("inputState")).sendKeys("maharashtra");
		driver.findElement(By.id("inputZip")).sendKeys("876543");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String msg= driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
		Thread.sleep(5000);
		Assert.assertEquals(msg, "Thanks for contacting us, we will get back to you shortly.");
		Thread.sleep(2000);

  }
  @BeforeMethod
  @Parameters({ "browser", "version", "platform" })
  public void setUpClass(String browser, String version, String platform) throws Exception {

  	    String username = "sakshinarkhede9235";
		String accesskey = "Btd44rb6gzUJADc4HK5HbnkND54UNThkte6CZWkB4v7zpxv4BK";

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
  		System.out.println(gridURL);
  		driver = new RemoteWebDriver(new URL(gridURL), capability);
  		System.out.println(capability);
  		System.out.println(driver.getSessionId());
          driver.get("https://www.lambdatest.com/selenium-playground/");
          driver.manage().deleteAllCookies();
	 
  }
  @AfterMethod
  public void close()
  {
	  driver.quit();
  }

}