package Suit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by diana.chizh on 13.12.2017.
 */
public class Main {
     public WebDriver driver ;
    public WebDriverWait wait;
    FindPage objFindPage;
    OffersPage objOffersPge;
    ProductPage objProductPage;

    @Parameters("browser")

    @BeforeTest

    public void setup (String browser){


        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();

        }

        //driver.get(url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
     @Test(priority = 1)
    public void findPageTest() {

         objFindPage = new FindPage(driver);
         objFindPage.getUrl(  DataforTests.url);
         objFindPage.searchBox(DataforTests.product);
         objFindPage.dropDownSelect(DataforTests.find_prod);


    }

    @Test(priority = 2)
    public void allOffersPageTest(){
        objOffersPge = new OffersPage(driver);
        objOffersPge. getCurrentPageTitle(driver);
        objOffersPge.button_Offer(driver);
        objOffersPge.findListOffer();
        objOffersPge.listSize();
        objOffersPge.List_offers(driver);
        objOffersPge.selectMin(driver);




    }

    @Test(priority = 3)
    public void ProductPageTest(){
        objProductPage = new ProductPage(driver);
        objProductPage.checkPage();

    }



    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}