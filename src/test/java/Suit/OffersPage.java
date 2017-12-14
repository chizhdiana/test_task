package Suit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by diana.chizh on 13.12.2017.
 */
public class OffersPage extends Main {


    By all_offer = By.cssSelector("div[data-selector='price-line']"); //  container offers
    By button = By.xpath("//*[@id=\"page-product\"]/div[3]/div[1]/ul/li[2]/span");//  button all offers




    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }


    public void button_Offer(WebDriver driver) { // clic button all offers
        driver.findElement(button).click();
        System.out.println("Button all offers is fined");

    }

    public List<WebElement> findListOffer() {// find container with all offers

        return driver.findElements(all_offer);



    }
    public void listSize(){
        System.out.println("List with offer is fined" + findListOffer().size());
    }


   public List<WebElement> List_offers(WebDriver driver) {// return list offers sorted by price

       List<WebElement> list_3= new ArrayList<WebElement>() ;
       for (WebElement elem : findListOffer()) {
           List<WebElement> list_1 = elem.findElements((By.cssSelector("a[data-tracking-id='offer-1']")));
           for (WebElement v_1 : list_1) {
               if (v_1.getText().equalsIgnoreCase("")) {
                   Integer.parseInt(v_1.getText().replaceAll("[^0-9]", "1"));
               } else if ((Integer.parseInt(v_1.getText().replaceAll("[^0-9]", "")) > 10)) {

                   List<WebElement> list_2 = elem.findElements(By.cssSelector("div[class= 'cell-2 cell-md m_b-sm']"));
                   for (WebElement v_2 : list_2) {
                       if (v_2.getText().replaceAll("[^0-9]", "").equalsIgnoreCase("")) {
                           v_2.getText().replaceAll("[^0-9]", "1");


                       } else if (v_2.getText().equalsIgnoreCase("от производителя")) {
                           v_2.getText().replace("от производителя", "1");

                       } else if (v_2.getText().equalsIgnoreCase("информация отсутствует")) {

                           Integer.parseInt(v_2.getText().replace("информация отсутствует", "2"));


                       } else if ((Integer.parseInt(v_2.getText().replaceAll("[^0-9]", "")) > 6)) {

                           list_3.addAll(elem.findElements(By.cssSelector("a[class='price-lg'] span[class='value']")));



                       }
                   }
               }

           }


       }System.out.println("List_3 size"+ " "+ list_3.size());
       return list_3;

   }

   public void selectMin(WebDriver driver){ // select min price
        WebElement minval = Get_min(List_offers(driver));
        System.out.println("Min_value is"+ " "+ minval.getText());
        minval.click();
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

   }


    public  WebElement Get_min(List<WebElement> lst){// get element from list with min price
        WebElement min_elem = lst.get(0);
        for(int i= 1; i<lst.size();i++){
            if (Integer.parseInt(lst.get(i).getText().replaceAll("[\\s]+", "")) < Integer.parseInt(min_elem.getText().replaceAll("[\\s]+", ""))) {
                min_elem = lst.get(i);

                System.out.println("Min" + "   " + min_elem.getText());

            } else {
                System.out.println("Not found min" + "   " + min_elem.getText());
            }

        }
        return min_elem;

    }





}
