# nopCommerce-hybrid-framewrok

A Gradle practice testing hybrid-framework project using selenium 3, testNG, log4j2, Extent Report, Jackson.\
This project was implemented over 60 test cases that cover main functions of a e-commerce domain, like: searching, paging, login, register, payment,...

Demo site: 
+ End-user: https://demo.nopcommerce.com/
+ Admin user: https://admin-demo.nopcommerce.com/

Test level: UI, function.

Follow Page Object Model to build common classess for common use and reusable function.

Apply dynamic locator for Object Pattern to locate elements easily and faster.

Apply Extent report for good looking UI, Screenshot on failures, test step definition, Test description.

Apply Jackson to manipulate json test input data and observe test data output for fast and multiple thread safe.

Apply Singleton Pattern to generate single page object (single thread setting)

Apply Factory pattern in building driver instance, PageObject instance.

Run on multiple browser: Chrome, Edge, Firefox,...

Able to run on gradle task CLI

Some image about extent report:

![Extent report](https://github.com/trunghieu-automate/nopCommerce-hybrid-framewrok/blob/main/ExtentReportV5/extentReport-001.jpg)

![Extent report](https://github.com/trunghieu-automate/nopCommerce-hybrid-framewrok/blob/main/ExtentReportV5/extentReport-002.jpg)
