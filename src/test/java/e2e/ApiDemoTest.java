package e2e;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import views.AccessibilityView;
import views.AnimationView;
import views.HomeView;

public class ApiDemoTest extends BaseTest {

  @Test(description = "Home View items validation")
  public void homeViewValidation() {
    SoftAssert softAssertion = new SoftAssert();
    HomeView homeView = new HomeView(getDriver());

    softAssertion.assertTrue(homeView.accessibilityButton().isDisplayed(),
        "Accessibility button should have been displayed");
    softAssertion.assertTrue(homeView.animationButton().isDisplayed(), "Animation button should have been displayed");
    softAssertion.assertTrue(homeView.appButton().isDisplayed(), "App button should have been displayed");
    softAssertion.assertTrue(homeView.contentButton().isDisplayed(), "Content button should have been displayed");
    softAssertion.assertTrue(homeView.graphicsButton().isDisplayed(), "Graphics button should have been displayed");
    softAssertion.assertTrue(homeView.mediaButton().isDisplayed(), "Media button should have been displayed");
    softAssertion.assertTrue(homeView.nfcButton().isDisplayed(), "NFC button should have been displayed");
    softAssertion.assertTrue(homeView.osButton().isDisplayed(), "OS button should have been displayed");
    softAssertion.assertTrue(homeView.preferenceButton().isDisplayed(), "Preference button should have been displayed");
    softAssertion.assertTrue(homeView.textButton().isDisplayed(), "Text button should have been displayed");
    softAssertion.assertTrue(homeView.viewsButton().isDisplayed(), "View button should have been displayed");

    softAssertion.assertAll("Asserting Home View navigation elements");
  }

  @Test(description = "Verify navigation to Accessibility View")
  public void navigateToAccessibilityViewValidation() {
    SoftAssert softAssertion = new SoftAssert();
    AccessibilityView accessibilityView = new HomeView(getDriver()).navigateToAccessibilityView();

    softAssertion.assertTrue(accessibilityView.accessibilityCustomViewButton().isDisplayed(),
        "Custom View button should have been displayed");
    softAssertion.assertTrue(accessibilityView.accessibilityServiceButton().isDisplayed(),
        "Service button should have been displayed");
    softAssertion.assertTrue(accessibilityView.accessibilityNodeProviderButton().isDisplayed(),
        "Node Provider button should have been displayed");
    softAssertion.assertTrue(accessibilityView.accessibilityNodeQueryingButton().isDisplayed(),
        "Node Querying button should have been displayed");

    softAssertion.assertAll("Asserting Home View navigation elements");

    accessibilityView.navigateBack();
  }

  @Test(description = "Verify navigation to Animation View")
  public void navigateToAnimationViewValidation() {
    SoftAssert softAssertion = new SoftAssert();
    AnimationView animationView = new HomeView(getDriver()).navigateToAnimationView();

    softAssertion.assertTrue(animationView.bouncingBallsButton().isDisplayed(),
        "Bouncing Ball button should have been displayed");
    softAssertion.assertTrue(animationView.cloningButton().isDisplayed(), "Cloning button should have been displayed");
    softAssertion.assertTrue(animationView.customEvaluatorButton().isDisplayed(),
        "Custom Evaluator button should have been displayed");

    softAssertion.assertAll("Asserting Animation View navigation elements");

    animationView.navigateBack();
  }
}
