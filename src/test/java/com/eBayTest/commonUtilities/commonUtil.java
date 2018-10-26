package com.eBayTest.commonUtilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class commonUtil {

    public static String PLATFORM_NAME;
    public static String PLATFORM_VERSION;
    public static String DEVICE_NAME;
    public static String BROWSER_NAME;
    public static String CHROME_DRIVER;
    public static String AUTOMATION_NAME;
    public static Properties property = new Properties();
    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static AndroidDriver driver;
    public static URL url;
    public static String SERVER;
    public static String NO_RESET;

    public static String propertyFile="config.properties";
    public static WebDriverWait wait;

    public static void loadConfig(String fileName) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + "/src/test/resources/Properties/" + fileName);
        property.load(fis);

        PLATFORM_NAME = property.getProperty("platformName");
        PLATFORM_VERSION = property.getProperty("platform.version");
        DEVICE_NAME = property.getProperty("device.name");
        BROWSER_NAME = property.getProperty("browserName");
        CHROME_DRIVER = property.getProperty("chromeDriverExecutable");
        AUTOMATION_NAME = property.getProperty("automationName");
        NO_RESET = property.getProperty("noReset");
        SERVER = property.getProperty("server.url");
    }


    public static void setCapabilities(){

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,commonUtil.PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,commonUtil.PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, commonUtil.DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, commonUtil.BROWSER_NAME);
        capabilities.setCapability("chromedriverExecutable",commonUtil.CHROME_DRIVER);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, commonUtil.NO_RESET);
    }


    public static AndroidDriver getDriver() throws MalformedURLException{

        url = new URL(commonUtil.SERVER);
        driver = new AndroidDriver(new URL(commonUtil.SERVER), capabilities);
        return driver;
    }

    public static HashMap getTestData() throws IOException{

        HashMap<String,String> testData = new HashMap<String, String>();

        testData.put("username",loadTestData("username"));
        testData.put("password",loadTestData("password"));
        testData.put("searchItem",loadTestData("searchItem"));
        testData.put("productName",loadTestData("productName"));

        return testData;
    }


    public static String loadTestData(String keyName) throws IOException
    {
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/Properties/TestData/" + "mock" + ".properties");
        property.load(fis);
        return property.getProperty(keyName);
    }


    public static void scrollDown() throws InterruptedException{

        Dimension size  = driver.manage().window().getSize();
        int startX = size.getWidth()/2;
        int startY = (int)(size.getHeight() * 0.8);
        int endY = (int)(size.getHeight() * 0.2);

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX,endY)).release().perform();

        Thread.sleep(3000);

    }


    public static void takeScreenshot() throws IOException{

        String origContext = driver.getContext();
        driver.context("NATIVE_APP");

        String destDir = "Screenshots";
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        new File(destDir).mkdirs();
        String screenShotName = timeStamp.format(new Date()) +".png";
        FileUtils.copyFile(source, new File(destDir + "/" + screenShotName));

        driver.context(origContext);
    }

}
