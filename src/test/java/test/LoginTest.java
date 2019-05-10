package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest {

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
    public Object[][] negativeLoginWithInvalidData() {
        return new Object[][]{
                { "bugz.anddrugz@gmail.com", "149600", "Hmm, we don't recognize that email. Please try again.", "" },
                { "bugz.and.drugz@gmail.com", "14960", "", "Hmm, that's not the right password. Please try again or request a new one." },
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
        // Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up ");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Bugz Drugz", "Wrong User Name is displayed");
    }

    @Test (dataProvider = "invalidLoginDataProvider")
    public void invalidLoginTest(String userEmail, String userPassword) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertEquals(loginSubmitPage.getErrorLoginMessage(),"Hmm, we don't recognize that email. Please try again.","Wrong error message for invalid login");
    }

    @Test (dataProvider = "invalidPasswordDataProvider")
    public void invalidPasswordTest(String userEmail, String userPassword) {
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertEquals(loginSubmitPage.getErrorPasswordMessage(),"Hmm, that's not the right password. Please try again or request a new one.","Wrong error message for invalid password");
    }

    @Test (dataProvider = "emptyDataProvider")
    public void negativeLoginWithEmptyFields(String userEmail, String userPassword) {
        loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
    }

    @Test (dataProvider = "negativeLoginWithInvalidData")
    public void negativeLoginWithInvalidData(String userEmail, String userPassword, String errorLoginMessage, String errorPasswordMessage) {
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded");
        LoginSubmitPage wrongCredentialsPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(wrongCredentialsPage.isPageLoaded(),"page.LoginSubmitPage is loaded" );
        Assert.assertEquals(wrongCredentialsPage.getErrorLoginMessage(),errorLoginMessage,"Wrong error message for invalid login");
        Assert.assertEquals(wrongCredentialsPage.getErrorPasswordMessage(),errorPasswordMessage,"Wrong error message for invalid password");
    }
}

