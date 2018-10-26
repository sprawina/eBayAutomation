package com.Pages;

import com.eBayTest.commonUtilities.Driver;
import com.eBayTest.commonUtilities.commonUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.io.IOException;

public class purchasePage extends Driver {


    public AndroidDriver driver;
    TouchAction touch;

    private static Logger logger = Logger.getLogger(loginPage.class);



    /*
     * Get AndroidDriver by calling Driver class.
     * Assign driver to local driver variable.
     * Create an object for touchAction.
     * Using PageFactory's initElements to find elements.
     */

    public purchasePage(AndroidDriver<WebElement> driver){

        super(driver);
        this.driver = driver;
        touch = new TouchAction(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }



    /*
     * Scroll down and Add the Item to cart.
     * Click on View in Cart.
     *
     * @return true if item is added to cart successfully.
     */

    public boolean addToCart() throws IOException{
        boolean itemAdded = false;
        try {
            String originalContext = driver.getContext();
            driver.context("NATIVE_APP");

            commonUtil.scrollDown();
            Thread.sleep(1000);
            purchase.addCart.click();

            itemAdded = true;
            Reporter.log("Item added to cart.");

            wait.until(ExpectedConditions.visibilityOf(purchase.viewCart));
            purchase.viewCart.click();

            logger.info("Add item to cart and view in checkout");
            Thread.sleep(5000);

            driver.context(originalContext);
        }
        catch (Exception e){
            logger.error("Exception", e);
            commonUtil.takeScreenshot();
        }

        return itemAdded;
    }




    /*
     * Checkout the item.
     *
     * @return true if check out done sucessfully.
     */

    public boolean checkOut() throws IOException{
        boolean checkedOut = false;
        try{
            String originalContext = driver.getContext();
            driver.context("NATIVE_APP");

            purchase.chckOut.click();
            checkedOut = true;
            logger.info("checkout item");
            Reporter.log("Item checkout done.");

            driver.context(originalContext);
        }
        catch (Exception e){
            logger.error("checkOut(): Exception taking screenshot");
            commonUtil.takeScreenshot();
            e.printStackTrace();
        }

        return checkedOut;
    }



    public boolean validateCheckout() throws IOException{
        boolean validCheckout = false;
        try{
            Thread.sleep(4000);
            String originalContext = driver.getContext();
            driver.context("NATIVE_APP");

            if(purchase.ebayCheckout.isDisplayed()){
                validCheckout = true;
                Reporter.log("Checkout validated");
            }
            driver.context(originalContext);
        }
        catch (Exception e){
            logger.error("Exception, ", e);
            commonUtil.takeScreenshot();
        }
        return validCheckout;
    }







        @FindBy(xpath = "//*[@text='Add to cart']")
        public WebElement addCart;

        @FindBy(xpath = "//*[@resource-id='vi-view-in-cart-button'][@text='View In Cart']")
        public WebElement viewCart;

        @FindBy(xpath = "//*[@text='Go to checkout']")
        public WebElement chckOut;

        @FindBy(xpath = "//*[@resource-id='tbdHdr']")
        public WebElement ebayCheckout;





}
