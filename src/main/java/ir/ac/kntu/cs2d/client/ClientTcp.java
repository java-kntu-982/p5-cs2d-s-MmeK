package ir.ac.kntu.cs2d.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.kntu.cs2d.JsonPacket;
import ir.ac.kntu.cs2d.Level;
import ir.ac.kntu.cs2d.TeamsEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTcp {
    private static final int GET_ID = 1, GET_MAP = 2, SEND_PLAYER = 3, GET_TEAM=4;

    private final Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ObjectMapper objectMapper;

    public ClientTcp(Socket socket) {
        this.socket = socket;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public long getId() {
        try {
            JsonPacket jP = new JsonPacket(GET_ID);
            String data = objectMapper.writeValueAsString(jP);
            oos.writeObject(data);
            oos.flush();
            return ois.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Level getLevel() {
        try {
            JsonPacket jP = new JsonPacket(GET_MAP);
            String data = objectMapper.writeValueAsString(jP);
            oos.writeObject(data);
            oos.flush();
            Level level = ((JsonPacket) ois.readObject()).getLevel();
            return level;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public TeamsEnum getTeam(){
        try {
            JsonPacket jP = new JsonPacket(GET_TEAM);
            String data = objectMapper.writeValueAsString(jP);
            oos.writeObject(data);
            oos.flush();
            TeamsEnum team = ((JsonPacket) ois.readObject()).getTeam();
            return team;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return TeamsEnum.Terrorist;
    }

}
