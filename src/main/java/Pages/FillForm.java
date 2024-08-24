package Pages;

import Utils.Actions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FillForm extends Actions {
    private AndroidDriver androidDriver;
    private By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private By countryDropdown = AppiumBy.id("android:id/text1");
    private By femaleRadioButton = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private By maleRadioButton = AppiumBy.id("com.androidsample.generalstore:id/radioMale"); // Corrected typo
    private By letsShopButton = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");


    public FillForm(AndroidDriver driver) {
        super(driver);
        this.androidDriver = driver;
    }

    public void enterName(String name) {
        androidDriver.findElement(nameField).sendKeys(name);
    }

    public void selectCountry(String countryName) {
        androidDriver.findElement(countryDropdown).click();
        androidDriver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().text(\"" + countryName + "\"));")).click();
    }

    public void selectGender(String gender) {
        if (gender.equals("female") || gender.equals("Female")) {
            androidDriver.findElement(femaleRadioButton).click();
        } else {
            androidDriver.findElement(maleRadioButton).click();
        }
    }
    public void clickLetsShop() {
        androidDriver.findElement(letsShopButton).click();
    }
}
