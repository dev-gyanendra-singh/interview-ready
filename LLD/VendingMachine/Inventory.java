package LLD.VendingMachine;

// Inventory.java
import java.util.*;

public class Inventory {
    private Map<Item, Integer> itemStock = new HashMap<>();
    public void addItem(Item item, int count) {
        itemStock.put(item,
                itemStock.getOrDefault(item, 0) + count);
    }
    public boolean hasItem(Item item) {
        return
                itemStock.getOrDefault(item, 0) > 0;
    }
    public void dispenseItem(Item item) {
        if (hasItem(item)) {
            itemStock.put(item, itemStock.get(item) - 1);
        } else {
            throw new RuntimeException("Item out of stock");
        }
    }

    public Set<Item> getAllItems() {
        return itemStock.keySet();
    }
}

