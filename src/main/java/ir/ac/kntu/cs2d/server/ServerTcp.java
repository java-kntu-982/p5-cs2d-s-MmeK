package ir.ac.kntu.cs2d.server;

import ir.ac.kntu.cs2d.JsonPacket;
import ir.ac.kntu.cs2d.TeamsEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerTcp implements Runnable {
    private static final int GET_ID = 1, GET_MAP = 2, SEND_PLAYER = 3, GET_TEAM=4;

    private ServerMain serverMain;
    private TeamsEnum team;
    private long id;
    Socket socket;

    public ServerTcp(Socket sc, ServerMain server) {
        this.socket = sc;
        this.serverMain=server;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            while(true) {
                int action = ((JsonPacket) ois.readObject()).getAction();
                switch(action) {
                    case GET_ID:
                        id=serverMain.getId().longValue();
                        oos.writeLong(id);
                        break;
                    case GET_MAP:
                        oos.writeObject(serverMain.getMap());
                        break;
                    case SEND_PLAYER:
                        this.main.includeCharacter(var3.characterData);
                        break;
                    case GET_TEAM:
                        team = serverMain.getTeam();
                        oos.writeObject(team);
                        break;
                }

                oos.flush();
            }


        } catch (IOException | ClassNotFoundException exception) {

        }
    }
}
