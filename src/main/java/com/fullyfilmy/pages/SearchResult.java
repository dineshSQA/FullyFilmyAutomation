package com.fullyfilmy.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class SearchResult extends BasePage {

	// Locators
	private By searchResults = By.xpath("(//div[@class='name']//a)");
	private By searchBoxLocator = By.xpath("//input[@name='q'])[2]");
    private By searchButtonLocator = By.className("button-search");

	public SearchResult(WebDriver driver) {
		super(driver);
	}

//	 public void performSearch(String searchQuery) {
//	        WebElement searchBox = driver.findElement(searchBoxLocator);
//	        searchBox.sendKeys(searchQuery);
//
//	        WebElement searchButton = driver.findElement(searchButtonLocator);
//	        searchButton.click();
//	    }

	
	    public List<String> getSearchResults() {
	        List<WebElement> resultElements = driver.findElements(searchResults);

	        return resultElements.stream()
	                .map(WebElement::getText)
	                .collect(Collectors.toList());
	    }
	}
	
	

