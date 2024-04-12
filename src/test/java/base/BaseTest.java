package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {  // Base Class for initialisation Google Chrome browser

    protected static WebDriver driver;
    protected static WebDriverWait wdWait;  // waiting for Webelements to appear
    protected static JavascriptExecutor js; // for scrolling window

    @Before

    public  void initialSetup () {
        WebDriverManager.chromedriver().driverVersion("123.0.6312.107").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote--allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        wdWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://etherscan.io/register");
    }

    protected void clickOnElement (WebElement w) {
        wdWait.until(ExpectedConditions.elementToBeClickable(w));
        w.click();
    }

    protected void fillTextElement (WebElement w, String text) {
        wdWait.until(ExpectedConditions.visibilityOf(w));
        w.clear();
        w.sendKeys(text);
    }

    protected boolean isElementDisplayed (WebElement w) {
        wdWait.until(ExpectedConditions.visibilityOf(w));
        return w.isDisplayed();
    }
    protected String getTextFromElement (WebElement w) {
        wdWait.until(ExpectedConditions.visibilityOf(w));
        return w.getText();
    }

    protected void clickOnDropDownItem (WebElement parent, WebElement target) {
        Actions hover = new Actions(driver);
        wdWait.until(ExpectedConditions.elementToBeClickable(parent));
        hover.moveToElement(parent).perform();
        clickOnElement(target);
    }

    @After
    public void tearDown() {   // To shutdown browsers after tests finished
        driver.close();
        driver.quit();
    }

}
