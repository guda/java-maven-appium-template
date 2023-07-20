package views;

import io.appium.java_client.AppiumDriver;

public class BaseView {

  private final AppiumDriver driver;

  public BaseView(AppiumDriver driver) {
    this.driver = driver;
  }

  public void navigateBack() {
    driver.navigate().back();
  }
}
