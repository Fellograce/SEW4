package client;

import common.Request;
import common.Response;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {
    public static void main(String[] args) {
        System.out.println("Client: Connecting to - localhost:2000");
        try (Socket serverSocket = new Socket("localhost", 2000);
             ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            Request req = new Request("Hello Server!");
            System.out.println("Sending request!");
            System.out.println(req);
            out.writeObject(req);

            Response response = (Response) in.readObject();
            System.out.println("Receiving response!");
            System.out.println(response);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Client: Kommunikationsfehler");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}