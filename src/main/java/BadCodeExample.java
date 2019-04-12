import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
//        System.out.println("Hello, World!");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\plk\\Downloads\\New folder\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");


//        Homework 02.04.2019

//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Selenium");
//        element.submit();

//        WebElement searchField = driver.findElement(By.name("q"));

        WebElement searchField = driver.findElement(By.xpath("//input[@name='q']"));
        searchField.sendKeys("Selenium"+Keys.ENTER);
//        searchField.sendKeys(Keys.ENTER);
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results count: " + searchResults.size());
        String searchWord = "Selenium";

//        Homework 09.04.2019

       /* for (int i=0; i<searchResults.size(); i++)
        {
            System.out.println(">>>");
            System.out.println("Search block: " + searchResults.get(i).getText());
            System.out.println("<<<");
            if (searchResults.get(i).getText().contains(searchWord)) {
                System.out.println("searchTerm found: " + driver.getTitle());
            } else {
                System.out.println("searchTerm not found: " + driver.getTitle());
            }

        } */

       /* for (WebElement block: searchResults) {
            System.out.println(">>>\nSearch block: " + block.getText()+ "\n<<<");
            if (block.getText().contains(searchWord)) {
                System.out.println("(: searchTerm found");
                // System.out.println(driver.getTitle());
                // System.out.println(driver.getCurrentUrl());
            } else {
                System.out.println("): searchTerm not found");
            }
        } */

        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            System.out.println(searchResultText);
            if (searchResultText.toLowerCase().contains(searchWord.toLowerCase())) {
                System.out.println("Search Term "+searchWord+" found in: \n"+searchResultText);
            } else {
                System.out.println("Search Term "+searchWord+" not found in: \n"+searchResultText);
            }
        }
        driver.quit();
    }
}
