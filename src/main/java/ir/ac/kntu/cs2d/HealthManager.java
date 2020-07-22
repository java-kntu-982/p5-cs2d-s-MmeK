package ir.ac.kntu.cs2d;

public class HealthManager {
    private final int maxHealth;
    private int health;

    private Player player;

    public HealthManager(int maxHealth, Player player) {
        this.maxHealth = maxHealth;
        this.player = player;
        this.health = maxHealth;
    }

    public int reduceHealth(int damage) {
        health = Math.max((health - damage), 0);
        if (health <= 0)
            die();
        return health;
    }

    private void die() {
        System.out.println("ded");
        player.die();
    }



}
