package com.eBayTest.commonUtilities;

import com.Pages.eBayHomePage;
import com.Pages.loginPage;
import com.Pages.purchasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class Apploader {

    public static AndroidDriver driver;
    public static String propertyFile="config.properties";
    public static WebDriverWait wait;

    public static loginPage login;
    public static eBayHomePage homePage;
    public static purchasePage purchase;



    @BeforeSuite(alwaysRun = true)
    public void setup() throws IOException{

        if(driver==null){
            commonUtil.loadConfig(propertyFile);
            commonUtil.setCapabilities();

            driver=commonUtil.getDriver();

            login = new loginPage(driver);
            homePage = new eBayHomePage(driver);
            purchase = new purchasePage(driver);

            wait = new WebDriverWait(driver,30);
        }
    }



    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
