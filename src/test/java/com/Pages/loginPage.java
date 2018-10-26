package com.Pages;

import com.eBayTest.commonUtilities.Driver;
import com.eBayTest.commonUtilities.commonUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.io.IOException;



public class loginPage extends Driver {


    public AndroidDriver driver;

    private static Logger logger = Logger.getLogger(loginPage.class);


    /*
     * Get AndroidDriver by calling Driver class.
     * Assign driver to local driver variable.
     * Using PageFactory's initElements to find elements.
     */

    public loginPage(AndroidDriver<WebElement> driver){

        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }



    /*
     * Get the Website URL from property file.
     * Load the website.
     */

    public void enterURL(){

        try{
            logger.info("Test started");
            String urlField = commonUtil.loadTestData("webUrl");
            driver.get(urlField);
            logger.info("Loading website...");
            Reporter.log("Website loaded.");
        }
        catch (IOException e){
            logger.error("enterURL(): IOException while fetching URL", e);
            e.printStackTrace();
        }
    }



    /*
    * Logout of the eBay website.
    *
    * @return true if log out successful or already logged out.
     */

    public boolean logoutOfApp(){

        boolean logoutStatus = false;
        try{
            Thread.sleep(3000);
            if(login.signIn.isDisplayed()){
                logoutStatus = true;
            }
            else {
                login.ebayLogo.click();
                login.accntButton.click();
                commonUtil.scrollDown();
                login.signOut.click();
                Thread.sleep(3000);

                if(login.signIn.isDisplayed()){
                    logoutStatus = true;
                }
                else {
                    logger.error("Error logging out");
                    commonUtil.takeScreenshot();
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception in logoutOfApp()");
        }

        return logoutStatus;
    }



    /**
     * Validate the fields in the login page.
     * Check if all fields are displayed before Logging in.
     *
     * @return valid Returns true if all the fields in login screen are displayed.
     */

    public boolean validateLoginPage() throws IOException{

        boolean valid = false;
        try{
            if(login.username.isDisplayed()){
                if(login.password.isDisplayed()){
                    if(login.chckBox.isDisplayed()){
                        if(login.signIn.isDisplayed()){
                            valid = true;
                            Reporter.log("LoginPage validated.");
                        }
                    }
                }
            }
            else {
                logger.error("loginPage not validated!");
            }

        }
        catch (Exception e){
            logger.error("Exception in loginPage: ", e);
            commonUtil.takeScreenshot();
        }

        return valid;
    }



    /*
    * Log in with username, password and un-check "stay signed-in".
    * Click on Sign In button.
    *
    * @param username and password.
    * @return true if login is successful.
     */

    public boolean validLoginCheck(String usr, String passwrd) throws IOException {

        boolean loginStatus = false;
        try {

            login.username.clear();
            login.username.sendKeys(usr);
            logger.info("Get username and send");

            login.password.clear();
            login.password.sendKeys(passwrd);
            logger.info("Get password and send");

            login.chckBox.click();
            login.signIn.click();
            logger.info("Signing in");

            if(login.accntButton.isDisplayed()){
                loginStatus = true;
                Reporter.log("Sign-In successful.");
            }
        }
        catch (Exception e){
            commonUtil.takeScreenshot();
            logger.error("Login Exception: Could not Log In");
        }
        return loginStatus;
    }






        @FindBy(id = "userid")
        public WebElement username;

        @FindBy(id = "pass")
        public WebElement password;

        @FindBy(id = "csi")
        public WebElement chckBox;

        @FindBy(id = "sgnBt")
        public WebElement signIn;

        @FindBy(xpath = "//*[@text='Sign out']")
        public WebElement signOut;

        @FindBy(id = "gh-mebay")
        public WebElement accntButton;

        @FindBy(xpath = "//*[@text='eBay']")
        public WebElement ebayLogo;




}
