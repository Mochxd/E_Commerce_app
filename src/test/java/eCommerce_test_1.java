import Pages.Cart;
import Pages.FillForm;
import Pages.Products;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_test_1 extends BaseTest {

    @Test(priority = 1)
    public void fillForm() {
        FillForm formPage = new FillForm(androidDriver);
        formPage.enterName("Eman Mostafa");
        formPage.selectCountry("Egypt");
        formPage.selectGender("female");
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
}
