package views;

import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class HomeView extends BaseView {

  private final AppiumDriver driver;

  public HomeView(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public WebElement accessibilityButton() {
    return driver.findElement(new ByAccessibilityId("Accessibility"));
  }

  public WebElement animationButton() {
    return driver.findElement(new ByAccessibilityId("Animation"));
  }

  public WebElement appButton() {
    return driver.findElement(new ByAccessibilityId("App"));
  }

  public WebElement contentButton() {
    return driver.findElement(new ByAccessibilityId("Content"));
  }

  public WebElement graphicsButton() {
    return driver.findElement(new ByAccessibilityId("Graphics"));
  }

  public WebElement mediaButton() {
    return driver.findElement(new ByAccessibilityId("Media"));
  }

  public WebElement nfcButton() {
    return driver.findElement(new ByAccessibilityId("NFC"));
  }

  public WebElement osButton() {
    return driver.findElement(new ByAccessibilityId("OS"));
  }

  public WebElement preferenceButton() {
    return driver.findElement(new ByAccessibilityId("Preference"));
  }

  public WebElement textButton() {
    return driver.findElement(new ByAccessibilityId("Text"));
  }

  public WebElement viewsButton() {
    return driver.findElement(new ByAccessibilityId("Views"));
  }

  public AccessibilityView navigateToAccessibilityView() {
    accessibilityButton().click();
    return new AccessibilityView(driver);
  }

  public AnimationView navigateToAnimationView() {
    animationButton().click();
    return new AnimationView(driver);
  }
}
