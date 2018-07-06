package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	// define the obj repository
		// page factory - OR (object repository)
		@FindBy(name="username") 
		WebElement uName;
		@FindBy(name="password") 
		WebElement pword;
		@FindBy(xpath="//input[@value='Login']") 
		WebElement LoginBtn;
		@FindBy(xpath="//button[contains(text(),'Sign Up')]") 
		WebElement SignUpBtn;
		@FindBy(xpath="//img[contains(@class,'img-responsive')]") 
		WebElement crmLogo;
		
		
		//@FindBy is equivalent to driver.findByElements
		public LoginPage(){  // constructor
			PageFactory.initElements(driver, this);
			//init - initialize
			// this means - current class object . 
		}

		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		public Boolean vlaidateCRMImage(){
			return crmLogo.isDisplayed();
		}
		
		public HomePage Login(String UserName, String Pword) throws Exception{
			uName.sendKeys(UserName);
			pword.sendKeys(Pword);
			LoginBtn.click();
			Thread.sleep(3000);
			System.out.println("Clicked on Login Button");
			
			// It will navigate to HomePage
			// Home Page is landing Page so Login Page should return HomePage
			return new HomePage();
		}
		
		
		
}
