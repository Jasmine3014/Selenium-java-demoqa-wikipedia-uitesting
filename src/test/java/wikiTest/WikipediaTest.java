package wiki;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WikipediaTest extends BaseTest {
    private final By wikiHeadline = By.xpath("//h1[@class='central-textlogo-wrapper']");
    private final By languageDropDown = By.id("searchLanguage");
    private final String languageValue = "en";
    private final By searchText = By.id("searchInput");
    private final String searchKeyword = "Albert Einstein";
    private final By submitBtn = By.xpath("//button[@type='submit']");
    private final By toolsBtn = By.id("vector-page-tools-dropdown-checkbox");
    private final By downloadPdfBtn = By.id("coll-download-as-rl");
    private final By fileDwnld = By.xpath("//button[@type='submit']");
    private final By fileNameElement = By.xpath("//div[@class='mw-electronpdfservice-selection-label-desc']");
    private String fileName;
    private String filePath;
    private File downloadedFile;

    @Test
    public void wikiTest() {
        Assert.assertTrue(driver.findElement(wikiHeadline).isDisplayed(),"Wikipedia Home Page is not Displayed");
        WebElement languageDropDwn = driver.findElement(languageDropDown);
        Select select = new Select(languageDropDwn);
        select.selectByValue(languageValue);
        driver.findElement(searchText).sendKeys(searchKeyword);
        driver.findElement(submitBtn).click();
        driver.findElement(toolsBtn).click();
        driver.findElement(downloadPdfBtn).click();
        fileName = driver.findElement(fileNameElement).getText().trim();
        Assert.assertTrue(fileName.endsWith(".pdf"),"The file name does not end with .pdf: " + fileName);
        filePath = RELATIVE_RESOURCE_PATH + fileName;
        downloadedFile = new File(filePath);
        driver.findElement(fileDwnld).click();
        Assert.assertTrue(isFileExists(downloadedFile),"Downloaded file does not exist: " + filePath);
    }

    private boolean isFileExists(File file) {
        try {
            Awaitility.await().atMost(MAX_WAIT,TimeUnit.SECONDS).until(file::exists);
        } catch (ConditionTimeoutException Exception) {
            return false;
        }
        return true;
    }

    @AfterMethod
    public void deleteMethod() {
        if(downloadedFile.exists()){
            downloadedFile.delete();
        }
    }
}
