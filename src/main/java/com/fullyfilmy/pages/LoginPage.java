package com.fullyfilmy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage extends BasePage {

    // Locators
	
    private By usernameField = By.id("input-email");
   private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By errorMessage = By.className("warning");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
//    
    
    
    
    
//    

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

   
}

