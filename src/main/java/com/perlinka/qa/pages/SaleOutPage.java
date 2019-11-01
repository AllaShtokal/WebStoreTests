package com.perlinka.qa.pages;

import com.perlinka.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.*;

public class SaleOutPage extends TestBase {

    //Page Factory -- object repository
    @FindBy(css = "a[class ='item__link']" )
    List<WebElement> products;

   @FindBy (xpath = "//form/div[2]/ul/li[5]/label")
   //@FindBy (xpath = "//li/label[contains(text(),'Туфли')"]
   WebElement tufliGirlCheckBox;

    @FindBy (xpath = "//form/div[1]/ul/li[3]/label")
    //@FindBy (xpath = "//li/label[contains(text(),'Кроссовки')"]
    WebElement snickersBoyCheckBox;

    @FindBy (xpath = "//button[contains(text(),'Показать')]")
    WebElement applayBtn;

    @FindBy (className = "item__price")
    List<WebElement> itemsPrice;

    @FindBy (xpath = "//nav[@class = 'pager']/ul/li")
    List<WebElement> pages;

    @FindBy (className = "item__name")
    WebElement productNames;

    //initializing the Page Objects:
    SaleOutPage(){
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

    public void clickOnSnickersBoysCheckBox()
    {
        snickersBoyCheckBox.click();


    }

    public String verifySaleOutPageTitle(){
        return driver.getTitle();
    }

    public Boolean verifyPriceVisibility(){
        for (WebElement item: itemsPrice)
        {   System.out.println(item.findElement(By.tagName("b")).getText());
            if (!item.isDisplayed())
            return false;}
        return true;

    }

    public int countNumberOfPages(){

        //System.out.println(pages.size());
         return pages.size();
    }

    public ProductPage clickOnRandomProductCard(){

        Random random = new Random();
        int index = random.nextInt(products.size());
        products.get(index).click();
        return new ProductPage();
    }

    public void goToNextPage(int n) {

        System.out.println("Next Page: " + pages.get(n)
                .findElement(By.tagName("form")).getAttribute("name"));
        pages.get(n).click();


    }

    public void printNumbersForMokasiny() {

        for (WebElement product: products) {
            if(product.findElement(By.className("item__name")).getText().contains("окасины"))
            {
                System.out.println(product.findElement(By.className("item__order-number"))
                        .getText());
            }
        }

    }

    public int countNumberOfProductsOnPage()
    {

        /*for (WebElement product: products)
        {
             System.out.println(product.findElement(By.className("item__name")).getText());
        }*/
        return products.size();
    }
    public List<String> addProductsToList()
    {
        List<String> items = new ArrayList<String>();
        for (WebElement product : products){
            items.add(product.findElement(By.className("item__order-number")).getText() +
                    " LINK: " +product.getAttribute("href") );
        }

        return items;
    }
}
