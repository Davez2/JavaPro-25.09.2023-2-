package org.example.server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(8081);
        server.run();
    }
}
