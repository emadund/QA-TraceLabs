package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class NewTab extends BaseTest {

    @FindBy (id="reddit-logo")
    WebElement reddit;

    @FindBy (css=".text-4xl.font-bold")
    WebElement introduction;

    public NewTab () {
        PageFactory.initElements(driver, this);
    }
    public void moveToNewTab () {
        wdWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
    public void moveToPreviousTab () {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public boolean isIntroductionDisplayed() {
        return isElementDisplayed(introduction);
    }
    public String introductionGetText() {
        return getTextFromElement(introduction);
    }

    public boolean redditDisplayed() {
        return isElementDisplayed(reddit);
    }

}
