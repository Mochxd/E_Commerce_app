package Pages;

import Utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart extends Actions {
    protected AndroidDriver androidDriver;
    private By productPrice = AppiumBy.id("com.androidsample.generalstore:id/productPrice");
    private By totalAmountLbl = AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");

    public Cart(AndroidDriver driver) {
        super(driver);
        this.androidDriver = driver;
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

    public double getDisplayedTotalAmount() {
        WebElement totalAmountElement = waitForVisibility(androidDriver.findElement(totalAmountLbl), 30);
        return stringParse(totalAmountElement.getText());
    }
}
