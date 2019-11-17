package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);

        createUserAndLogout(driver);

        sleep(3);
        
        driver.quit();
    }

    private static void loginSuccessfully(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
    }

    private static void loginWithWrongPassword(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
        driver.findElement(By.id("error"));
    }

    private static void createUserSuccessfully(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("123456abc");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("123456abc");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();
    }

    private static void createUserAndLogout(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("123456abc");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("123456abc");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
