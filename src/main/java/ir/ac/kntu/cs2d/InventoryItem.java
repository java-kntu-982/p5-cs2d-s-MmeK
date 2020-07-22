package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;

public interface InventoryItem {
    void onUsed(Vector2D playerCenter, Vector2D mousePos);
}
