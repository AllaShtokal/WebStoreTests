package com.perlinka.qa.testcases;

import com.perlinka.qa.base.TestBase;
import com.perlinka.qa.pages.MainPage;
import com.perlinka.qa.pages.SaleOutPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTests extends TestBase {
    MainPage mainPage;
    SaleOutPage saleOutPage;

    public MainPageTests(){
        super();//to call TestBase constructor (to init Properties)
    }

    @BeforeMethod
    public void setUp(){

        initialization();
        mainPage = new MainPage();

    }

    @Test(priority = 1)
    public void mainPageTitleTest(){
        String title = mainPage.verifyMainPageTitle();
        Assert.assertEquals(title,"Детская обувь > купить обувь для детей в интернет магазине в" +
                " Одессе, Киеве - доставка по Украине | Perlinka", "mainPage Title is not matched");

    }

    @Test(priority = 2)
    public void imgLogoTest(){
        boolean result = mainPage.validateImgLogo();
        Assert.assertTrue(result);
    }

    @Test(priority = 3)
    public void saleOutMenuBtnTest(){
        saleOutPage = mainPage.clickOnSaleOutMenuBtn();

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
