import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "bugz.and.drugz@gmail.com", "149600" },
                { "bugz.AND.drugz@gmail.com", "149600" },
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        // login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        //driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("bugz.and.drugz@gmail.com");
        //driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("149600");
        //driver.findElement(By.xpath("//input[@id='login-submit']")).click();
        // try {
        //    sleep(3000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");

        //WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        //Assert.assertTrue(profileMenuItem.isDisplayed(), "Home page was not loaded");
        //driver.findElement(By.xpath("//li[@id='profile-nav-item']")).click();
        //WebElement profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        //Assert.assertEquals(profileUserName.getText(), "Bugz Drugz", "Wrong User Name is displayed");

        driver.quit();
    }

    @Test
    public void emptyInputsLoginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        /* loginPage.login("bugz.and.drugz@gmail.com", "");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Password, Wrong URL"); */
        loginPage.login("", "149600");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Login, Wrong URL");
        loginPage.login("", "");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Empty Credentials, Wrong URL");
        driver.quit();
    }

    @Test
    public void negativeLoginWithEmptyFields() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
        driver.quit();
    }

    @Test
    public void classWork() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("bugz.and.drugz@gmail.com", "149600");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed(), "Home page is not loaded");
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");
        driver.quit();
    }
}