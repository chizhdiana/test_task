package Suit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by diana.chizh on 13.12.2017.
 */
public class FindPage extends Main{

    public FindPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchbox = By.id("searchbox");  // drop_down
    By drop_list =  By.xpath("//*[@id=\"ui-id-1\"]");// drop_down element

public void getUrl(String url){
    driver.get(url);

}



    public void  searchBox(String product){// send product name
       this.driver.findElement(searchbox).sendKeys(product);

    }


    public void dropDownSelect(String find_prod) {// get options from product

        List<WebElement> options_prod = driver.findElement(drop_list).findElements(By.tagName("li"));
        for (int i = 0; i < options_prod.size(); i++) {

            if (options_prod.get(i).getText().contains(find_prod)) {
                System.out.print(options_prod.get(i).getText());

                options_prod.get(i).click();
                break;

            } else {
                System.out.println("List is empty");
            }
        }

    }


}
