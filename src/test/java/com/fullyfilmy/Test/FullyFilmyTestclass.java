package com.fullyfilmy.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fullyfilmy.pages.CartPage;
import com.fullyfilmy.pages.HomePage;
import com.fullyfilmy.pages.LoginPage;
import com.fullyfilmy.pages.RegistrationPage;
import com.fullyfilmy.pages.SearchResult;

public class FullyFilmyTestclass {
	private WebDriver driver;
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test(dataProvider = "registrationData", dataProviderClass = ExcelUtils.class)
	public void testRegistration(String username, String password) {
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = homePage.navigateToRegistration();
		registrationPage.registerUser(username, password);
		// Implement assertions for successful registration
	}

	@Test(dataProvider = "LoginData", dataProviderClass = ExcelUtils.class)
	public void testSignIn(String username, String password) {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = homePage.navigateToLogin();
		loginPage.login(username, password);
	}
	@Test(dataProvider ="searchData", dataProviderClass = ExcelUtils.class)
	public void testSearchAndWriteToExcel(String searchQuery) {
		HomePage homePage = new HomePage(driver);
		SearchResult SearchResult = homePage.searchProduct(searchQuery);


		// Assuming you have a SearchResult page and a method to retrieve search results
		SearchResult searchResultPage = new SearchResult(driver);
		List<String> searchResults = searchResultPage.getSearchResults();

		// Print search results to console
		System.out.println("Search Results for Query '" + searchQuery + "':");
		for (String result : searchResults) {
			System.out.println(result);
		}
		ExcelUtils.writeToExcel("./TestData/test-data.xlsx","SearchData",searchResults);

		}
	@Test()
	public void testAddToCartAndUpdate() {
		
		HomePage homePage = new HomePage(driver);
		homePage.addToCart();

		
		CartPage CartPage = homePage.goToCart();

		// cart contains the correct product and quantity
		int cartProductQuantity = CartPage.getQuantityOfProductInCart();
		Assert.assertEquals(cartProductQuantity, 1);  

		//  total number of products in the cart
		int totalProductsInCart = CartPage.getTotalProductsInCart();
		Assert.assertEquals(totalProductsInCart, 1); 
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
