package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    public abstract boolean isPageLoaded();

}

// New test for resetPasswordTest
// lot of new page classes
// get manually url from email > redirect to new page for test
