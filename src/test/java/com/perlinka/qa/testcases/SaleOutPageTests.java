package com.perlinka.qa.testcases;

import com.perlinka.qa.base.TestBase;
import com.perlinka.qa.pages.MainPage;
import com.perlinka.qa.pages.ProductPage;
import com.perlinka.qa.pages.SaleOutPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static com.perlinka.qa.util.TestUtil.NUM_OF_PRODUCTS_TO_TEST;

public class SaleOutPageTests extends TestBase {
    MainPage mainPage;
    SaleOutPage saleOutPage;
    ProductPage productPage;

    public SaleOutPageTests() {
        super();//to call TestBase constructor (to init Properties)
    }

    @BeforeMethod
    public void setUp() {

        initialization();
        mainPage = new MainPage();
        saleOutPage = mainPage.clickOnSaleOutMenuBtn();


    }

    @Test(priority = 1)
    public void saleOutPageTitleTest() {
        String title = saleOutPage.verifySaleOutPageTitle();
        Assert.assertEquals(title, "Распродажа детской обуви в Одессе, Киеве - доставка по" +
                " Украине > заказать в интернет-магазине | Perlinka", "SaleOutPage Title is not matched");

    }

    @Test(priority = 2)
    public void clickOnProductCardTest() {
        saleOutPage.clickOnRandomProductCard();
    }

    @Test(priority = 3)
    public void TestTask1() {

        for (int k = 0; k < NUM_OF_PRODUCTS_TO_TEST; ++k) {
            productPage = saleOutPage.clickOnRandomProductCard();
            Vector<Boolean> prices = productPage.checkNewPriceVisibility();
            for (int i = 0; i < prices.size(); ++i) {
                Assert.assertTrue(prices.get(i), "New price of the product " +
                        "on the page [ " + driver.getCurrentUrl() + " ] is not displayed");
            }
            Assert.assertTrue(productPage.checkDiscountEndIsNotNull(), "DiscountEnd " +
                    "on the page [ " + driver.getCurrentUrl() + " ] is NULL");
            Assert.assertTrue(productPage.verifySizesAreAvailable(), "No Size " +
                    "on the page [ " + driver.getCurrentUrl() + " ] is available");
            driver.navigate().back();


        }
    }

    @Test(priority = 4)
    public void TestTask2() {
        saleOutPage.clickOnTufliGirlCheckBox();
        saleOutPage.ApplayFilteringButton();

        for (int i = 1; i <= saleOutPage.countNumberOfPages(); ++i) {
            Assert.assertTrue(saleOutPage.verifyPriceVisibility(), "Price isn't displayed " +
                    "on the page [ " + driver.getCurrentUrl());
            if (i != saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);

        }

    }

    @Test(priority = 5)
    public void TestTask3() {

        saleOutPage.clickOnSnickersBoysCheckBox();
        saleOutPage.ApplayFilteringButton();

        for (int i = 1; i <= saleOutPage.countNumberOfPages(); ++i) {
            saleOutPage.printNumbersForMokasiny();
            if (i != saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);
        }
    }

    @Test(priority = 6)
    public void TestTask4() {
        ArrayList<String> listGirlsTufli = new ArrayList<String>();
        ArrayList<String> listBoysSnickers = new ArrayList<String>();
        ArrayList<String> listTotal = new ArrayList<String>();

        //ДевочкиТуфли
        saleOutPage.clickOnTufliGirlCheckBox();
        saleOutPage.ApplayFilteringButton();
        //считаем здесь
        int numOfGirlsTufli = 0;
        for (int i = 1; i <= saleOutPage.countNumberOfPages(); ++i) {
            listGirlsTufli.addAll(saleOutPage.addProductsToList());
            numOfGirlsTufli += saleOutPage.countNumberOfProductsOnPage();
            System.out.println(saleOutPage.countNumberOfProductsOnPage());
            if (i != saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);
        }
        System.out.println("ДевочкиТуфли = " + numOfGirlsTufli + " шт.");

        //МальчикиКроссовки
        saleOutPage.clickOnTufliGirlCheckBox();
        saleOutPage.clickOnSnickersBoysCheckBox();
        saleOutPage.ApplayFilteringButton();
        //считаем здесь
        int numOfBoysSnickers = 0;
        for (int i = 1; i <= saleOutPage.countNumberOfPages(); ++i) {
            listBoysSnickers.addAll(saleOutPage.addProductsToList());
            numOfBoysSnickers += saleOutPage.countNumberOfProductsOnPage();
            System.out.println(saleOutPage.countNumberOfProductsOnPage());
            if (i != saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);
        }
        System.out.println("МальчикиКроссовки = " + numOfBoysSnickers + " шт.");

        //И Мальчики, и Девочки
        saleOutPage.clickOnTufliGirlCheckBox();
        saleOutPage.ApplayFilteringButton();
        //считаем здесь

        int numOfAllProducts = saleOutPage.countNumberOfProductsOnPage();
        listTotal.addAll(saleOutPage.addProductsToList());
        for (int i = 1; i <= saleOutPage.countNumberOfPages(); ++i) {
            if (i != saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);
            numOfAllProducts += saleOutPage.countNumberOfProductsOnPage();
            listTotal.addAll(saleOutPage.addProductsToList());
            System.out.println(saleOutPage.countNumberOfProductsOnPage());
        }
        System.out.println("Мальчики И Дeвочки вместе = " + numOfAllProducts + " шт.");

        //удалим 1 ел в listTotal
        listTotal.remove(1);
        listTotal.remove(5);
        //делаем сравнение
        Assert.assertEquals(listGirlsTufli.size() + listBoysSnickers.size(), listTotal.size(),
                ProductsNotMatched(listGirlsTufli,listBoysSnickers, listTotal));
        // если не совпадает

    }

    public String ProductsNotMatched(ArrayList<String> l1,ArrayList<String> l2, ArrayList<String> l3) {
        String s = " \n";
        for(String el: l1)
        {
            if(l3.contains(el))
                continue;
            else s+= el +"\n";
        }
        for(String el: l2)
        {
            if(l3.contains(el))
                continue;
            else s+= el +"\n";
        }


    return s;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }




}
