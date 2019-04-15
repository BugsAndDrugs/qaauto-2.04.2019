import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void successfulLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        WebElement login = driver.findElement(By.xpath("//input[@name='session_key']"));
        login.sendKeys("bugz.and.drugz@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@name='session_password']"));
        password.sendKeys("149600");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        Assert.assertEquals(driver.getTitle(), "LinkedIn");
        driver.quit();
    }
}
