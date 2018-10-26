# eBayAutomation
Automation testing of eBay website via chrome browser in android emulator.

Softwares used: IntelliJ IDE, Appium 1.7.2, Android emulator (API 28).

Testcase File:
1. eBayTest/TestCases/AddToCart.java

Pages:
1. eBayTest/Pages/loginPage.java - login with username, password.
2. eBayTest/Pages/eBayHomePage.java - search product, select product, validate product details.
3. eBayTest/Pages/purchasePage.java - add to cart, view in cart, check out, validate checkout.

CommonUtilities:
1. eBayTest/commonUtilities/Apploader.java - creates page objects, set driver configurations.
2. eBayTest/commonUtilities/commonUtil.java - set desired capabilities, load test data from property file, scroll down           function, screenshot function.
3. eBayTest/commonUtilities/Driver.java - initialises AndroidDriver.
