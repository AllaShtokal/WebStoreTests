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
            for (Boolean price : prices) {
                Assert.assertTrue(price, "New price of the product " +
                        "on the page [ " + driver.getCurrentUrl() + " ] is not displayed");
            }
            //Assert.assertTrue(productPage.checkDiscountEndIsNotNull(), "DiscountEnd " +
            //  "on the page [ " + driver.getCurrentUrl() + " ] is NULL");
            Assert.assertTrue(productPage.verifySizesAreAvailable(), "No Size " +
                    "on the page [ " + driver.getCurrentUrl() + " ] is available");
            driver.navigate().back();


        }
    }

    @Test(priority = 4)
    public void TestTask2() {
        saleOutPage.selectCheckBox("Туфли Девочки");
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

        saleOutPage.selectCheckBox("Кроссовки Мальчики");
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

        saleOutPage.selectCheckBox("Туфли Девочки");
        saleOutPage.ApplayFilteringButton();
        Count(listGirlsTufli);
        System.out.println("ДевочкиТуфли = " + listGirlsTufli.size() + " шт.");

        saleOutPage.selectCheckBox("Туфли Девочки");
        saleOutPage.selectCheckBox("Кроссовки Мальчики");
        saleOutPage.ApplayFilteringButton();
        Count(listBoysSnickers);
        System.out.println("МальчикиКроссовки = " + listBoysSnickers.size() + " шт.");

        saleOutPage.selectCheckBox("Туфли Девочки");
        saleOutPage.ApplayFilteringButton();
        Count(listTotal);
        System.out.println("Мальчики И Дeвочки вместе = " + listTotal.size() + " шт.");

        //If we delete two elements in listTotal
        //listTotal.remove(1);
        //listTotal.remove(5);
        //делаем сравнение
        Assert.assertEquals(listGirlsTufli.size() + listBoysSnickers.size(), listTotal.size(),
                ProductsNotMatched(listGirlsTufli, listBoysSnickers, listTotal));


    }

    public List Count(ArrayList l){
        for(int i =0;;) {
            l.addAll(saleOutPage.addProductsToList());
            System.out.println(saleOutPage.countNumberOfProductsOnPage());
            ++i;
            if (i < saleOutPage.countNumberOfPages())
                saleOutPage.goToNextPage(i);
            else break;
        }
        return l;
    }

    public String ProductsNotMatched(ArrayList<String> l1, ArrayList<String> l2, ArrayList<String> l3) {
        StringBuilder s = new StringBuilder(" \n Elements are not matched : \n");
        for (String el : l1) {
            if (l3.contains(el))
                continue;
            else s.append(el).append("\n");
        }
        for (String el : l2) {
            if (!l3.contains(el))
                s.append(el).append("\n");
        }


        return s.toString();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
