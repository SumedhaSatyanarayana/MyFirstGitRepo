package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;



public class LoginPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	public LoginPageTest(){
		super(); //to call testBase class constructor

	}

	@BeforeMethod
	public void setup(){
		TestBase.initialization();
		loginpage = new LoginPage();

	}

	@Test
	public void LoginPageTileTest(){
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software in the cloud powers sales and customer service");
	}

	@Test
	public void LoginPageCRMImgTest(){
		boolean flag = loginpage.vlaidateCRMImage();
		Assert.assertTrue(flag);
	}

	@Test
	public void LoginTest() throws Exception{
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));

	}


	@AfterMethod
	public void teardown(){
		driver.quit();
	}
}
