import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ByProduct {
    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver=WebDriverManager.chromedriver().create();
        webDriver.get("https://www.dns-shop.ru/");
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(1500,800));
        String Search="//div[@class='header-menu-wrapper']//input[@placeholder='Поиск по сайту']";
        String UpToPrice="//input[@type='number'][contains(@placeholder, 'до')]";
        webDriver.findElement(By.xpath(Search)).sendKeys("утюг");
        webDriver.findElement(By.xpath(Search)).sendKeys(Keys.ENTER);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-role='login-button']")));
        WebElement scroll= webDriver.findElement(By.xpath("//button[@data-role='login-button']"));
        scroll.sendKeys(Keys.PAGE_DOWN);
        webDriver.findElement(By.xpath(UpToPrice)).sendKeys("1000");
        webDriver.findElement(By.xpath(UpToPrice)).sendKeys(Keys.ENTER);
        Thread.sleep(500);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Купить')]")));
        webDriver.findElement(By.xpath("//button[contains(text(),'Купить')]")).sendKeys(Keys.ENTER);
        webDriver.findElement(By.xpath("//a[@href='/order/begin/']")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[@class='cart-items__wrapper']//preceding::button[@class='menu-control-button remove-button']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }
}
