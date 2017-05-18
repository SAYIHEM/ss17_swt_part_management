import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ReceivingStockTest extends StockTest {
    private Purchasing purch;

    @Override
    @Before
    public void setUp() {
        stock = new ReceivingStock(10, 100);
        purch = new Purchasing((ReceivingStock) stock);
        part = Factory.create(PartType.SINGLE_COMPONENT, "0", "A Single Component");

        stock.insert(part, 15);
    }

    @Test
    public void testConstructorIllegalArgument() {
        try {
            new ReceivingStock(-1, 1);
            fail("ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the minStockItems argument is less than 1!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new ReceivingStock(0, -1);
            fail("ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the maxStockItems argument is less than the minStockItems argument!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new ReceivingStock(5, 4);
            fail("ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the maxStockItems argument is less than the minStockItems argument!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testObserver() {
        /*
         * The ReveivingStock is configured in a way that when a part's quantity falls below 10 the stock has to be
         * refilled back to 100 items.
         */
        stock.register(purch);
        stock.delete(part, 6);
        assertEquals(
                "It seems that Stock.register() or Stock.notify() are not implemented correctly! The ReceivingStock is not getting refilled correctly.",
                100, stock.get(part));
    }

    @Test
    public void testGetMinStockItems() {
        assertEquals("ReceivingStock.getMinStockItems() should return the correct value!", 10,
                ((ReceivingStock) stock).getMinStockItems());
    }

    @Test
    public void testGetMaxStockItems() {
        assertEquals("ReceivingStock.getMaxStockItems() should return the correct value!", 100,
                ((ReceivingStock) stock).getMaxStockItems());
    }
}
