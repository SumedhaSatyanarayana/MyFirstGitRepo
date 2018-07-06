package com.crm.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase(){// testBase class constructor
		// read my properties
		try {
			prop = new Properties();
			String path = ("J:\\workspace\\CRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		}catch (Exception e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	public static void initialization(){
		String BrowserName = prop.getProperty("browser");
		if (BrowserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "J:\\eclipse\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();	
		}
		else if (BrowserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "J:\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();	
		}
		else {
		/*	System.setProperty("webdriver.gecko.driver", "J:\\eclipse\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();	*/;
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		//Thread.sleep(1000);
	}
}
