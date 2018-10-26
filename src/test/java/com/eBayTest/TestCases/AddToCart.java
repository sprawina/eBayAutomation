package com.eBayTest.TestCases;


import com.eBayTest.commonUtilities.Apploader;
import com.eBayTest.commonUtilities.commonUtil;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;


public class AddToCart extends Apploader {



    /*
     * Loading the website.
     * Check if already logged-in- yes?:logout and login:login
     */

    @BeforeTest
    public void logOutCheck(){

        login.enterURL();
        Assert.assertTrue(login.logoutOfApp(),"Assertion Error: Already logged-in, please logout and login again");
    }



    /*
     * Test case:
     * Get the website using URL
     * Check if eBay is logged out
     * Validate login page elements
     * Login using username, password and check if home screen is visible
     * Search for an item (samsung television)
     * Select the required item and go to details
     * In Product detail page: Click Add to cart
     * In Product detail page: Click View in cart
     * Purchase Page: Check out
     * Logout after test completes.
     */

    @Test
    public void ebayShop() throws IOException{

        boolean loginPge = login.validateLoginPage();
        Assert.assertTrue(loginPge,"Assertion Error: Could not validate login page.");

        HashMap testData = commonUtil.getTestData();

        boolean loginCheck = login.validLoginCheck(testData.get("username").toString(),testData.get("password").toString());
        Assert.assertTrue(loginCheck,"Assertion Error: Login failed.");

        boolean searchItem = homePage.searchProduct(testData.get("searchItem").toString());
        Assert.assertTrue(searchItem,"Assertion Error: Searching product failed.");

        boolean selectItem = homePage.selectProduct(testData.get("productName").toString());
        Assert.assertTrue(selectItem,"Assertion Error: Selecting product failed.");

        boolean validateItem = homePage.validateProductDetails();
        Assert.assertTrue(validateItem,"Assertion Error: Could not validate item.");

        boolean addToCart = purchase.addToCart();
        Assert.assertTrue(addToCart,"Assertion Error: Adding to cart failed.");

        boolean checkOutItem = purchase.checkOut();
        Assert.assertTrue(checkOutItem,"Item could not be checked out.");

    }



    /*
     * Logout of eBay after checkout of item.
     */

    @AfterTest
    public void logOutAfterTest() throws IOException{
        Assert.assertTrue(purchase.validateCheckout(),"Assertion Error: Logout after checkout failed.");
    }


}
