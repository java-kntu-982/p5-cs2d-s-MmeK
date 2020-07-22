package ir.ac.kntu.cs2d.Models;

import ir.ac.kntu.cs2d.Collider2D;
import ir.ac.kntu.cs2d.InventoryItem;
import ir.ac.kntu.cs2d.PhysicsManager;
import ir.ac.kntu.cs2d.Player;
import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.media.AudioClip;

import java.io.Serializable;
import java.util.List;

public class Gun implements Serializable, InventoryItem {
    private PhysicsManager physicsManager;
    private String name;
    private int price, millisToReload, millisToFireAgain, damage, magazineCapacity;
    private int currentBullets;
    private AudioClip fireSound,reloadSound;
    private String playerLayer;

    private boolean canShoot = true, reloading = false;

    public Gun(String name, PhysicsManager physicsManager, int price, int millisToReload, int millisToFireAgain, int damage, int magazineCapacity,
               AudioClip fireSound, AudioClip reloadSound, String playerLayer) {
        this.name = name;
        this.physicsManager=physicsManager;
        this.price = price;
        this.millisToReload = millisToReload;
        this.millisToFireAgain = millisToFireAgain;
        this.damage = damage;
        this.magazineCapacity = magazineCapacity;
        this.fireSound = fireSound;
        this.reloadSound = reloadSound;
        this.playerLayer = playerLayer;
        this.currentBullets = magazineCapacity;
    }

    @Override
    public void onUsed(Vector2D playerCenter, Vector2D mousePos) {
        if (currentBullets <= 0) {
            if (!reloading)
                new Thread(() -> {
                    try {
                        reloading = true;
                        reloadSound.play();
                        Thread.sleep(millisToReload);
                        currentBullets = magazineCapacity;
                        reloading=false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            return;
        }
        currentBullets--;
        if (canShoot) {
            fireSound.play();
            Vector2D extendedMousePos = Vector2D.extendVector(playerCenter, mousePos, 100);

            canShoot = false;
            new Thread(() -> {
                try {
                    Thread.sleep(millisToFireAgain);
                    canShoot = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    canShoot = true;
                }
            }).start();
            new Thread(() -> {
                List<Collider2D> hitColliders = physicsManager.lineCast(playerCenter.x,
                        playerCenter.y, extendedMousePos.x, extendedMousePos.y,
                        playerLayer);
                for (Collider2D col : hitColliders) {
                    System.out.println(col.getGameObject().getName());
                    if (col.getLayer().equals("enemy"))
                        ((Player)col.getGameObject()).getHealthManager().reduceHealth(damage);
                }
            }).start();
        }
    }
}
