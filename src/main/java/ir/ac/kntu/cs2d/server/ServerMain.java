package ir.ac.kntu.cs2d.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.kntu.cs2d.Level;
import ir.ac.kntu.cs2d.ResourcesLoader;
import ir.ac.kntu.cs2d.TeamsEnum;
import ir.ac.kntu.cs2d.Util.ResourceHelper;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.concurrent.atomic.LongAdder;

public class ServerMain {
    private static final int PORT=54321;
    private final LongAdder id= new LongAdder();
    private ResourcesLoader resourcesLoader;
    public static void main(String[] args) {
        new ServerMain();
    }

    private ServerMain() {
        this.resourcesLoader=new ResourcesLoader();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket;
            while((clientSocket = serverSocket.accept())!=null){
                new Thread(new ServerTcp(clientSocket,this)).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public LongAdder getId() {
        id.increment();
        return id;
    }
    public Level getMap(){
        ObjectMapper levelMapper = new ObjectMapper();
        try {
            Level level= levelMapper.readValue(new File(Paths.get(ResourceHelper.MAPS+"de_dust.json").toUri()),
                    Level.class);
            return level;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public TeamsEnum getTeam(){
        return TeamsEnum.values()[id.intValue()%TeamsEnum.values().length];
    }
}
