package demoQA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class DemoQaTest extends BaseTest {
    private final By alertText = By.xpath("//h5[contains(text(),'Alerts, Frame & Windows')]");
    private final By browserWinText = By.xpath("//li[@id='item-0']//span[text()='Browser Windows']");
    private final By tabBtn = By.id("tabButton");
    private final By headingText = By.id("sampleHeading");
    private final By scrollText = By.xpath("//h5[contains(text(),'Book Store Application')]");

    private void clickUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    private void scrollViewUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test
    public void demoTest() {
        WebElement scrollTextElement = driver.findElement(scrollText);
        scrollViewUsingJavaScript(scrollTextElement);
        WebElement alertTextElement = driver.findElement(alertText);
        clickUsingJavaScript(alertTextElement);
        driver.findElement(browserWinText).click();
        Assert.assertTrue(driver.findElement(tabBtn).isDisplayed(),"New Tab Button isn't apperad So Browser Windows page is not Displayed");
        driver.findElement(tabBtn).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertTrue(driver.findElement(headingText).isDisplayed(),"SampleHeading page is not Displayed");
    }
}
