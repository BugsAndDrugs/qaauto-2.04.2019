import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {
    @Test
    public void successfulLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("bugz.and.drugz@gmail.com");
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("149600");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileMenuItem.isDisplayed(), "Home page was not loaded");
        driver.findElement(By.xpath("//li[@id='profile-nav-item']")).click();
        WebElement profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        Assert.assertEquals(profileUserName.getText(), "Bugz Drugz", "Wrong User Name is displayed");
        driver.quit();
    }
    @Test
    public void invalidCredentialsLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("bugz.and.drugz@gmail.com");
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("14960");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME", "Wrong Redirect");
        WebElement errorPasswordMessage = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        Assert.assertEquals(errorPasswordMessage.getText(), "Hmm, that's not the right password. Please try again or request a new one.", "Wrong error message for invalid password");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://www.linkedin.com");
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("bugzanddrugz@gmail.com");
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("149600");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        /* driver.findElement(By.xpath("//a[@data-tracking-control-name='guest_homepage-basic_nav-header-signin']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("bugzanddrugz@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("149600");
        driver.findElement(By.xpath("//input[@id='submit']")).click(); */
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME", "Wrong Redirect");
        WebElement errorLoginMessage = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        Assert.assertEquals(errorLoginMessage.getText(), "Hmm, we don't recognize that email. Please try again.", "Wrong error message for invalid login");
        driver.quit();

    }
    @Test
    public void emptyInputsLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("bugz.and.drugz@gmail.com");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Password, Wrong URL");
        driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("149600");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Login, Wrong URL");
        driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Credentials, Wrong URL");
        driver.quit();
    }
}