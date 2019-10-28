package com.perlinka.qa.pages;

import com.perlinka.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaleOutPage extends TestBase {

    //Page Factory -- object repository
    @FindBy(css = "a[class ='item__link']" )
    List<WebElement> products;

    @FindBy (xpath = "//form/div[2]/ul/li[5]/label")
    WebElement tufliGirlCheckBox;

    @FindBy (xpath = "//button[contains(tesxt(),'Показавть']")
    WebElement applayBtn;
    //initializing the Page Objects:
    public SaleOutPage(){
        PageFactory.initElements(driver, this);
    }

    //Methods
    public void ApplayFilteringButton(){
        applayBtn.click();

    }
    public void clickOnTufliGirlCheckBox()
    {
        tufliGirlCheckBox.click();

    }
    public String verifySaleOutPageTitle(){
        return driver.getTitle();
    }

    public ProductPage clickOnProductCard(){

        Random random = new Random();
        int index = random.nextInt(products.size());
        products.get(index).click();
        return new ProductPage();
    }


}
