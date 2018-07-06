package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	public HomePageTest(){
		super(); //to call testBase class constructor
	}
	@BeforeMethod
	public void setUp() throws Exception{
		TestBase.initialization();
		testutil = new TestUtil();
		loginpage = new LoginPage();
		contactspage = new ContactsPage(); 
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void VerifyHomePageTitleTest(){
		String HomePageTitle = homepage.VerifyHomePageTitle();
		System.out.println(HomePageTitle);
		Assert.assertEquals(HomePageTitle, "CRMPRO","HomePage title not matched");
	}
	@Test
	public void VerifyCurrentUser(){
		testutil.SwitchToFrame();
		boolean User = homepage.VerifyCurrentUser();
		Assert.assertTrue(User);
	}
	
	@Test
	public void VerifyContactsLinkTest(){
		testutil.SwitchToFrame();
		contactspage = homepage.ClickOnContactsLink();
		
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}
	
	

}
