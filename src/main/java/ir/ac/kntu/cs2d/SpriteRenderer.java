package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.image.Image;

public class SpriteRenderer {
    private Image[] sprites;


    public Image[] getSprites() {
        return sprites;
    }

    public SpriteRenderer(Image[] images) {
        this.sprites = images;
    }

    public Vector2D getSpriteSize() {
        return new Vector2D(sprites[0].getWidth(), sprites[0].getHeight());
    }

 }
