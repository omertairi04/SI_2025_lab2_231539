import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SILab2Test {

    // ===== Every Statement Testing =====
    @Test
    public void testAllItemsNull() {
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
    }

    @Test
    public void testItemNameNull() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(null, 1, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "1234567890123456"));
    }

    @Test
    public void testItemNameEmpty() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("", 1, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "1234567890123456"));
    }

    @Test
    public void testPriceGreaterThan300() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 1, 350, 0));
        assertEquals(320, SILab2.checkCart(items, "1234567890123456")); // 350*1 - 30
    }

    @Test
    public void testDiscountGreaterThan0() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 1, 100, 0.1));
        assertEquals(60, SILab2.checkCart(items, "1234567890123456")); // 100*0.9 - 30
    }

    @Test
    public void testQuantityGreaterThan10() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 15, 100, 0));
        assertEquals(1470, SILab2.checkCart(items, "1234567890123456")); // 100*15 - 30
    }

    @Test
    public void testInvalidCardNumberLength() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 1, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "1234"));
    }

    @Test
    public void testInvalidCardNumberCharacters() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 1, 100, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "123456789012345X"));
    }

    // ===== Multiple Condition Testing =====
    @Test
    public void testMultipleCondition_TFF() { // T F F
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 5, 350, 0));
        assertEquals(1720, SILab2.checkCart(items, "1234567890123456")); // 350*5 - 30
    }

    @Test
    public void testMultipleCondition_FTF() { // F T F
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 5, 100, 0.1));
        assertEquals(420, SILab2.checkCart(items, "1234567890123456")); // (100*0.9)*5 - 30
    }

    @Test
    public void testMultipleCondition_FFT() { // F F T
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 15, 100, 0));
        assertEquals(1470, SILab2.checkCart(items, "1234567890123456")); // 100*15 - 30
    }

    @Test
    public void testMultipleCondition_FFF() { // F F F
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", 5, 100, 0));
        assertEquals(500, SILab2.checkCart(items, "1234567890123456")); // 100*5 (no penalty)
    }
}