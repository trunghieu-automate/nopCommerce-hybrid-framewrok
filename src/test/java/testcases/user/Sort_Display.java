package testcases.user;

import actions.common.BaseTest;
import actions.common.Constants;
import actions.pageObjects.user.HomePageObject;
import actions.pageObjects.user.PageGeneratorManager;
import actions.pageObjects.user.ProductPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.logs.Log;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Sort_Display extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    ProductPageObject productPage;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser) {
        driver = getDriver(browser, Constants.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);

        Log.info("Pre condition: navigate to notebooks page by click to product menu bar from home page");
        productPage = homePage.clickToProductMenuBarByTextName("Notebooks");
    }

    @Test
    public void TC_01_Sort_Name_A_Z(Method method){
        startTest(method.getName(), "Sort by name from A to Z, Verify product is sort properly");

        Log.info(method.getName() + " - " + "Step 01: Select options Name: A to Z from Sort by dropdown list");
        productPage.selectDropDownListById("products-orderby", "Name: A to Z");

        Log.info(method.getName() + " - " + "Step 02: Verify product is sorted ascending");
        verifyTrue(productPage.isProductsSortedByNameAsOrder("ascending"));
    }

    @Test
    public void TC_02_Sort_Name_Z_A(Method method){
        startTest(method.getName(), "Sort by name from Z to A, Verify product is sort properly");

        Log.info(method.getName() + " - " + "Step 01: Select options Name:Z to A from Sort by dropdown list");
        productPage.selectDropDownListById("products-orderby", "Name: Z to A");

        Log.info(method.getName() + " - " + "Step 02: Verify product is sorted descending");
        verifyTrue(productPage.isProductsSortedByNameAsOrder("descending"));
    }

    @Test
    public void TC_03_Sort_Price_Low_To_High(Method method){
        startTest(method.getName(), "Sort by price from A to Z, Verify product is sort properly");

        Log.info(method.getName() + " - " + "Step 01: Select options Price: Low to High from Sort by dropdown list");
        productPage.selectDropDownListById("products-orderby", "Price: Low to High");

        Log.info(method.getName() + " - " + "Step 02: Verify product is sorted ascending");
        verifyTrue(productPage.isProductsSortByPriceAsOrder("ascending"));
    }

    @Test
    public void TC_04_Sort_Price_High_To_Low(Method method){
        startTest(method.getName(), "Sort by price from Z to A, Verify product is sort properly");

        Log.info(method.getName() + " - " + "Step 01: Select options Price: High to Low from Sort by dropdown list");
        productPage.selectDropDownListById("products-orderby", "Price: High to Low");

        Log.info(method.getName() + " - " + "Step 02: Verify product is sorted ascending");
        verifyTrue(productPage.isProductsSortByPriceAsOrder("descending"));
    }

    @Test
    public void TC_05_Display_3_Product_Per_Page(Method method){
        startTest(method.getName(), "Paging 3 products a page, verify number of product displayed is less than or equals to 3");

        Log.info(method.getName() + " - " + "Step 01: Select options '3' from display dropdown list");
        productPage.selectDropDownListById("products-pagesize", "3");

        Log.info(method.getName() + " - " + "Step 02: Verify less than or equals 3 products are displayed");
        verifyTrue(productPage.isNumberOfProductsDisplayedLessThanOrEqualTo(3));
    }

    @Test
    public void TC_06_Display_6_Product_Per_Page(Method method){
        startTest(method.getName(), "Paging 6 products a page, verify number of product displayed is less than or equals to 6");

        Log.info(method.getName() + " - " + "Step 01: Select options '6' from display dropdown list");
        productPage.selectDropDownListById("products-pagesize", "6");

        Log.info(method.getName() + " - " + "Step 02: Verify less than or equals 6 products are displayed");
        verifyTrue(productPage.isNumberOfProductsDisplayedLessThanOrEqualTo(6));
    }

    @Test
    public void TC_07_Display_9_Product_Per_Page(Method method){
        startTest(method.getName(), "Paging 9 product a page, verify number of product displayed is less than or equals to 9");

        Log.info(method.getName() + " - " + "Step 01: Select options '9' from display dropdown list");
        productPage.selectDropDownListById("products-pagesize", "9");

        Log.info(method.getName() + " - " + "Step 02: Verify less than or equals 9 products are displayed");
        verifyTrue(productPage.isNumberOfProductsDisplayedLessThanOrEqualTo(9));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
