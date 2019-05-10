package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileMenuItem;

    @FindBy(xpath = "//ul[@id='nav-settings__dropdown-options']//h3")
    private WebElement profileUserName;

    @FindBy(xpath = "//form[@id='extended-nav-search']//input")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isProfileMenuItemDisplayed() {
        return profileMenuItem.isDisplayed();
    }

    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }

    public String getProfileUserNameText() {
        return profileUserName.getText();
    }

    public SearchResultsPage search(String searchValue) {
        searchField.sendKeys(searchValue+ Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultsPage(driver);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("https://www.linkedin.com/feed")
                && driver.getTitle().contains("LinkedIn")
                && profileMenuItem.isDisplayed();
    }
}
