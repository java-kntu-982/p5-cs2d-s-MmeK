package ir.ac.kntu.cs2d;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class Level {
    private final String mapName;
    private final List<Rectangle> walls, obstacles;
    private final Rectangle tSpawn, ctSpawn, bombSite;
    private final double width, height;

    @JsonCreator
    public Level(@JsonProperty("map_name") String mapName, @JsonProperty("width") double width,
                 @JsonProperty("height") double height, @JsonProperty("walls") List<Rectangle> walls,
                 @JsonProperty("obstacles") List<Rectangle> obstacles, @JsonProperty("t_spawn") Rectangle tSpawn,
                 @JsonProperty("ct_spawn") Rectangle ctSpawn, @JsonProperty("bomb_site") Rectangle bombSite) {
        this.mapName = mapName;
        this.width=width;
        this.height=height;
        this.walls = walls;
        this.obstacles = obstacles;
        this.tSpawn = tSpawn;
        this.ctSpawn = ctSpawn;
        this.bombSite = bombSite;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @JsonGetter("map_name")
    public String getMapName() {
        return mapName;
    }

    @JsonGetter("walls")
    public List<Rectangle> getWalls() {
        return walls;
    }

    @JsonGetter("obstacles")
    public List<Rectangle> getObstacles() {
        return obstacles;
    }

    @JsonGetter("t_spawn")
    public Rectangle gettSpawn() {
        return tSpawn;
    }

    @JsonGetter("ct_spawn")
    public Rectangle getCtSpawn() {
        return ctSpawn;
    }

    @JsonGetter("bomb_site")
    public Rectangle getBombSite() {
        return bombSite;
    }

    @Override
    public String toString() {
        return "Level{" +
                "mapName='" + mapName + '\'' +
                ", walls=" + walls +
                ", obstacles=" + obstacles +
                ", tSpawn=" + tSpawn +
                ", ctSpawn=" + ctSpawn +
                ", bombSite=" + bombSite +
                '}';
    }


}
