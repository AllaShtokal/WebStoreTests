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

public class ProductPageTests extends TestBase {
    MainPage mainPage;
    SaleOutPage saleOutPage;
    ProductPage productPage;

    public ProductPageTests(){
        super();//to call TestBase constructor (to init Properties)
    }

    @BeforeMethod
    public void setUp(){

        initialization();
        mainPage = new MainPage();
        saleOutPage = mainPage.clickOnSaleOutMenuBtn();
        productPage = saleOutPage.clickOnProductCard();

    }

    @Test
    public void newPriceVisibilityTest(){
        Vector<Boolean> vector = productPage.checkNewPriceVisibility();
        for (int i = 0; i<vector.size();++i)
        {
            Assert.assertTrue(vector.get(i), "New price of the product on the page [ "+ driver.getCurrentUrl() +" ] is not displayed");
        }
    }

    //@AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
