package Pages;

import Utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Products extends Actions {
    private AndroidDriver androidDriver;

    private By addToCartButtons = AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
    private By cartButton = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");


    public Products(AndroidDriver driver) {
        super(driver);
        this.androidDriver = driver;
    }

    public void addFirstProductToCart() {
        androidDriver.findElements(addToCartButtons).get(0).click();
    }

    public void addSecondProductToCart() {
        androidDriver.findElements(addToCartButtons).get(0).click();
    }

    public Cart openCart() {
        androidDriver.findElement(cartButton).click();
        return new Cart(androidDriver);
    }
}
