import org.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSystemTest {
    /**
     * Test case: Calculate total without any special prices on products
     * Expected: 304
     */
    @Test
    public void testCalculateTotalNoOffers() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, null));
        checkoutSystem.addProduct(new Product("B", 35, null));
        checkoutSystem.addProduct(new Product("C", 25, null));
        checkoutSystem.addProduct(new Product("D", 12, null));
        checkoutSystem.consumeDataSource("/data-set-1.json");

        int expectedTotal = (50 * 3) + (35 * 3) + 25 + (12 * 2);
        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + expectedTotal + ", Actual: " + actualTotal);
        assertEquals(expectedTotal, actualTotal);
    }

    /**
     * Test case: Calculate total including special offers
     * Expected: 284
     */
    @Test
    public void testCalculateTotalWithOffers() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, new SpecialPrice(3, 140)));
        checkoutSystem.addProduct(new Product("B", 35, new SpecialPrice(2, 60)));
        checkoutSystem.addProduct(new Product("C", 25, null));
        checkoutSystem.addProduct(new Product("D", 12, null));
        checkoutSystem.consumeDataSource("/data-set-1.json");

        int expectedTotal = 140 + 95 + 25 + (12 * 2);
        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + expectedTotal + ", Actual: " + actualTotal);
        assertEquals(expectedTotal, actualTotal);
    }

    /**
     * Test case: No products in the cart
     * Expected: 0
     */
    @Test
    public void testEmptyCart() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();

        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + 0 + ", Actual: " + actualTotal);
        assertEquals(0, actualTotal);
    }

    /**
     * Test case: An invalid item in the shopping cart
     * Expected: 140
     */
    @Test
    public void testInvalidProduct() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, new SpecialPrice(3, 140)));
        checkoutSystem.consumeDataSource("/data-set-2.json");

        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + 140 + ", Actual: " + actualTotal);
        assertEquals(140, actualTotal);
    }

    /**
     * Test case: A product has a special price but is below the required quantity for it
     * Expected: 100
     */
    @Test
    public void testSpecialPriceUnderQuantity() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, new SpecialPrice(3, 140)));
        checkoutSystem.consumeDataSource("/data-set-3.json");

        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + 100 + ", Actual: " + actualTotal);
        assertEquals(100, actualTotal);
    }

    /**
     * Test case: An item in the cart has a negative quantity
     * Expected: 30
     */
    @Test
    public void testNegativeQuantity() {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, null));
        checkoutSystem.addProduct(new Product("B", 10, null));
        checkoutSystem.addProduct(new Product("C", 10, null));
        checkoutSystem.consumeDataSource("/data-set-4.json");

        int actualTotal = checkoutSystem.calculateSubTotal();
        System.out.println("Expected: " + 30 + ", Actual: " + actualTotal);
        assertEquals(30, actualTotal);
    }

}
