import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WrongCredentialsPage {
    private WebDriver driver;
    private WebElement errorLoginMessage;
    private WebElement errorPasswordMessage;

    public WrongCredentialsPage(WebDriver driver) {
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
}
