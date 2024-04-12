package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class ConfirmationYourEmail extends BaseTest {

    @FindBy (css = ".fs-base")
    WebElement registrationSuccessfull;

    public void moveToNewTab () {
        wdWait.until(ExpectedConditions.numberOfWindowsToBe(3));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
    }
    public ConfirmationYourEmail () {
        PageFactory.initElements(driver, this);
    }

    public boolean isConfirmationRegistrationDisplayed () {
        return isElementDisplayed(registrationSuccessfull);
    }

    public String confirmationRegistrationText () {
        return getTextFromElement(registrationSuccessfull);
    }
}
