# eBayAutomation
Automation testing of eBay website via chrome browser in android emulator.

Softwares used: IntelliJ IDE, Appium 1.7.2, Android emulator (API 28).

Testcase File:
1. AddToCart

Pages:
1. loginPage - login with username, password.
2. eBayHomePage - search product, select product, validate product details.
3. purchasePage - add to cart, view in cart, check out, validate checkout.

CommonUtilities:
1. Apploader - creates page objects, set driver configurations.
2. commonUtil - set desired capabilities, load test data from property file, scroll down function, screenshot function.
3. Driver - initialises AndroidDriver.
