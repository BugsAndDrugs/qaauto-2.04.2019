import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LoginPage {

    WebDriver driver;
    WebElement userEmailField;
    WebElement userPasswordField;
    WebElement submitInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        submitInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
    }

    public HomePage login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        submitInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HomePage(driver);
    }

    public LoginPage loginToLogin(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        submitInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LoginPage(driver);
    }

    public WrongCredentialsPage loginToLoginSubmit(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        submitInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new WrongCredentialsPage(driver);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && driver.getTitle().contains("LinkedIn: Log In or Sign Up")
                && submitInButton.isDisplayed();
    }
}
