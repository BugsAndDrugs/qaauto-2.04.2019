import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod

    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod

    public void afterMethod() {
        driver.quit();
    }

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
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");
    }

    @Test (dataProvider = "invalidLoginDataProvider")
    public void invalidLoginTest(String userEmail, String userPassword) {
        WrongCredentialsPage wrongCredentialsPage = loginPage.loginToLoginSubmit(userEmail, userPassword);
        Assert.assertEquals(wrongCredentialsPage.getErrorLoginMessage(),"Hmm, we don't recognize that email. Please try again.","Wrong error message for invalid login");
    }

    @Test (dataProvider = "invalidPasswordDataProvider")
    public void invalidPasswordTest(String userEmail, String userPassword) {
        WrongCredentialsPage wrongCredentialsPage = loginPage.loginToLoginSubmit(userEmail, userPassword);
        Assert.assertEquals(wrongCredentialsPage.getErrorPasswordMessage(),"Hmm, that's not the right password. Please try again or request a new one.","Wrong error message for invalid password");
    }

    @Test (dataProvider = "emptyDataProvider")
    public void negativeLoginWithEmptyFields(String userEmail, String userPassword) {
        loginPage.loginToLogin(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
    }

    /* Classwork 23.04.2019
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

    // Classwork 26.04.2019
    @DataProvider
    public Object[][] negativeLoginWithInvalidData() {
        return new Object[][]{
                { "bugz.anddrugz@gmail.com", "149600", "Hmm, we don't recognize that email. Please try again.", "" },
                { "bugz.and.drugz@gmail.com", "14960", "", "Hmm, that's not the right password. Please try again or request a new one." },
        };
    }

    @Test (dataProvider = "negativeLoginWithInvalidData")
    public void negativeLoginWithInvalidData(String userEmail, String userPassword, String errorLoginMessage, String errorPasswordMessage) {
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
        WrongCredentialsPage wrongCredentialsPage = loginPage.loginToLoginSubmit(userEmail, userPassword);
        Assert.assertTrue(wrongCredentialsPage.isPageLoaded(),"WrongCredentialsPage is loaded" );
        Assert.assertEquals(wrongCredentialsPage.getErrorLoginMessage(),errorLoginMessage,"Wrong error message for invalid login");
        Assert.assertEquals(wrongCredentialsPage.getErrorPasswordMessage(),errorPasswordMessage,"Wrong error message for invalid password");
    }
}
// Review/Refactor tests
// Write a new test: LoginWithValidCredentials > Search "HR" > 10 results > Each block contains HR

/*
1:58 PM
Home Task #7:
Implement basicSearchTest() Scenario.
- Open new Browser
- Navigate to http://www.linkedin.com
- Verify that Landing page is loaded
- Login with valid credentials
- Verify that Home page is loaded
- Search for "HR" search term
- Verify that Search page is loaded
- Verify that numbers of search results is 10
- Verify that each search result contains search term

LinkedIn: Log In or Sign Up
http://www.linkedin.com
 */
