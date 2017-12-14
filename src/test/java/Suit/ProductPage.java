package Suit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static Suit.DataforTests.*;


/**
 * Created by diana.chizh on 14.12.2017.
 */
public class ProductPage extends Main {

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }//  check that driver switch to product page





    public String checkPage(){
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
 prodPage = driver.getCurrentUrl();
 System.out.println("Product page is"+" "+ prodPage );
Assert.assertFalse(DataforTests.prodPage.contentEquals(currenPage));

System.out.println("Last page is"+" "+ DataforTests.currenPage);
return prodPage;
}

}
