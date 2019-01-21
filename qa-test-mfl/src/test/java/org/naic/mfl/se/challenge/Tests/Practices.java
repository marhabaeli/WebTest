package org.naic.mfl.se.challenge.Tests;

import fabricator.Calendar;
import fabricator.Contact;
import fabricator.Fabricator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Practices {
    public static void main(String[] args){
       /* System.setProperty("webdriver.chrome.driver", "/Users/marhabaeli/WebDriver/chromedriver");

        ChromeOptions options=new ChromeOptions();
      //  options.addArguments("windows-size=1400,800");
        options.addArguments("headless");

        WebDriver driver=new ChromeDriver(options);

        //dynamic wait
        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      //  driver.manage().window().maximize();

        driver.get("http://google.com");

       driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("java");
       List<WebElement> list=driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[contains(@class,'sbl')]"));

       for(int i=0; i<list.size();i++){
           System.out.println(list.get(i).getText());
       }*/

       dataFactory();
    }

    static void dataFactory() {

       Contact contact=Fabricator.contact();
        System.out.println(contact.firstName());
        System.out.println(contact.lastName());
        System.out.println(contact.address());

        Calendar calendar=Fabricator.calendar();
        System.out.println(calendar.day());
        System.out.println(calendar.month(true));
        System.out.println(calendar.year());
    }
}
