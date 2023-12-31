package e2e;

import constants.ProjectConstants;
import io.appium.java_client.android.AndroidDriver;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  ThreadLocal<AndroidDriver> driver = new ThreadLocal<AndroidDriver>();

  @BeforeMethod
  public void setUp(Method method) throws MalformedURLException {
    if (System.getProperty(ProjectConstants.REMOTE) != null) {
      if (System.getProperty(ProjectConstants.SERVICE).equals(ProjectConstants.SAUCE_LABS)) {
        System.out.println("******* RUNNING SAUCE LABS");
        sauceLabsDriverSetup(method);
      } else if (System.getProperty(ProjectConstants.SERVICE).equals(ProjectConstants.BROWSERSTACK)) {
        System.out.println("******* RUNNING BROWSERSTACK");
        browserstackDriverSetup();
      }
    } else {
      System.out.println("******* RUNNING LOCAL");
      localDriverSetup();
    }
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (System.getProperty(ProjectConstants.REMOTE) != null) {
      if (System.getProperty(ProjectConstants.SERVICE).equals(ProjectConstants.SAUCE_LABS)) {
        sauceLabsDriverTearDown(result);
      } else if (System.getProperty(ProjectConstants.SERVICE).equals(ProjectConstants.BROWSERSTACK)) {
        localDriverTearDown();
      }
    } else {
      localDriverTearDown();
    }
  }

  public AndroidDriver getDriver() {
    return driver.get();
  }

  private void sauceLabsDriverSetup(Method method) throws MalformedURLException {
    System.out.println("Sauce Android Native - BeforeMethod hook");

    String username = "oauth-amir.gudic-92595";
    String accessKey = "777b56b0-bd77-4c45-8cb5-f8ca278e4d02";

    String sauceUrl = "@ondemand.eu-central-1.saucelabs.com:443";

    String SAUCE_REMOTE_URL = "https://" + username + ":" + accessKey + sauceUrl + "/wd/hub";
    System.out.println("****** URL: " + SAUCE_REMOTE_URL);

    URL url = new URL(SAUCE_REMOTE_URL);

    MutableCapabilities capabilities = new MutableCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("app", "storage:filename=ApiDemos-debug.apk");
    capabilities.setCapability("deviceName", "Google Pixel GoogleAPI Emulator");
    capabilities.setCapability("platformVersion", "8.1");
    capabilities.setCapability("automationName", "UiAutomator2");
    capabilities.setCapability("name", method.getName());

    MutableCapabilities sauceOptions = new MutableCapabilities();
    sauceOptions.setCapability("appiumVersion", "1.22.1");
    sauceOptions.setCapability("build", "1.0");
    sauceOptions.setCapability("name", "Regression");
    capabilities.setCapability("sauce:options", sauceOptions);

    try {
      driver.set(new AndroidDriver(url, capabilities));
    } catch (Exception e) {
      System.out.println("*** Problem to create the Android driver " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private void browserstackDriverSetup() throws MalformedURLException {
    String userName = "amirgudic_NhlvX8";
    String accessKey = "m7xMf8xhqy4exJuAVo3t";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("platformVersion", "8.1");
    capabilities.setCapability("deviceName", "Samsung Galaxy Note 9");
    capabilities.setCapability("automationName", "UiAutomator2");
    capabilities.setCapability("app", "bs://add5fa08bba919e51fe25399e4c4637c799ad213");
    capabilities.setCapability("project", "SS Sample Run");
    capabilities.setCapability("build", "Build 1.0.0");
    capabilities.setCapability("name", "ApiDemo Sample Test");
    capabilities.setCapability("browserstack.appium_version", "1.22.0");

    driver.set(
        new AndroidDriver(
            new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),
            capabilities)
    );
  }

  private void localDriverSetup() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "android");
    capabilities.setCapability("automationName", "UiAutomator2");
    capabilities.setCapability("platformVersion", "8.1");
    capabilities.setCapability("deviceName", "Pixel_5");
    capabilities.setCapability("autoGrantPermissions", "true");
    capabilities.setCapability("autoAcceptAlerts", "true");
    capabilities.setCapability("fullReset", "true");
    capabilities.setCapability("noReset", "false");
    capabilities.setCapability("app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

    driver.set(new AndroidDriver(new URL("http://localhost:4723/wd/hub/"), capabilities));
  }

  private void sauceLabsDriverTearDown(ITestResult result) {
    String meho = "sauce:job-result=";
    System.out.println("Sauce - AfterMethod hook");
    ((JavascriptExecutor) getDriver()).executeScript(meho + (result.isSuccess() ? "passed" : "failed"));
    getDriver().quit();
  }

  private void localDriverTearDown() {
    if (driver != null) {
      driver.get().quit();
    }
  }
}
