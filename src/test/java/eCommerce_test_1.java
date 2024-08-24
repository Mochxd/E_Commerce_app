import Pages.Cart;
import Pages.FillForm;
import Pages.Products;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_test_1 extends BaseTest {

    @Test
    public void fillForm() {
        FillForm formPage = new FillForm(androidDriver);
        formPage.enterName("Eman Mostafa");
        formPage.selectCountry("Egypt");
        formPage.selectGender("female");
        formPage.clickLetsShop();
    }

    @Test
    public void addProductToCartTest() {
        FillForm formPage = new FillForm(androidDriver);
        formPage.enterName("Eman Mostafa");
        formPage.selectCountry("Albania");
        formPage.selectGender("Female");
        Products productsPage =  formPage.clickLetsShop();

        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();
        Cart cart = productsPage.openCart();

        double actualResult = cart.getDisplayedTotalAmount();
        double totalPrice = cart.getTotalPrice();
        System.out.println("Total price of products in the cart: " + totalPrice);
        Assert.assertEquals(actualResult, totalPrice, "The calculated total does not match the displayed total");
    }
}