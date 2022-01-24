import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ChangeSity {
    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver= WebDriverManager.chromedriver().create();
        webDriver.get("https://www.dns-shop.ru/");
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        String location="Москва";
        WebElement LocalCity= webDriver.findElement(By.xpath("//ul[@class='header-top-menu__common-list']//div[@class='city-select w-choose-city-widget']//p"));
        System.out.println("Город по умолчанию: "+LocalCity.getText());
        webDriver.findElement(By.xpath("//a[@class='w-choose-city-widget pseudo-link pull-right']")).click();

        WebDriverWait waitForElement=new WebDriverWait(webDriver,3);
        By searchForm= By.xpath("//div[@class='base-modal select-city-modal base-modal_full-on-mobile']//div[contains(text(),'Ваш город')]");
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(searchForm));
        webDriver.findElement(By.xpath("//input[@data-role='search-city']")).sendKeys("Москва");
        webDriver.findElement(By.xpath("//input[@data-role='search-city']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        By newLocation= By.xpath("/html/body/header/div[3]/div/ul[1]/li[1]/div/div/div/p");// такой локатор потому что см. ниже
        System.out.println(webDriver.findElement(newLocation).getText());
        //System.out.println(LocalCity.getText());  При повторном считывании вэб элемента выпадает ошибка
        if(location.equals(webDriver.findElement(newLocation).getText())) System.out.println("Город сменен");
        Thread.sleep(3000);
    }
}
