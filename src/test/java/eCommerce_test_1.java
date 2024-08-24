import Pages.FillForm;
import Pages.Products;
import Utils.Actions;
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
        Products productsPage = new Products(androidDriver);

        formPage.enterName("Eman Mostafa");
        formPage.selectCountry("Albania");
        formPage.selectGender("Female");
        formPage.clickLetsShop();

        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();
        productsPage.openCart();

        double totalPrice = productsPage.getTotalPrice();
        System.out.println("Total price of products in the cart: " + totalPrice);

        double actualResult = productsPage.getDisplayedTotalAmount();
        Assert.assertEquals(actualResult, totalPrice, "The calculated total does not match the displayed total");
    }
}