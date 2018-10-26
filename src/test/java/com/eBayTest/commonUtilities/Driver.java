package com.eBayTest.commonUtilities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;


public class Driver extends Apploader{

    public AndroidDriver<WebElement> driver;

    public Driver(AndroidDriver<WebElement> driver) {

        this.driver = driver;
    }
}
