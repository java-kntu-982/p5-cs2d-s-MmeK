package ir.ac.kntu.cs2d.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.kntu.cs2d.JsonPacket;
import ir.ac.kntu.cs2d.Player;
import ir.ac.kntu.cs2d.TeamsEnum;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ServerTcp implements Runnable {
    private static final int GET_ID = 1, GET_MAP = 2, SEND_PLAYER = 3, GET_TEAM = 4;

    private ServerMain serverMain;
    private TeamsEnum team;
    private long id;
    private ObjectMapper objectMapper;
    Socket socket;

    public ServerTcp(Socket sc, ServerMain server) {
        this.socket = sc;
        System.out.println("Client Connected ip:" + sc.getInetAddress().toString() + " id= " + id);
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.serverMain = server;
    }

    @Override
    public void run() {
        try {
            PrintWriter oos = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader ois = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {
                String data = ois.readLine();
                System.out.println(data);
                JsonPacket jsonPacket = objectMapper.readValue(data, JsonPacket.class);
                int action = jsonPacket.getAction();
                switch (action) {
                    case GET_ID:
                        id = serverMain.getId().longValue();
                        oos.println(id);
                        break;
                    case GET_MAP:
                        data = objectMapper.writeValueAsString(serverMain.getMap());
                        oos.println(data);
                        break;
                    case SEND_PLAYER:
                        break;
                    case GET_TEAM:
                        team = serverMain.getTeam();
                        System.out.println(team);
                        data = objectMapper.writeValueAsString(team);
                        oos.println(data);
                        break;
                }
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
