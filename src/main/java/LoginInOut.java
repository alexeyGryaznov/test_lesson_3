import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginInOut {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.dns-shop.ru/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // опеределим веб элементы
        WebElement AutorezationButton=driver.findElement(By.xpath("//button[@data-role='login-button']"));
        WebDriverWait waitForElement=new WebDriverWait(driver,3);
        String Nik="Пришелец-CY75848";
        AutorezationButton.sendKeys(Keys.ENTER);
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Войти с паролем')]")));
        driver.findElement(By.xpath("//div[@class='block-other-login-methods__password-caption']")).click();
        driver.findElement(By.xpath("//input[@autocomplete='username']")).sendKeys("lightsteach@yandex.ru");
        driver.findElement(By.xpath("//input[@autocomplete='current-password']")).sendKeys("test123456");
        driver.findElement(By.xpath("//div[@class='form-entry-with-password__main-button']//*[contains(text(),'Войти')]")).click();
        driver.findElement(By.xpath("//div[@class='header-profile__level']")).click();
        WebElement NickName= driver.findElement(By.xpath("//span[@class='header-profile__username'][contains(text(),'Пришелец-CY75848')]"));
        if(Nik.equals(NickName.getText()))System.out.println("Пользователь с Ником: " + NickName.getText() + " залогинен");
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/logout/']")));
        driver.findElement(By.xpath("//a[@href='/logout/']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.quit();
    }
}
