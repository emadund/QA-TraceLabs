package page;

import base.BaseTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BaseTest {

    @FindBy (id="ContentPlaceHolder1_txtUserName")
    WebElement username;

    @FindBy (id="ContentPlaceHolder1_txtEmail")
    WebElement email;

    @FindBy (id="ContentPlaceHolder1_txtConfirmEmail")
    WebElement mailConfirmation;

    @FindBy (css="[name=\"ctl00$ContentPlaceHolder1$txtPassword\"]")
    WebElement password;

    @FindBy (css="#ContentPlaceHolder1_txtPassword2")
    WebElement passwordConfirmation;

    @FindBy (css="[name=\"ctl00$ContentPlaceHolder1$MyCheckBox\"]")
    WebElement termsConditions;

    @FindBy (id="ContentPlaceHolder1_SubscribeNewsletter")
    WebElement newsletter;

    @FindBy (css="[title=\"reCAPTCHA\"]")
    WebElement iframe;

    @FindBy (xpath = "//*[contains(text(),\"I'm not a robot\")] ")
    WebElement robot;

    @FindBy (id="ContentPlaceHolder1_btnRegister")
    WebElement createAccount;

    @FindBy (id="btnCookie")
    WebElement cookie;

    @FindBy (css="#ContentPlaceHolder1_txtUserName-error")
    WebElement userNameText;

    @FindBy (css="#ContentPlaceHolder1_txtEmail-error")
    WebElement emailText;

    @FindBy (css="#ContentPlaceHolder1_txtConfirmEmail-error")
    WebElement confirmEmailText;

    @FindBy (css="#ContentPlaceHolder1_txtPassword-error")
    WebElement passwordText;

    @FindBy (css="#ContentPlaceHolder1_txtPassword2-error")
    WebElement confirmPasswordText;

    @FindBy (xpath = "//*[contains(text(),\"Please accept our Terms and Conditions.\")]")
    WebElement acceptTermsText;

    @FindBy (id="LI6")
    WebElement apiDocumentation;

    @FindBy (css = ".alert.alert-danger")
    WebElement error;

    @FindBy (css=".gasPricePlaceHolder")
    WebElement gasIcon;
    @FindBy (xpath = "(//*[contains(text(),\"Ethereum Gas Tracker\")])[2]")
    WebElement gasTracker;

    @FindBy (id="LI_blockchain")
    WebElement blockchain;

    @FindBy (id="LI_resources")
    WebElement resources;

    @FindBy (id="li_developers")
    WebElement developers;
    @FindBy (css=".fab.fa-reddit-alien")
    WebElement reddit;

    @FindBy (id="LI12")
    WebElement transaction;

    @FindBy (css=".h5.mb-0")
    WebElement transactionText;



    public HomePage () {
        PageFactory.initElements(driver, this);
    }

    public void clickCookie () {
        if (cookie.isDisplayed()) {
        clickOnElement(cookie); }
    }
    public void inputUsername (char x) {
        wdWait.until(ExpectedConditions.visibilityOf(username));
        username.clear();
        username.sendKeys(String.valueOf(x));
            }
    public void inputUsername (String x) {
        fillTextElement(username,x);
    }

    public void inputEmail (String x) {
        fillTextElement(email,x);
    }
    public void inputConfirmationMail (String x) {
        fillTextElement(mailConfirmation,x);
    }
    public void inputPassword (String x) {
        fillTextElement(password,x);
    }
    public void inputConfirmationPassword (String x) {
        fillTextElement(passwordConfirmation,x);
    }

    public void checkTermsAndCondition () {
        wdWait.until(ExpectedConditions.visibilityOf(termsConditions));
        if (!termsConditions.isSelected()) termsConditions.click();
    }
    public void checkNewsletters () {
        wdWait.until(ExpectedConditions.elementToBeClickable(newsletter));
        if (!newsletter.isSelected()) newsletter.click();
    }
    public void checkRobot () {
        wdWait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        clickOnElement(robot);
        driver.switchTo().defaultContent();
    }

   public void clickCreateAnAccount () {
        clickOnElement(createAccount);
    }
    public boolean isUserNameTextDisplayed () {
        return isElementDisplayed(userNameText);
    }
    public String userNameTextGetText () {
        return getTextFromElement(userNameText);
    }
    public boolean isEmailTextDisplayed () {
        return isElementDisplayed(emailText);
    }
    public String emailTextGetText () {
        return getTextFromElement(emailText);

    }
    public boolean isEmailConfirmartionTextDisplayed () {
        return isElementDisplayed(confirmEmailText);
    }
    public String emailConfirmationTextGetText () {
        return getTextFromElement(confirmEmailText);
    }
    public boolean isPasswordTextDisplayed () {
        return isElementDisplayed(passwordText);
    }
    public String passwordTextGetText () {
        return getTextFromElement(passwordText);
    }
    public boolean isConfirmPasswordTextDisplayed () {
        return isElementDisplayed(confirmPasswordText);
    }
    public String confirmPasswordTextGetText () {
        return getTextFromElement(confirmPasswordText);
    }
    public boolean isAcceptTermsTextDisplayed () {
        return isElementDisplayed(acceptTermsText);
    }
    public String acceptTermsGetText () {
        return getTextFromElement(acceptTermsText);
    }
    public void clickOnGasIcon () {
        clickOnElement(gasIcon);
    }
    public boolean isGasTrackerDisplayed () {
        return isElementDisplayed(gasTracker);
    }
    public String gasTrackerText () {
        return getTextFromElement(gasTracker);
    }
    public void clickOnTransaction () {
        clickOnDropDownItem(blockchain, transaction);
    }
    public boolean isTransactionDisplayed() {
        return isElementDisplayed(transactionText);
    }
    public String getTransactionText() {
        return getTextFromElement(transactionText);
    }
    public boolean errorIsDisplayed () {
        return isElementDisplayed(error);
    }
    public String errorText () {
       return getTextFromElement(error);
    }

    public void clickOnApiDocumentation() {
        clickOnDropDownItem(developers, apiDocumentation);
        }
    public void clickReddit () {
        clickOnElement(reddit);
    }
}
