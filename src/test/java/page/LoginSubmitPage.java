package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage extends BasePage {

    private WebElement errorLoginMessage;
    private WebElement errorPasswordMessage;

    public LoginSubmitPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getErrorLoginMessage() {
        errorLoginMessage = driver.findElement(By.xpath("//div[@id='error-for-username']"));
        return errorLoginMessage.getText();
    }
    public String getErrorPasswordMessage() {
        errorPasswordMessage = driver.findElement(By.xpath("//div[@id='error-for-password']"));
        return errorPasswordMessage.getText();
    }
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME");
    }
}
