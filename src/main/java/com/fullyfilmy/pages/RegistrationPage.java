package com.fullyfilmy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
	
	private By Continuebtn=By.xpath("//a[contains(text(),'Continue')]");
	private By usernameField = By.xpath("//input[@placeholder='E-Mail']");
	private By passwordField = By.xpath("//input[@placeholder='Password']");
	private By submitBtn = By.xpath("//input[@value='Submit']");
	private By errorMessage = By.className("warning");

	public RegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void registerUser(String username, String password) {
   	 driver.findElement(Continuebtn).click();

        
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        
        driver.findElement(submitBtn).click();
   }


}
