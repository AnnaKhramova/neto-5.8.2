package ru.akhramova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer PORT = 8082;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 ) {
                System.out.println("New connection accepted");
                out.println("Write your name");
                final String name = in.readLine();
                out.println("Are you child? (yes/no)");
                final String isChild = in.readLine();
                if (isChild.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                }
                if (isChild.equals("no")) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
