import Pages.Cart;
import Pages.FillForm;
import Pages.Products;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_test_1 extends BaseTest {

    @Test(priority = 1)
    public void fillForm() {
        FillForm formPage = new FillForm(androidDriver);
        formPage.enterName("Eman Mostafa");
        formPage.selectCountry("Egypt");
        formPage.selectGender("female");
        formPage.clickLetsShop();
    }
    @Test(priority = 1, dataProvider = "data2")
    public void fillFormWithDataProvider(String name, String gender, String country) {
        FillForm formPage = new FillForm(androidDriver);
        formPage.enterName(name);
        formPage.selectCountry(country);
        formPage.selectGender(gender);
        formPage.clickLetsShop();
    }
    @Test(priority = 2)
    public void fillFormWithoutEnteringName() {
        FillForm formPage = new FillForm(androidDriver);
        formPage.selectCountry("Albania");
        formPage.selectGender("female");
        formPage.clickLetsShop();

        String toastMessage = formPage.getToastMessage();
        System.out.println(toastMessage);
        Assert.assertEquals(toastMessage, "Please enter your name", "Toast message did not match the expected value.");
    }
        @Test(priority = 3)
        public void addProductToCartTest() throws InterruptedException {
            FillForm formPage = new FillForm(androidDriver);
            formPage.enterName("Eman Mostafa");
            formPage.selectCountry("Albania");
            formPage.selectGender("Female");
            Products productsPage = formPage.clickLetsShop();

            productsPage.addFirstProductToCart();
            productsPage.addSecondProductToCart();
            Cart cart = productsPage.openCart();

            double actualResult = cart.getDisplayedTotalAmount();
            double totalPrice = cart.getTotalPrice();
            System.out.println("Total price of products in the cart: " + totalPrice);
            Assert.assertEquals(actualResult, totalPrice, "The calculated total does not match the displayed total");

            cart.agreeToTermsAndConditions();
            cart.selectCheckBox();
            cart.proceedToWebView();
            Thread.sleep(4000);
            cart.switchToWebView("WEBVIEW_com.androidsample.generalstore");
            cart.searchInWebView("cat");
            cart.switchBackToNative();
        }
        @DataProvider
        public Object [][] data() throws IOException {
            List<HashMap<String, String>> jsonData = getJsonData("D:\\Appium\\E_Commerce_app\\src\\main\\resources\\data.json");
            Object [][] data = new Object [1][3];
            data [0][0] = "Mohamed Mostafa";
            data [0][1] = "male";
            data [0][2] = "Albania";
            return data;
        }

    @DataProvider
    public Object[][] data2() throws IOException {
        List<HashMap<String, String>> jsonData = getJsonData("D:\\Appium\\E_Commerce_app\\src\\main\\resources\\data.json");
        Object[][] data = new Object[jsonData.size()][3];

        for (int i = 0; i < jsonData.size(); i++) {
            data[i][0] = jsonData.get(i).get("name");
            data[i][1] = jsonData.get(i).get("gender");
            data[i][2] = jsonData.get(i).get("country");
        }

        return data;
    }
}
