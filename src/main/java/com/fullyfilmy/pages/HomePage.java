package com.fullyfilmy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	private WebDriver driver;
	private String FullyfilmyUrl = "https://fullyfilmy.in/";
	private By Accbtn = By.className("my-account");
	private By searchbox = By.id("search_query");
	private By searchBoxLocator = By.id("search_query");
	private By searchButtonLocator = By.className("button-search");

	//("my-account");
	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.get(FullyfilmyUrl);
	}
	public RegistrationPage navigateToRegistration() {
		// Implementation to navigate to the registration page
		driver.findElement(Accbtn).click();
		return new RegistrationPage(driver);
	}

	public LoginPage navigateToLogin() {
		// Implementation to navigate to the login page
		driver.findElement(Accbtn).click();
		return new LoginPage(driver);
	}

	public SearchResult searchProduct(String searchKeyword) {
		// Implementation to perform a search and navigate to the search result page
		driver.findElement(searchbox).click();
		WebElement searchBox = driver.findElement(searchBoxLocator);
		searchBox.click();
		searchBox.sendKeys(searchKeyword);

		WebElement searchButton = driver.findElement(searchButtonLocator);
		searchButton.click();

		return new SearchResult(driver);
	}
	 public void addToCart() {
		   WebElement product = driver.findElement(By.xpath("(//div[@class='name']//a)[1]"));
		   Actions actions = new Actions(driver);
		   actions.moveToElement(product).perform();
		   
	        WebElement addToCartButton = driver.findElement(By.xpath("(//button[@title='Add to cart'])[1]"));
	        addToCartButton.click();
	    }

	    public CartPage goToCart() {
	        WebElement cartIcon = driver.findElement(By.className("cart-icon"));
	        cartIcon.click();
	        return new CartPage(driver);
	    }
}
