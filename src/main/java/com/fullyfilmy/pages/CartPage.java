package com.fullyfilmy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	 private WebDriver driver;
	
	  

	 public CartPage(WebDriver driver) {
	        this.driver = driver;
	    }
	  
	
	 public int getQuantityOfProductInCart() {
	        WebElement quantityElement = driver.findElement(By.xpath("//i[@class='cart-count']"));
	        return Integer.parseInt(quantityElement.getText());
	    }

	    public int getTotalProductsInCart() {
	        WebElement totalProductsElement = driver.findElement(By.xpath("//i[@class='cart-count']"));
	        return Integer.parseInt(totalProductsElement.getText());
	    }
	    }