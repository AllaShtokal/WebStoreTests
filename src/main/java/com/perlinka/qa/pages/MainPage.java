package com.perlinka.qa.pages;

import com.perlinka.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends TestBase {

    //Page Factory -- object repository
    @FindBy(xpath = "//*[@id=\"menu\"]/li[4]/a")
    WebElement saleOutMenuBtn;

    @FindBy(xpath = "//img[contains(@src,'ua_logo')]")
    WebElement imgLogo;

    //initializing the Page Objects:

    public MainPage(){
      PageFactory.initElements(driver, this);
    }

    //Actions
    public String verifyMainPageTitle(){
        return driver.getTitle();
    }

    public boolean validateImgLogo(){
        return imgLogo.isDisplayed();
    }

    public SaleOutPage clickOnSaleOutMenuBtn(){
       saleOutMenuBtn.click();
       return new SaleOutPage();
    }


}
