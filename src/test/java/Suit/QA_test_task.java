package Suit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;

import java.util.*;


import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by diana.chizh on 06.12.2017.
 */
public class QA_test_task {
    public WebDriver driver;
    public static Logger Log = Logger.getLogger(QA_test_task.class.getName());
    public WebDriverWait wait;
    private By searchbox = By.id("searchbox");
    private By button_offers = By.xpath("//*[@id=\"page-product\"]/div[3]/div[1]/ul/li[2]/span");


    @Parameters("browser")

    @BeforeTest
    public void beforeTest(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();

        }
         String url ="http://hotline.ua/";
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test(priority = 0)
    public void find_element() {

        driver.findElement(searchbox ).sendKeys("iPhone");

         Log.info("Wait until drop_down list is present on page");

        WebElement ul_prod = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ui-id-1\"]")));
        // Assert.assertTrue(ul_prod.isDisplayed());

        Log.info("Find all elements in drop_list");

        List<WebElement> options_prod = ul_prod.findElements(By.tagName("li"));
        System.out.print(options_prod.size());

        Log.info("Get element witch contains specific world");

        for (int i = 0; i < options_prod.size(); i++) {
            //System.out.print("Element is present:" + " " + options_prod.get(i).getText() + "   ");

            if (options_prod.get(i).getText().contains("Apple iPhone 8")) {

                //System.out.print("Element is founded");
                options_prod.get(i).click();
                break;

            } else {
                System.out.print("List is empty");
            }
        }
    }



    @Test(priority = 1)
    public void getMinPriceElement() {

        driver.findElement (button_offers).click();

        Log.info("Get price list");

        List<WebElement> list_all = driver.findElements(By.cssSelector("div[data-selector='price-line']"));
        for (int i = 0; i < list_all.size(); i++) {

            Log.info("From  price list get all reviews");


            List<WebElement> list_1 = driver.findElements((By.cssSelector("a[data-tracking-id='offer-1']")));

            for (WebElement elem_1 : list_1) {

                if (Integer.parseInt(elem_1.getText().replaceAll("[^0-9]", "")) > 10) {
                    //System.out.println("List_1_size_before" + "   " + Integer.parseInt(elem_1.getText().replaceAll("[^0-9]", "")));

                    Log.info("From  price list get all producer's guarantee");

                    List<WebElement> list_2 = driver.findElements(By.cssSelector("div[class= 'cell-2 cell-md m_b-sm']"));

                    for (WebElement elem_2 : list_2) {
                        System.out.println("List_2_size_before" + "   " + Integer.parseInt(elem_2.getText().replaceAll("[^0-9]", "")));
                        if (elem_2.getText().contains(" информация отсутствует    ")) {
                            Integer.parseInt(elem_2.getText().replaceAll("[^0-9]", "1"));

                        } else if (Integer.parseInt(elem_2.getText().replaceAll("[^0-9]","")) > 6) {

                            Log.info("From  price list get all price info and compare");

                            List<WebElement> list_3 = driver.findElements(By.cssSelector("span[class='value']"));
                            int min = Integer.MIN_VALUE;
                            for (int j = 0; j < list_3.size(); i++) {
                                // System.out.println("List3"+ "   "+(Float.parseFloat(list_3.get(i).getText())) );
                                WebElement elem_3 = list_3.get(i);

                                if (Integer.parseInt(elem_3.getText().replaceAll(" ","")) > min) {

                                    elem_3.click();


                                }
                            }


                        }
                    }
                }

            }
        }
    }



@Test (priority = 2)
public  void check_product_page() {




}


    @AfterTest
    public void afterTest(){
        driver.quit();
    }


}
