package ir.ac.kntu.cs2d;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HealthManager {
    @JsonIgnore
    private final int maxHealth;
    private int health;

    @JsonIgnore
    private Player player;

    public int getHealth() {
        return health;
    }

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
