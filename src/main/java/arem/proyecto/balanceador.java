package arem.proyecto;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class balanceador {

    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {        

        ServerSocket serverSocket = null;
        Integer port;
        try {
            if (System.getenv("PORT") != null) {
                port = Integer.parseInt(System.getenv("PORT"));
            } else {
                port = 4567;
            }
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4567");
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println();
                clientSocket = serverSocket.accept();
                AppServer sh = new AppServer(clientSocket);
                pool.submit(sh);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
    }
    
}