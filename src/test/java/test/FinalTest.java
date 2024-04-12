package test;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WindowType;
import page.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


public class FinalTest extends BaseTest {
    private HomePage homePage;
    private RegisterNewAccount registerNewAccount;
    private MailinatorPage mailinatorPage;
    private ConfirmationYourEmail confirmationYourEmail;
    private NewTab newTab;
    private final String myUsername = "mark";
    private final String myTestPassword = "P@ssw0r";
    private Random ran = new Random();
    private Date d1 = new Date();
    String d2 = d1.toString().replaceAll(":", "_");

    static String longText(int i) {
        String pom = new String();
        for (int j = 0; j < i; j++) {
            pom += "" + (int) (Math.random() * 10);
        }
        return pom;
    }

    private String[] testUsername = {"h" + (ran.nextInt(8999) + 1000),
            10000 + ran.nextInt(89999) + "L",
            "v" + longText(28),
            "" + longText(29) + "0"};

    private String eMail = "dundaetf" + ran.nextInt(99999) + "@mailinator.com";

    private final char[] testChars = {' ', '!', '+'};


    @Before

    public void preparation() {
        homePage = new HomePage();
        registerNewAccount = new RegisterNewAccount();
        mailinatorPage = new MailinatorPage();
        confirmationYourEmail = new ConfirmationYourEmail();
        newTab = new NewTab();
    }

    private void positiveUsername(String x) throws IOException  // Method for different usernames testing
    {
        homePage.clickCookie();
        homePage.inputUsername(x);
        homePage.inputEmail(eMail);
        homePage.inputConfirmationMail(eMail);
        js.executeScript("window.scrollBy(0,500)");
        homePage.inputPassword(myTestPassword + "!");
        homePage.inputConfirmationPassword(myTestPassword + "!");
        homePage.checkTermsAndCondition();
        homePage.checkRobot();
        try {
            Thread.sleep(10000); //If banner images show, images clicking required manually in order to continue test!
            homePage.clickCreateAnAccount();
            Assert.assertTrue(registerNewAccount.submittingTextIsDisplayed());
            Assert.assertEquals("Your account registration has been submitted and is pending email verification."
                    , registerNewAccount.getSubmittingText());
        } catch (Exception e) {
            System.out.println("Captcha interferes! Not possible to do registration without completing captcha images");
            cacthingBugs();
        } finally {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.navigate().to("https://www.mailinator.com/");
            mailinatorPage.enterEmail(eMail);
            mailinatorPage.clickInbox();
            mailinatorPage.confirmLink();
            confirmationYourEmail.moveToNewTab();
            try {
                Assert.assertTrue(confirmationYourEmail.isConfirmationRegistrationDisplayed());
                Assert.assertEquals("Congratulations! Your account is successfully verified." +
                                " You may proceed to log in with your user ID and password." +
                                " Please Sign In to your account and enjoy your Etherscan Services!",
                        confirmationYourEmail.confirmationRegistrationText());
            } catch (Exception e) {
                System.out.println("Email didn't get confirmation link for receiving");
                cacthingBugs();
            }

        }

    }

    @Test

    public void positiveTestCase11() throws IOException {  // Username length 5
        positiveUsername(testUsername[0]);
    }

    @Test

    public void positiveTestCase12() throws IOException {  // Username length 6
        positiveUsername(testUsername[1]);
    } // Username length 6

    @Test

    public void positiveTestCase13() throws IOException {  // Username length 29
        positiveUsername(testUsername[2]);
    } // Username length 29

    @Test

    public void positiveTestCase14() throws IOException {
        positiveUsername(testUsername[3]);
    } // Username length 30

    @Test
    public void negativeTestCase21() throws Exception { //All fields left empty
        homePage.clickCookie();
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(1000);
        homePage.clickCreateAnAccount();

        try {
            Assert.assertTrue(homePage.isUserNameTextDisplayed());
            Assert.assertEquals("Please enter Username.", homePage.userNameTextGetText());
        } catch (Exception e) {
            System.out.println("Error for username not showing properly");
            cacthingBugs();
        } finally {
            try {
                Assert.assertTrue(homePage.isEmailTextDisplayed());
                Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
            } catch (Exception e) {
                System.out.println("Error for email not showing properly");
                cacthingBugs();
            }
            try {
                Assert.assertTrue(homePage.isEmailConfirmartionTextDisplayed());
                Assert.assertEquals("Please re-enter your email address.", homePage.emailConfirmationTextGetText());
            }
            catch (Exception e) {
                System.out.println("Error for confirmation email not showing properly");
                cacthingBugs();
            }
            try {
                Assert.assertTrue(homePage.isPasswordTextDisplayed());
                Assert.assertEquals("Please enter Password.", homePage.passwordTextGetText());
            }
            catch (Exception e) {
                System.out.println("Error for password not showing properly");
                cacthingBugs();
            }
            try {
                Assert.assertTrue(homePage.isConfirmPasswordTextDisplayed());
                Assert.assertEquals("Your password must be at least 8 characters long."
                        , homePage.confirmPasswordTextGetText());
            }
            catch (Exception e) {
                System.out.println("Error for confirmation password not showing properly");
                cacthingBugs();
            }
            try {
                Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
                Assert.assertEquals("Please accept our Terms and Conditions.", homePage.acceptTermsGetText());
            }
            catch (Exception e) {
                System.out.println("Error with accepting terms");
                cacthingBugs();
            }
        }
    }
        @Test
        public void negativeTestCase22 () throws Exception// invalid username inputs
        {
            homePage.clickCookie();
            homePage.inputEmail(eMail);
            homePage.inputConfirmationMail(eMail);
            homePage.inputPassword(myTestPassword);
            homePage.inputConfirmationPassword(myTestPassword);
            homePage.checkTermsAndCondition();
            js.executeScript("window.scrollBy(0,400)");
            homePage.checkRobot();
            homePage.clickCreateAnAccount();
            try {
                Assert.assertTrue(homePage.isUserNameTextDisplayed());
                Assert.assertEquals("Please enter Username.", homePage.userNameTextGetText());
            } catch (Exception e) {
                System.out.println("Error for username negative test case");
                cacthingBugs();
            } finally {
                for (char x : testChars) {
                    homePage.inputUsername(x);
                    try {
                        Assert.assertTrue(homePage.isUserNameTextDisplayed());
                        Assert.assertEquals("Please enter at least 5 characters.", homePage.userNameTextGetText());
                    }
                    catch (Exception e) {
                        System.out.println("Error for username not showing properly");
                        cacthingBugs();
                    }
                    homePage.inputUsername(myUsername + x);
                    try {
                        Assert.assertTrue(homePage.isUserNameTextDisplayed());
                        Assert.assertEquals("Only alphanumeric characters allowed.", homePage.userNameTextGetText());
                    }
                    catch (Exception e) {
                        System.out.println("Error for username not showing properly");
                        cacthingBugs();
                    }
                }
            }
        }
        @Test
        public void negativeTestCase23 () throws Exception {// invalid email and confirmation email inputs
            homePage.clickCookie();
            homePage.inputUsername(myUsername + "o");
            homePage.inputPassword(myTestPassword + "d");
            homePage.inputConfirmationPassword(myTestPassword + "d");
            js.executeScript("window.scrollBy(0,500)");
            Thread.sleep(1000);
            homePage.checkTermsAndCondition();
            homePage.checkNewsletters();
            homePage.clickCreateAnAccount();
            try {
                Assert.assertTrue(homePage.isEmailTextDisplayed());
                Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
            }
            catch (Exception e) {
                System.out.println("Error for email not showing properly");
                cacthingBugs();
            }
            finally {

                homePage.inputEmail("on purpose wrong");
                homePage.inputConfirmationMail("on purpose wrong");
                try {
                    Assert.assertTrue(homePage.isEmailTextDisplayed());
                    Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for email not showing properly");
                    cacthingBugs();
                }
                homePage.inputEmail("on purpose wrong@gmail.com");
                homePage.inputConfirmationMail("on purpose wrong@gmail.com");
                try {
                    Assert.assertTrue(homePage.isEmailTextDisplayed());
                    Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for email not showing properly");
                    cacthingBugs();
                }
                homePage.inputEmail("on_purpose_wrong@gma!il.com");
                homePage.inputConfirmationMail("on_purpose_wrong@gma!il.com");
                try {
                    Assert.assertTrue(homePage.isEmailTextDisplayed());
                    Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for email not showing properly");
                    cacthingBugs();
                }
                homePage.inputEmail("on purpose_wrong@gmail.com");
                homePage.inputConfirmationMail("on purpose_wrong@gmail.com");
                try {
                    Assert.assertTrue(homePage.isEmailTextDisplayed());
                    Assert.assertEquals("Please enter a valid email address.", homePage.emailTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for email not showing properly");
                    cacthingBugs();
                }
                homePage.inputEmail(eMail);
                homePage.inputConfirmationMail("mismatched_mail@gmail.com");
                try {
                    Assert.assertTrue(homePage.isEmailConfirmartionTextDisplayed());
                    Assert.assertEquals("Email address does not match.", homePage.emailConfirmationTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for email not showing properly");
                    cacthingBugs();
                }
            }
        }
        @Test
        public void negativeTestCase24 () throws Exception// invalid inputs for password and confirmation password
        {
            homePage.clickCookie();
            homePage.inputUsername(myUsername + "o");
            homePage.inputEmail(eMail);
            homePage.inputConfirmationMail(eMail);
            homePage.checkTermsAndCondition();
            homePage.checkNewsletters();
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0,300)");
            homePage.clickCreateAnAccount();
            try {
                Assert.assertTrue(homePage.isPasswordTextDisplayed());
                Assert.assertEquals("Please enter Password.", homePage.passwordTextGetText());
            } catch (Exception e) {
                System.out.println("Error for password not showing properly");
                cacthingBugs();
            } finally {
                homePage.inputPassword(myTestPassword);
                homePage.inputConfirmationPassword(myTestPassword);
                try {
                    Assert.assertTrue(homePage.isPasswordTextDisplayed());
                    Assert.assertEquals("Your password must be at least 8 characters long.", homePage.passwordTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for password not showing properly");
                    cacthingBugs();
                }
                homePage.inputPassword(myTestPassword + "d");
                homePage.inputConfirmationPassword("mismatched on purpose");
                try {
                    Assert.assertTrue(homePage.isConfirmPasswordTextDisplayed());
                    Assert.assertEquals("Password does not match, please check again.", homePage.confirmPasswordTextGetText());
                } catch (Exception e) {
                    System.out.println("Error for password not showing properly");
                    cacthingBugs();
                }
            }
        }
        @Test
        public void negativeTestCase25 () throws Exception // invalid checking mandatory checkboxes
        {
            homePage.clickCookie();
            homePage.inputUsername(myUsername + "o");
            homePage.inputEmail(eMail);
            homePage.inputConfirmationMail(eMail);
            homePage.inputPassword(myTestPassword + "!");
            homePage.inputConfirmationPassword(myTestPassword + "!");
            js.executeScript("window.scrollBy(0,500)");
            Thread.sleep(1000);
            homePage.clickCreateAnAccount();
            try {
                Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
                Assert.assertEquals("Please accept our Terms and Conditions.", homePage.acceptTermsGetText());
            }
            catch (Exception e) {
                System.out.println("Errors for terms and conditions not showing properly");
                cacthingBugs();
            }
            finally {
                homePage.checkNewsletters();
                homePage.clickCreateAnAccount();
                try {
                    Assert.assertTrue(homePage.isAcceptTermsTextDisplayed());
                    Assert.assertEquals("Please accept our Terms and Conditions.", homePage.acceptTermsGetText());
                } catch (Exception e) {
                    System.out.println("Errors for terms and conditions not showing properly");
                    cacthingBugs();
                }
                homePage.checkTermsAndCondition();
                homePage.clickCreateAnAccount();
                try {
                    Assert.assertTrue(homePage.errorIsDisplayed());
                    Assert.assertEquals("Error! Invalid captcha response.\n" +
                            "Please Try Again", homePage.errorText());
                } catch (Exception e) {
                    System.out.println("Errors for terms and conditions not showing properly");
                    cacthingBugs();
                }
            }
        }
        @Test
        public void inspectButtonsForSameTab () throws Exception // checking several other elements opening new page in the same tab
        {
            homePage.clickCookie();
            homePage.clickOnGasIcon();
            try {
                Assert.assertTrue(homePage.isGasTrackerDisplayed());
                Assert.assertEquals("Ethereum Gas Tracker", homePage.gasTrackerText());
            }
            catch (Exception e) {
                System.out.println("Correct new page in same tab not showing properly");
                cacthingBugs();
            }
            finally {
                driver.navigate().back();
                homePage.clickOnTransaction();
                try {
                    Assert.assertTrue(homePage.isTransactionDisplayed());
                    Assert.assertEquals("Transactions", homePage.getTransactionText());
                } catch (Exception e) {
                    System.out.println("Correct new page in same tab not showing properly");
                    cacthingBugs();
                }
            }


        }
        @Test
        public void inspectButtonsForNewTab () throws Exception // checking several other elements opening new page in the new tab
        {
            homePage.clickCookie();
            homePage.clickOnApiDocumentation();
            newTab.moveToNewTab();
            try {
                Assert.assertTrue(newTab.isIntroductionDisplayed());
                Assert.assertEquals("Introduction", newTab.introductionGetText());
            }
            catch (Exception e) {
                    System.out.println("Correct new page in new tab not showing properly");
                    cacthingBugs();
            }
            finally {
                driver.close();
                newTab.moveToPreviousTab();
                Thread.sleep(1000);
                js.executeScript("window.scrollBy(0,1500)");
                Thread.sleep(1000);
                js.executeScript("window.scrollBy(0,1500)");
                homePage.clickReddit();
                newTab.moveToNewTab();
                try {
                    Assert.assertTrue(newTab.redditDisplayed());
                }
                catch (Exception e) {
                    System.out.println("Correct new page in new tab not showing properly");
                    cacthingBugs();
                }
                newTab.moveToPreviousTab();
                driver.close();
                newTab.moveToPreviousTab();
            }
        }
        private void cacthingBugs () throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File scrFile = ts.getScreenshotAs(OutputType.FILE);
            File destFile = new File("D:\\Marko\\Moje\\QA\\GitClone\\QA-TraceLabs\\screenshots\\bug" + d2 + ".png");
            FileUtils.copyFile(scrFile, destFile);
        }
    }


