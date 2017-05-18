import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class StockTest {
    protected Stock stock;
    protected Part part;

    @Before
    public void setUp() {
        stock = new Stock() {
        };
        part = Factory.create(PartType.SINGLE_COMPONENT, "0", "A Single Component");

        stock.insert(part, 15);
    }

    @Test
    public void testInsertIllegalArgument() {
        try {
            stock.insert(null, 15);
            fail("ReceivingStock.insert() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            stock.insert(part, 0);
            fail("ReceivingStock.insert() should throw an IllegalArgumentException if the count argument non-positive!");
        } catch (IllegalArgumentException e) {
        }

        try {
            stock.insert(part, -1);
            fail("ReceivingStock.insert() should throw an IllegalArgumentException if the count argument is non-positive!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testInsert() {
        SingleComponent sc = new SingleComponent("3", "name");
        stock.insert(sc, 15);
        assertEquals("Stock.insert() should add new parts to the stock if it did not contain these parts previously!",
                15, stock.get(sc));
        stock.insert(sc, 15);
        assertEquals("Stock.insert() should insert the correct number of parts into the stock!", 30, stock.get(sc));
    }

    @Test
    public void testGetNullArgument() {
        try {
            stock.get(null);
            fail("Stock.get() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testGet() {
        assertEquals("Stock.get() should return -1 if the stock does not contain the part!", -1,
                stock.get(Factory.create(PartType.RESOURCE, "1", "A Resource Component")));
        assertEquals("Stock.get() should return the correct number of parts within the stock!", 15, stock.get(part));
    }

    @Test
    public void testSetNullArgument() {
        try {
            stock.set(null, 10);
            fail("Stock.set() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            stock.insert(part, 0);
            fail("ReceivingStock.insert() should throw an IllegalArgumentException if the count argument is less than 1!");
        } catch (IllegalArgumentException e) {
        }

        try {
            stock.insert(part, -1);
            fail("ReceivingStock.insert() should throw an IllegalArgumentException if the count argument is less than 1!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSet() {
        Part testPart = Factory.create(PartType.RESOURCE, "1", "A Resource Component");

        assertFalse("Stock.set() should return false if the part is not in the stock!", stock.set(testPart, 5));
        assertFalse("Stock.set() should return false if the count argument has a negative value!",
                stock.set(testPart, -1));
        assertEquals("Stock.set() should not add any parts to the stock!", -1, stock.get(testPart));
        assertFalse("Stock.set() should return false if the count argument has a negative value!", stock.set(part, -1));
        assertEquals("Stock.set() should not modify the number of parts if the count argument was invalid!", 15,
                stock.get(part));
        assertTrue("Stock.set() should return true if the number of parts within the stock has been updated!",
                stock.set(part, 5));
        assertEquals("Stock.set() should set the number of parts correctly!", 5, stock.get(part));
    }

    @Test
    public void testDeleteNullArgument() {
        try {
            stock.delete(null, 10);
            fail("Stock.delete() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testDelete() {
        assertFalse("Stock.delete() should return false if the part is not in the stock!",
                stock.delete(Factory.create(PartType.RESOURCE, "1", "A Resource Component"), 1));
        assertFalse("Stock.delete() should return false if the count argument is negative!", stock.delete(part, -1));
        assertEquals("Stock.delete() should not remove any parts from the stock if the count argument is invalid!",
                15, stock.get(part));
        assertFalse(
                "Stock.delete() should return false if the count argument is greater than the number of parts within the stock!",
                stock.delete(part, 20));
        assertEquals("Stock.delete() should not remove any parts from the stock if the count argument is invalid!",
                15, stock.get(part));
        assertTrue("Stock.delete() should return true if parts were deleted successfully!", stock.delete(part, 4));
        assertEquals("Stock.delete() should actually remove parts from the stock!", 11, stock.get(part));
    }
}
