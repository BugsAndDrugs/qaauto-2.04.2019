import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "bugz.and.drugz@gmail.com", "149600" },
                { "bugz.AND.drugz@gmail.com", "149600" },
        };
    }
    @DataProvider
    public Object[][] invalidLoginDataProvider() {
        return new Object[][]{
                { "bugz.anddrugz@gmail.com", "149600" },
                { "bugzand.drugz@gmail.com", "149600" },
                { "bugzanddrugz@gmail.com", "149600" },
        };
    }

    @DataProvider
    public Object[][] invalidPasswordDataProvider() {
        return new Object[][]{
                { "bugz.and.drugz@gmail.com", "14960" },
                { "bugz.and.drugz@gmail.com", "!@#$%^&*()_+" },
        };
    }

    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                { "", "" },
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");
        driver.quit();
    }

    @Test (dataProvider = "invalidLoginDataProvider")
    public void invalidLoginTest(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
        WrongCredentialsPage wrongCredentialsPage = new WrongCredentialsPage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME", "Wrong Redirect");
        Assert.assertEquals(wrongCredentialsPage.getErrorLoginMessage(),"Hmm, we don't recognize that email. Please try again.","Wrong error message for invalid login");
        driver.quit();
    }

    @Test (dataProvider = "invalidPasswordDataProvider")
    public void invalidPasswordTest(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
        WrongCredentialsPage wrongCredentialsPage = new WrongCredentialsPage(driver);
        Assert.assertEquals(wrongCredentialsPage.getErrorPasswordMessage(),"Hmm, that's not the right password. Please try again or request a new one.","Wrong error message for invalid password");
        driver.quit();
    }

    @Test (dataProvider = "emptyDataProvider")
    public void negativeLoginWithEmptyFields(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
        driver.quit();
    }
    /*
    @Test (dataProvider = "validDataProvider")
    public void classWork(String userEmail, String userPassword) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed(), "Home page is not loaded");
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");
        driver.quit();
    }
    */
}