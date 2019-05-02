
// Review/Refactor tests
// Write a new test: LoginWithValidCredentials > Search "HR" > 10 results > Each block contains HR

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

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
public class SearchTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] SearchDataProvider() {
        return new Object[][]{
                { "bugz.and.drugz@gmail.com", "149600", "HR" }
        };
    }

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

    @Test(dataProvider = "SearchDataProvider")
    public void basicSearchTest(String userEmail, String userPassword, String searchValue) {
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
        loginPage.login(userEmail, userPassword);
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(),"Home page is not loaded");
        homePage.search(searchValue);
        searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.isSearchPageLoaded(),"Search page is not loaded");
        List<WebElement> searchResults = searchPage.getSearchResults();
        Assert.assertEquals(searchResults.size(),8,"Wrong results count");
        for (WebElement searchResult : searchResults) {
            // String searchResultText = searchResult.getText();
            // System.out.println(searchResultText);
            Assert.assertTrue(searchResult.getText().contains(searchValue));
        }

    }

}
