package com.perlinka.qa.testcases;

import com.perlinka.qa.base.TestBase;
import com.perlinka.qa.pages.MainPage;
import com.perlinka.qa.pages.ProductPage;
import com.perlinka.qa.pages.SaleOutPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Vector;

import static com.perlinka.qa.util.TestUtil.NUM_OF_PRODUCTS_TO_TEST;

public class SaleOutPageTests extends TestBase {
    MainPage mainPage;
    SaleOutPage saleOutPage;
    ProductPage productPage;

    public SaleOutPageTests(){
        super();//to call TestBase constructor (to init Properties)
    }

    @BeforeMethod
    public void setUp(){

        initialization();
        mainPage = new MainPage();
        saleOutPage = mainPage.clickOnSaleOutMenuBtn();
        productPage = saleOutPage.clickOnProductCard();


    }

    @Test(priority = 1)
    public void saleOutPageTitleTest(){
        String title = saleOutPage.verifySaleOutPageTitle();
        Assert.assertEquals(title,"Распродажа детской обуви в Одессе, Киеве - доставка по" +
                " Украине > заказать в интернет-магазине | Perlinka", "SaleOutPage Title is not matched");

    }

    @Test(priority = 2)
    public void clickOnProductCardTest(){
        saleOutPage.clickOnProductCard();
    }

    @Test(priority = 3)
    public void ProductCardTestTask1(){

        for(int k=0;k< NUM_OF_PRODUCTS_TO_TEST;++k)
        {

           Vector<Boolean> prices = productPage.checkNewPriceVisibility();
            for (int i = 0; i<prices.size();++i)
            {
                Assert.assertTrue(prices.get(i), "New price of the product " +
                        "on the page [ "+ driver.getCurrentUrl() +" ] is not displayed");
            }
            Assert.assertTrue(productPage.checkDiscountEndIsNotNull(), "DiscountEnd " +
                    "on the page [ "+ driver.getCurrentUrl() +" ] is NULL");
            driver.navigate().back();
            productPage = saleOutPage.clickOnProductCard();

        }


    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }




}
