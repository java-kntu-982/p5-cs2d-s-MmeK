package ir.ac.kntu.cs2d.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTcp implements Runnable {

    Socket socket;

    public ServerTcp(Socket sc) {
        this.socket = sc;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {



        } catch (IOException exception) {

        }
    }
}
