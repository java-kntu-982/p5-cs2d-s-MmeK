package ir.ac.kntu.cs2d;

import com.fasterxml.jackson.annotation.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonPacket {

    private int action;
    private Level level;
    private Player player;
    private long id;
    private TeamsEnum team;

    @JsonCreator
    public JsonPacket(@JsonProperty("action") int action,@JsonProperty("level") Level level,
                      @JsonProperty("player") Player player,@JsonProperty("id") long id,@JsonProperty("team") TeamsEnum team) {
        this.action = action;
        this.level = level;
        this.player = player;
        this.id = id;
        this.team = team;
    }

    public JsonPacket(int action){
        this.action=action;
    }

    public TeamsEnum getTeam() {
        return team;
    }

    @JsonSetter
    public void setTeam(TeamsEnum team) {
        this.team = team;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @JsonGetter
    public Level getLevel() {
        return level;
    }

    @JsonSetter
    public void setLevel(Level level) {
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JsonPacket{" +
                "action=" + action +
                ", level=" + level +
                ", player=" + player +
                ", id=" + id +
                ", team=" + team +
                '}';
    }
}
