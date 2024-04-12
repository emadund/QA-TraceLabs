package test;

import base.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WindowType;
import page.*;
import test.apachepoi.ReadXLSdata;

import java.util.ArrayList;
import java.util.Random;



public class FinalTestPOI extends BaseTest{
    private HomePage homePage;
    private RegisterNewAccount registerNewAccount;
    private MailinatorPage mailinatorPage;
    private ConfirmationYourEmail confirmationYourEmail;
    private NewTab newTab;
    private final String myUsername = "mark";
    private final String myTestPassword = "P@ssw0r";
    private Random ran = new Random();
    private ArrayList<ReadXLSdata> data;

    static String longText (int i) {
        String pom=new String();
        for (int j=0;j<i;j++) {
            pom+=""+(int)(Math.random()*10);
        }
        return pom;
    }

    private String[] testUsername={"h"+(ran.nextInt(8999)+1000),
            10000+ran.nextInt(89999)+"L",
            "v"+longText(28),
            ""+longText(29)+"0"};

    private String eMail = "dundaetf" + ran.nextInt(99999) + "@mailinator.com";

    private final char[] testChars = {' ', '!', '+'};

    @Before

    public void preparation() throws Exception {
        homePage = new HomePage();
        registerNewAccount = new RegisterNewAccount();
        mailinatorPage = new MailinatorPage();
        confirmationYourEmail = new ConfirmationYourEmail();
        newTab = new NewTab();
        data = ReadXLSdata.getList("tracelabs");


    }


    private void positiveUsername (String x)  // Method for different usernames testing
    {
        homePage.clickCookie();
        homePage.inputUsername(x);
        homePage.inputEmail(eMail);
        homePage.inputConfirmationMail(eMail);
        js.executeScript("window.scrollBy(0,500)");
        homePage.inputPassword(myTestPassword+"!");
        homePage.inputConfirmationPassword(myTestPassword+"!");
        homePage.checkTermsAndCondition();
        homePage.checkRobot();
        try {
            Thread.sleep(10000); //If banner images show, images clicking required manually in order to continue test!
            homePage.clickCreateAnAccount();
            Assert.assertTrue(registerNewAccount.submittingTextIsDisplayed());
            Assert.assertEquals("Your account registration has been submitted and is pending email verification."
                    , registerNewAccount.getSubmittingText()); }
        catch (Exception e) {
            System.out.println("Captcha interferes! Not possible to do registration without completing captcha images");
        }
        finally {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.navigate().to("https://www.mailinator.com/");
            mailinatorPage.enterEmail(eMail);
            mailinatorPage.clickInbox();
            mailinatorPage.confirmLink();
            confirmationYourEmail.moveToNewTab();
            Assert.assertTrue(confirmationYourEmail.isConfirmationRegistrationDisplayed());
            Assert.assertEquals("Congratulations! Your account is successfully verified." +
                            " You may proceed to log in with your user ID and password." +
                            " Please Sign In to your account and enjoy your Etherscan Services!",
                    confirmationYourEmail.confirmationRegistrationText());

        }

    }

    @Test

    public void positiveTestCase11 () throws Exception {        // Username length 5

        positiveUsername(data.get(0).getUsername());
    }
    @Test

    public void positiveTestCase12 () {  // Username length 6
        positiveUsername(data.get(1).getUsername());
    } // Username length 6
    @Test

    public void positiveTestCase13 () {  // Username length 29
        positiveUsername(data.get(2).getUsername());
    } // Username length 29
    @Test

    public void positiveTestCase14 () { positiveUsername(data.get(3).getUsername());} // Username length 30

    @Test
    public void negativeTestCase21 () throws Exception { //All fields left empty
        homePage.clickCookie();
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(1000);
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isUserNameTextDisplayed());
        Assert.assertEquals("Please enter Username.",homePage.userNameTextGetText());
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        Assert.assertTrue(homePage.isEmailConfirmartionTextDisplayed());
        Assert.assertEquals("Please re-enter your email address.",homePage.emailConfirmationTextGetText());
        Assert.assertTrue(homePage.isPasswordTextDisplayed());
        Assert.assertEquals("Please enter Password.",homePage.passwordTextGetText());
        Assert.assertTrue(homePage.isConfirmPasswordTextDisplayed());
        Assert.assertEquals("Your password must be at least 8 characters long."
                ,homePage.confirmPasswordTextGetText());
        Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
        Assert.assertEquals("Please accept our Terms and Conditions.",homePage.acceptTermsGetText());
    }
    @Test
    public void negativeTestCase22 () // invalid username inputs
    {
        homePage.clickCookie();
        homePage.inputEmail(data.get(5).getEmail());
        homePage.inputConfirmationMail(data.get(5).getEmail());
        homePage.inputPassword(data.get(5).getPassword());
        homePage.inputConfirmationPassword(data.get(5).getPassword());
        homePage.checkTermsAndCondition();
        js.executeScript("window.scrollBy(0,400)");
        homePage.checkRobot();
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isUserNameTextDisplayed());
        Assert.assertEquals("Please enter Username.",homePage.userNameTextGetText());
        for (char x:testChars) {
            homePage.inputUsername(x);
            Assert.assertTrue(homePage.isUserNameTextDisplayed());
            Assert.assertEquals("Please enter at least 5 characters.", homePage.userNameTextGetText());
            homePage.inputUsername(myUsername + x);
            Assert.assertTrue(homePage.isUserNameTextDisplayed());
            Assert.assertEquals("Only alphanumeric characters allowed.", homePage.userNameTextGetText());
        }
    }
    @Test
    public void negativeTestCase23 () throws Exception// invalid email and confirmation email inputs
    {   homePage.clickCookie();
        homePage.inputUsername(data.get(9).getUsername()+"o");
        homePage.inputPassword(data.get(9).getPassword()+"d");
        homePage.inputConfirmationPassword(data.get(9).getPassword()+"d");
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);
        homePage.checkTermsAndCondition();
        homePage.checkNewsletters();
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        homePage.inputEmail("on purpose wrong");
        homePage.inputConfirmationMail("on purpose wrong");
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        homePage.inputEmail("on purpose wrong@gmail.com");
        homePage.inputConfirmationMail("on purpose wrong@gmail.com");
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        homePage.inputEmail("on_purpose_wrong@gma!il.com");
        homePage.inputConfirmationMail("on_purpose_wrong@gma!il.com");
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        homePage.inputEmail("on purpose_wrong@gmail.com");
        homePage.inputConfirmationMail("on purpose_wrong@gmail.com");
        Assert.assertTrue(homePage.isEmailTextDisplayed());
        Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
        homePage.inputEmail(eMail);
        homePage.inputConfirmationMail("mismatched_mail@gmail.com");
        Assert.assertTrue(homePage.isEmailConfirmartionTextDisplayed());
        Assert.assertEquals("Email address does not match.", homePage.emailConfirmationTextGetText());
    }
    @Test
    public void negativeTestCase24 () throws Exception// invalid inputs for password and confirmation password
    {   homePage.clickCookie();
        homePage.inputUsername(data.get(14).getUsername()+"o");
        homePage.inputEmail(data.get(14).getEmail());
        homePage.inputConfirmationMail(data.get(14).getEmail());
        homePage.checkTermsAndCondition();
        homePage.checkNewsletters();
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isPasswordTextDisplayed());
        Assert.assertEquals("Please enter Password.",homePage.passwordTextGetText());
        homePage.inputPassword(data.get(18).getPassword());
        homePage.inputConfirmationPassword(data.get(18).getPassword());
        Assert.assertTrue(homePage.isPasswordTextDisplayed());
        Assert.assertEquals("Your password must be at least 8 characters long.",homePage.passwordTextGetText());
        homePage.inputPassword(data.get(18).getPassword()+"d");
        homePage.inputConfirmationPassword("mismatched on purpose");
        Assert.assertTrue(homePage.isConfirmPasswordTextDisplayed());
        Assert.assertEquals("Password does not match, please check again.",homePage.confirmPasswordTextGetText());
    }
    @Test
    public void negativeTestCase25 () throws Exception // invalid checking mandatory checkboxes
    {   homePage.clickCookie();
        homePage.inputUsername(data.get(18).getUsername()+"o");
        homePage.inputEmail(data.get(18).getEmail());
        homePage.inputConfirmationMail(data.get(18).getEmail());
        homePage.inputPassword(data.get(18).getPassword()+"!");
        homePage.inputConfirmationPassword(data.get(18).getPassword()+"!");
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000);
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
        Assert.assertEquals("Please accept our Terms and Conditions.",homePage.acceptTermsGetText());
        homePage.checkNewsletters();
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
        Assert.assertEquals("Please accept our Terms and Conditions.",homePage.acceptTermsGetText());
        homePage.checkTermsAndCondition();
        homePage.clickCreateAnAccount();
        Assert.assertTrue(homePage.errorIsDisplayed());
        Assert.assertEquals("Error! Invalid captcha response.\n" +
                "Please Try Again", homePage.errorText());
    }
    @Test
    public void inspectButtonsForSameTab () // checking several other elements opening new page in the same tab
    {   homePage.clickCookie();
        homePage.clickOnGasIcon();
        Assert.assertTrue(homePage.isGasTrackerDisplayed());
        Assert.assertEquals("Ethereum Gas Tracker ⛽",homePage.gasTrackerText());
        driver.navigate().back();
        homePage.clickOnTransaction();
        Assert.assertTrue(homePage.isTransactionDisplayed());
        Assert.assertEquals("Transactions",homePage.getTransactionText());

    }
    @Test
    public void inspectButtonsForNewTab () throws Exception // checking several other elements opening new page in the new tab
    {
        homePage.clickCookie();
        homePage.clickOnApiDocumentation();
        newTab.moveToNewTab();
        Assert.assertTrue(newTab.isIntroductionDisplayed());
        Assert.assertEquals("Introduction",newTab.introductionGetText());
        driver.close();
        newTab.moveToPreviousTab();
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,1500)");
        homePage.clickReddit();
        newTab.moveToNewTab();
        Assert.assertTrue(newTab.redditDisplayed());
        newTab.moveToPreviousTab();
        driver.close();
        newTab.moveToPreviousTab();
    }
}
