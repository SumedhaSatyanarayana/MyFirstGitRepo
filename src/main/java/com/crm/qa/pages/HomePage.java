package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	// define the obj repository
	// page factory - OR (object repository)
	@FindBy(xpath="//td[contains(text(),'User: Sumo S')]") 
	@CacheLookup
	WebElement currentUser;
	@FindBy(xpath="//a[contains(text(),'Contacts')]") WebElement contacts;
	@FindBy(xpath="//a[contains(text(),'Deals')]") WebElement deals;
	@FindBy(xpath="//a[contains(text(),'Tasks')]") WebElement tasks;
	@FindBy(xpath="//a[contains(text(),'New Contact')]") WebElement newContact;
	public HomePage(){  // constructor
		PageFactory.initElements(driver, this);
		//init - initialize
		// this means - current class object . 
	}
	
	public String VerifyHomePageTitle(){
		return driver.getTitle();
	}
	public boolean VerifyCurrentUser(){
		
		boolean User = currentUser.isDisplayed();
		return User;
	}
	public ContactsPage ClickOnContactsLink(){
		contacts.click();
		return new ContactsPage();
	}
	public DealsPage ClickOnDealsLink(){
		deals.click();
		return new DealsPage();
	}
	public TasksPage ClickOnTasksLink(){
		tasks.click();
		return new TasksPage();
	}
	public void ClickOnNewContact(){
		Actions action = new Actions(driver);
		action.moveToElement(contacts).build().perform();
		newContact.click();
	}
}
