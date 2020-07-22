package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Models.Gun;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<InventoryItem> items = new ArrayList<>();
    private int currentitem = 0;

    public InventoryItem switchToItem(int input) throws ArrayIndexOutOfBoundsException {
        if (input > items.size() || input < 1)
            throw new ArrayIndexOutOfBoundsException("Item does not exist");
        currentitem=input-1;
        return getCurrentItem();
    }

    public InventoryItem getCurrentItem() {
        return items.get(currentitem);
    }

    public InventoryItem switchItem(int i) {
        currentitem = (currentitem + i) < 0 ? currentitem = items.size() - 1 : (currentitem + i) % items.size();
        return getCurrentItem();
    }
    public void addItem(InventoryItem inventoryItem){
        items.add(inventoryItem);
    }
}
