import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;


public class ArsoVremeTests {

    private AndroidDriver driver;//The AndroidDriver extends the AppiumDriver which eventually extends from the RemoteWebDriver that belongs to Selenium

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();//Set the desired capabilities like in the appium desktop
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.julij.arsovreme");
        desiredCapabilities.setCapability("appActivity", "com.julij.arsovreme.MainActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");//The url that the appium sits on

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void EnabledTest() {
        WebElement app =  driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.view.View[2]"));//Find element by its id
        app.click();//Check that the element is enabled
        Assertions.assertTrue(app.isEnabled());
    }

    @Test
    public void searchIconTest() {
        WebElement app =  driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.view.View[1]/android.view.View[2]"));//Find element by its id
        app.click();
        WebElement input1 = driver.findElement(By.id("meteo_search-location"));
        input1.sendKeys("israel");
        WebElement result = driver.findElement(By.id("meteo_search-location/ans3"));//Send text or in this case number as text to the selected element
        Assertions.assertEquals("israel",result.getText());
    }

    @Test
    public void DropDownMenuTest() {
        WebElement menu = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[1]/android.widget.Button"));
        menu.click();
        Select dropdown = new Select(driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[2]")));
        dropdown.selectByVisibleText("poletne napovedi");
    }

    @Test
    public void LocatoinOnMapTest() {
        WebElement details = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View/android.view.View[5]/android.widget.Button"));
        details.click();
        WebElement locatoin = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View/android.widget.Button"));
        locatoin.click();
        WebElement result = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View[1]"));
        Assertions.assertEquals("Reka",result.getText());
    }

    @Test
    public void DateAndTimeTest() {
        {
            WebElement DATE = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.widget.Button"));
            DATE.getText();
            SimpleDateFormat formatter = new SimpleDateFormat("22/4/2023");
            Assertions.assertEquals(DATE.getText(),formatter);
        }

        WebElement result = driver.findElement(By.id("com.example.myapplication:id/result"));
        Assertions.assertEquals("0.0",result.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
