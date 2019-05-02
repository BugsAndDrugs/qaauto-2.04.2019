import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    private WebDriver driver;
    public WebElement searchResult;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchPageLoaded() {
        return driver.getCurrentUrl().contains("https://www.linkedin.com/search");
    }

    List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//div[@class='search-result__info pt3 pb4 ph0']"));
    }
}
