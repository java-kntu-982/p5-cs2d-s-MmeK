package ir.ac.kntu.cs2d.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.kntu.cs2d.JsonPacket;
import ir.ac.kntu.cs2d.Level;
import ir.ac.kntu.cs2d.TeamsEnum;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTcp {
    private static final int GET_ID = 1, GET_MAP = 2, SEND_PLAYER = 3, GET_TEAM = 4;
    private static final String IP = "localhost";
    private static final int PORT = 54321;

    private final Socket socket;
    private PrintWriter oos;
    private BufferedReader ois;
    private ObjectMapper objectMapper;

    public ClientTcp() throws IOException {
        InetAddress ip = InetAddress.getByName(IP);
        Socket socket = new Socket(ip, PORT);
        this.socket = socket;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            oos = new PrintWriter(socket.getOutputStream(), true);
            ois = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    public void close() throws IOException {
        socket.setKeepAlive(false);
        socket.shutdownInput();
        socket.shutdownOutput();
        socket.close();
        System.out.println("Closing");
    }

    public long getId() {
        try {
            JsonPacket jP = new JsonPacket(GET_ID);
            String data = objectMapper.writeValueAsString(jP);
            oos.println(data);
            return Long.parseLong(ois.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Level getLevel() {
        try {
            JsonPacket jP = new JsonPacket(GET_MAP);
            String data = objectMapper.writeValueAsString(jP);
            oos.println(data);
            String levelS = ois.readLine();
            System.out.println(levelS);
            Level level = objectMapper.readValue(levelS,Level.class);
            return level;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TeamsEnum getTeam() {
        try {
            JsonPacket jP = new JsonPacket(GET_TEAM);
            String data = objectMapper.writeValueAsString(jP);
            oos.println(data);
//            oos.printLnObject(data);
            oos.flush();
            String teamS = ois.readLine();
            TeamsEnum team = teamS.equals("Counter_Terrorist") ? TeamsEnum.Counter_Terrorist : TeamsEnum.Terrorist;
            return team;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TeamsEnum.Terrorist;
    }

}
