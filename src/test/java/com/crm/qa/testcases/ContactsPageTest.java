package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;



public class ContactsPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	String sheetName = "Sheet1";
	
	public ContactsPageTest(){
		super(); //to call testBase class constructor
	}
	@BeforeMethod
	public void setUp() throws Exception{
		TestBase.initialization();
		testutil = new TestUtil();
		loginpage = new LoginPage();
		contactspage = new ContactsPage(); 
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.SwitchToFrame();
		homepage.ClickOnContactsLink();
		Thread.sleep(3000);
	}
	@Test(priority=1)
	public void VerifyContactsPageLable(){
		boolean flag = contactspage.VerifyContactPageLable();
		Assert.assertTrue(flag,"Contacts Page is missing");
	}
	
	/*@Test(priority=2)
	public void SelectContact(){
		contactspage.selectContact();
	}*/
	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException, RuntimeException, IOException{
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=2, dataProvider="getCRMTestData")
	public void ValidateCreateNewContact(String title, String ftName, String surName, String comp){
	//	testutil.SwitchToFrame();
		homepage.ClickOnNewContact();
		//contactspage.createNewContact("Mr.","Tom","Geller","Marvel Uneverse");
		contactspage.createNewContact(title, ftName, surName, comp);
		
	}
	@AfterMethod
	public void teardown(){
		driver.quit();
		
	}
	

}
