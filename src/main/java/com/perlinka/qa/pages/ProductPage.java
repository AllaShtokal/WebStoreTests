package com.perlinka.qa.pages;

import com.perlinka.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductPage extends TestBase {

    //Page Factory -- object repository
    @FindBy(css = "span[class ='item-details__new-price']" )
    List<WebElement> newPrices;

    @FindBy(xpath = "//input[contains(@name, 'size[')]")
    List<WebElement> sizes;

   // @FindBy(className = "item-details__discount-end")
    //WebElement discountEnd;

    //initializing the Page Objects:
    public ProductPage(){
        PageFactory.initElements(driver, this);
    }

    //Methods
    public  Boolean verifySizesAreAvailable(){
        for(WebElement size: sizes)
        {
            if (size != null)
            {System.out.println("First available size is " + size.getAttribute("name"));
            return true;}

        }
        return false;
    }
    public Boolean checkDiscountEndIsNotNull(){
        WebElement discountEnd;
         try{ discountEnd = driver.findElement(By.className("item-details__discount-end"));
            }
        catch (NoSuchElementException e ){
            return true;
        }

        //discountEnd = driver.findElement(By.className("item-details__discount-end"));
        if( discountEnd.isDisplayed() && discountEnd.findElement(By.tagName("b")).getText() != null)
                return true;
            else return false;


    }

    public Vector<Boolean> checkNewPriceVisibility() {

        Vector<Boolean> vector = new Vector<Boolean>(newPrices.size());

        for (WebElement price: newPrices)
        {
            vector.add(price.isDisplayed());
            System.out.println("Price: " + price.findElement(By.tagName("b")).getText());
        }
        return vector;


    }



}
