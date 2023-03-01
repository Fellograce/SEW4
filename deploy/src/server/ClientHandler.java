package server;

import common.Request;
import common.Response;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 * Takes the communication between client and server Responsible for Input and Output-Streams
 */
public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        setClientSocket(socket);
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            Request request = (Request) in.readObject();
            System.out.println("Received a request!");
            System.out.println(request);

            Response response = new Response("Hello Client!");
            System.out.println("Sending response!");
            out.writeObject(response);
        } catch (IOException e) {
            System.out.println("Server: Communication Error!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
