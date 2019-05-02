import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class HomePage {
    private WebDriver driver;
    private WebElement profileMenuItem;
    private WebElement profileUserName;
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        searchField = driver.findElement(By.xpath("//input[@role='combobox']"));
    }

    public boolean isProfileMenuItemDisplayed() {
        return profileMenuItem.isDisplayed();
    }

    public void clickOnProfileMenuItem() {
        profileMenuItem.click();
    }
    public String getProfileUserNameText () {
        profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        return profileUserName.getText();
    }

    public void search(String searchValue) {
        searchField.sendKeys(searchValue+ Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isHomePageLoaded() {
        return driver.getCurrentUrl().contains("https://www.linkedin.com/feed")
                && driver.getTitle().contains("LinkedIn")
                && profileMenuItem.isDisplayed();
    }
}
