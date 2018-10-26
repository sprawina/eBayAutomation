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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class eBayHomePage extends Driver{


    public AndroidDriver driver;
    private static Logger logger = Logger.getLogger(loginPage.class);
    TouchAction touch;


    /*
     * Get AndroidDriver by calling Driver class.
     * Assign driver to local driver variable.
     * Create an object for touchAction.
     * Using PageFactory's initElements to find elements.
     */

    public eBayHomePage(AndroidDriver<WebElement> driver){

        super(driver);
        this.driver = driver;
        touch = new TouchAction(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }



    /*
     * Search for a product in the search field.
     * Convert context from CHROMIUM to NATIVE_APP to check visibility and click element.
     * Restore original context.
     *
     * @param name of the item (ex: samsung television)
     * @return true if product searching is successful and shows the list of products.
     */

    public boolean searchProduct(String itemName) throws IOException{

        boolean searchProd = false;
        try{
            homePage.close.click();
            homePage.accnt.click();
            homePage.openSrch.click();
            homePage.searchField.sendKeys(itemName);
            homePage.searchBttn.click();

            String originalContext = driver.getContext();
            driver.context("NATIVE_APP");

            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(resolution));

            if(resolution.isDisplayed()){
                homePage.resolution.click();
                searchProd = true;
                logger.info("Search complete, successful");
                Reporter.log("Product search complete.");
            }
            else {
                logger.info("Search failed");
                commonUtil.takeScreenshot();
            }

            searchProd = true;

            driver.context(originalContext);

        }
        catch (Exception e){
            logger.error("Exception", e);
            commonUtil.takeScreenshot();
            e.printStackTrace();
        }
        return searchProd;
    }



    /*
     * Convert context to NATIVE_APP.
     * Scrolling down and select a product in the product list.
     * Revert back to original context.
     *
     * @return true if product is selected successfully
     */

    public boolean selectProduct(String productName) throws IOException{
        boolean selectItm = false;

        try{
            String originalContext = driver.getContext();
            driver.context("NATIVE_APP");

             Thread.sleep(2000);

            commonUtil.scrollDown();
            Thread.sleep(2000);
            commonUtil.scrollDown();


            homePage.Item.click();
            selectItm = true;
            Reporter.log("Product selection done.");

            Thread.sleep(2000);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


            driver.context(originalContext);
        }
        catch (Exception e){
            logger.error("Error selecting product");
            commonUtil.takeScreenshot();
        }
        return selectItm;
    }



    /*
     * Validate selected product with the details given.
     *
     * @return true if product details matches selected product.
     */

    public boolean validateProductDetails() throws IOException{
        boolean validProduct = false;
        String originalContext = driver.getContext();
        driver.context("NATIVE_APP");
        try{
            if(homePage.productDetails.isDisplayed()){
                validProduct = true;
                Reporter.log("Product details validated.");
            }
        }
        catch (Exception e){
            logger.error("Exception, ", e);
            commonUtil.takeScreenshot();
        }
        driver.context(originalContext);
        return validProduct;
    }







        @FindBy(id = "gh-banner-close")
        public WebElement close;

        @FindBy(id = "gh-mebay")
        public WebElement accnt;

        @FindBy(id = "gh-openSearch")
        public WebElement openSrch;

        @FindBy(id = "kw")
        public WebElement searchField;

        @FindBy(id = "ghs-submit")
        public WebElement searchBttn;

        @FindBy(xpath = "//*[@text='2160p (4K)']")
        public WebElement resolution;

        @FindBy(xpath = "//*[@resource-id='srp-river-results-listing7']")
        public WebElement Item;

        @FindBy(xpath = "//*[@resource-id='vi-frag-imagegallery']")
        public WebElement productDetails;




}
