package uiTesting;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class UITest extends BaseTest {
    private final By sampleAppBtn = By.xpath("//a[contains (@href, '/sampleapp')]");
    private final By sampleAppHeading = By.xpath("//h3[contains(text(),'Sample App')]");
    private final By userName = By.xpath("//input[@type ='text']");
    private final By password = By.xpath("//input[@type ='password']");
    private final By loginBtn = By.id("login");
    private final String Random_Text = UUID.randomUUID().toString();
    private String passwordGiven = "pwd";
    private final By sucessMsg = By.id("loginstatus");

    @Test
    public void homeTest() {
        driver.findElement(sampleAppBtn).click();
        Assert.assertTrue(driver.findElement(sampleAppHeading).isDisplayed(),"Sample Page is not Displayed");
        driver.findElement(userName).sendKeys(Random_Text);
        driver.findElement(password).sendKeys(passwordGiven);
        driver.findElement(loginBtn).click();
        Assert.assertTrue(driver.findElement(sucessMsg).isDisplayed(), "Success Message is not Displayed");
    }
}
