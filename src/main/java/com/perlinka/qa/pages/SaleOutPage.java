package com.perlinka.qa.pages;

import com.perlinka.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class SaleOutPage extends TestBase {

    //Page Factory -- object repository
    @FindBy(css = "a[class ='item__link']" )
    List<WebElement> products;

    //to test my test :D
    @FindBy(xpath = "/html/body/div[1]/main/div/div/div/div[3]/div[1]/div[1]/a")
    WebElement testProduct;

    //initializing the Page Objects:
    public SaleOutPage(){
        PageFactory.initElements(driver, this);
    }

    //Methods
    public String verifySaleOutPageTitle(){
        return driver.getTitle();
    }

    public ProductPage clickOnProductCard(){

        Random random = new Random();
        int index = random.nextInt(products.size());
        products.get(index).click();
        return new ProductPage();
        //to test my test :D
         /*testProduct.click();
        return  new ProductPage();*/





    }


}
