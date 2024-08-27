package Pages;

import Utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Cart extends Actions {
    private AndroidDriver androidDriver;
    private By totalAmountLbl = AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");
    private By productPrice = AppiumBy.id("com.androidsample.generalstore:id/productPrice");
    private By termsButton = AppiumBy.id("com.androidsample.generalstore:id/termsButton");
    private By acceptButton = AppiumBy.id("android:id/button1");
    private By checkBox = AppiumBy.className("android.widget.CheckBox");
    private By proceedButton = AppiumBy.id("com.androidsample.generalstore:id/btnProceed");

    public Cart(AndroidDriver driver) {
        super(driver);
        this.androidDriver = driver;
    }

    public double getDisplayedTotalAmount() {
        WebElement totalAmountElement = waitForVisibility(androidDriver.findElement(totalAmountLbl), 30);
        return stringParse(totalAmountElement.getText());
    }

    public double getTotalPrice() {
        List<WebElement> prices = androidDriver.findElements(productPrice);
        double sum = 0;
        for (WebElement priceElement : prices) {
            String priceString = priceElement.getText();
            double priceValue = stringParse(priceString);
            sum += priceValue;
        }
        return sum;
    }

    public void agreeToTermsAndConditions() {
        longPress(androidDriver.findElement(termsButton));
        androidDriver.findElement(acceptButton).click();
    }

    public void selectCheckBox() {
        androidDriver.findElement(checkBox).click();
    }

    public void proceedToWebView() {
        androidDriver.findElement(proceedButton).click();
    }

    public void switchToWebView(String webViewContext) {
        Set<String> contextHandles = androidDriver.getContextHandles();
        for (String context : contextHandles) {
            if (context.contains(webViewContext)) {
                androidDriver.context(context);
                break;
            }
        }
    }

    public void searchInWebView(String query) {
        androidDriver.findElement(By.name("q")).sendKeys(query);
        androidDriver.findElement(By.name("q")).sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public void switchBackToNative() {
        androidDriver.context("NATIVE_APP");
    }
}
