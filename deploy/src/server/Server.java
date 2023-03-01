package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        Socket clientSocket = null;
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            while (true) {
                System.out.println("Server: Waiting for client to connect ...");
                clientSocket = serverSocket.accept();
                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Server: Communication Error!");
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                    System.out.println("Server: Client Socket closed successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}