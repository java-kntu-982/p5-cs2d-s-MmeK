package ir.ac.kntu.cs2d;

public class JsonPacket {

    private int action;
    private Level level;
    private Player player;
    private long id;
    private TeamsEnum team;

    public JsonPacket(int action){
        this.action=action;
    }

    public TeamsEnum getTeam() {
        return team;
    }

    public void setTeam(TeamsEnum team) {
        this.team = team;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Level getLevel() {
        return level;
    }

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
}
