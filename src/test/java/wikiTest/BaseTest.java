package wiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final int MAX_WAIT = 10;
    protected static final String URL = "https://www.wikipedia.org/";
    protected static final String RELATIVE_RESOURCE_PATH = "target/download/";
    protected final String absoluteDownloadPath = Paths.get(System.getProperty("user.dir"), RELATIVE_RESOURCE_PATH).toString();

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", absoluteDownloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
