package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	@FindBy(xpath="//td[contains(text(),'Contacts')]") WebElement contacts;
	@FindBy(xpath="//a[@href='https://www.freecrm.com/system/index.cfm?action=contact&sub=load&edit=0&client_id=&contact_id=51282667']") WebElement name;
	@FindBy(id=("first_name")) WebElement firstname;
	@FindBy(id=("surname")) WebElement surname;
	@FindBy(name=("client_lookup")) WebElement companyName;
	@FindBy(xpath="//input[@type='submit' and @value='Save']") WebElement saveBtn;
	
	public ContactsPage(){  // constructor
		PageFactory.initElements(driver, this);
	}
	public boolean VerifyContactPageLable(){
		return contacts.isDisplayed();
	}
	public void selectContact(){
		name.click();
	}
	public void createNewContact(String title,String ftName, String surName, String compName ){
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstname.sendKeys(ftName);
		surname.sendKeys(surName);
		companyName.sendKeys(compName);
		saveBtn.click();
		
	}
}
