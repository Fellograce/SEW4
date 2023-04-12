package serverClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    public static void main(String[] args) {
        Socket clientSocket = null;
        ExecutorService executorService = Executors.newCachedThreadPool();
        String[] asdf = new String[5];
        ReentrantLock lock = new ReentrantLock();

        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            while (true) {
                clientSocket = serverSocket.accept();
                executorService.execute(new ClientHandler(clientSocket, asdf, lock));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
