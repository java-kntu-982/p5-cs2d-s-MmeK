package ir.ac.kntu.cs2d.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(65000)) {
            Socket clientSocket;
            while((clientSocket = serverSocket.accept())!=null){
                new Thread(new ServerTcp(clientSocket)).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private ServerMain() {
    }
}
