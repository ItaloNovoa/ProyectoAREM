package arem.proyecto;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

import javax.imageio.ImageIO;

public class AppServer {

    private static HashMap<String, UrlHandler> Handler = new HashMap<String, UrlHandler>();

    public static void appendHash(String s, Handler uh, Method m) {
        Handler.put("/apps/" + m.getAnnotation(Web.class).value(), new UrlHandler(m));
    }

    public static void main(String[] args) throws IOException {
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
        client(serverSocket);
    }

    public static void client(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = null;
        while (true) {
            try {
                System.out.println();
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            String encabezado = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";
            while ((inputLine = in.readLine()) != null) {
                // lectura y escritura entre servidores
                System.out.println("Received: " + inputLine);
                int index = inputLine.indexOf("/apps/");
                String resource = "", urlInputLine = "";
                int i = -1;
                if (index != -1) {
                    for (i = index; i < inputLine.length() && inputLine.charAt(i) != ' '; i++) {
                        resource += inputLine.charAt(i);
                    }
                } else {
                    i = inputLine.indexOf('/') + 1;
                }
                if (inputLine.contains("/apps/")) {
                    try {
                        out.println(encabezado);
                        out.println(Handler.get("prueba1").procesar());

                    } catch (Exception e) {
                        error(clientSocket,out);
                    }
                } else if (inputLine.contains(".html")) {
                    while (!urlInputLine.endsWith(".html") && i < inputLine.length()) {
                        urlInputLine += (inputLine.charAt(i++));
                    }
                    String urlDirectoryServer = System.getProperty("user.dir") + "/ejemplo/" + urlInputLine;
                    System.out.println(urlDirectoryServer);
                    try {
                        BufferedReader readerFile = new BufferedReader(new FileReader(urlDirectoryServer));
                        out.println(encabezado);
                        while (readerFile.ready()) {
                            out.println(readerFile.readLine());
                        }
                    } catch (FileNotFoundException e) {
                        error(clientSocket,out);
                    }
                } else if (inputLine.contains(".png")) {
                    try {
                        while (!urlInputLine.endsWith(".png") && i < inputLine.length()) {
                            urlInputLine += (inputLine.charAt(i++));
                        }
                        BufferedImage imagen = ImageIO
                                .read(new File(System.getProperty("user.dir") + "/ejemplo/" + urlInputLine));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(imagen, "png", baos);
                        byte[] imageBy = baos.toByteArray();
                        DataOutputStream outImg = new DataOutputStream(clientSocket.getOutputStream());
                        outImg.writeBytes("HTTP/1.1 200 OK \r\n");
                        outImg.writeBytes("Content-Type: image/png\r\n");
                        outImg.writeBytes("Content-Length: " + imageBy.length);
                        outImg.writeBytes("\r\n\r\n");
                        outImg.write(imageBy);
                        outImg.close();
                        out.println(outImg.toString());
                    } catch (Exception e) {
                        error(clientSocket,out);
                    }                    

                } else if (inputLine.contains(".jpeg")) {
                    try {
                        while (!urlInputLine.endsWith(".jpeg") && i < inputLine.length()) {
                            urlInputLine += (inputLine.charAt(i++));
                        }
                        BufferedImage imagen = ImageIO
                                .read(new File(System.getProperty("user.dir") + "/ejemplo/" + urlInputLine));
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(imagen, "jpeg", baos);
                        byte[] imageBy = baos.toByteArray();
                        DataOutputStream outImg = new DataOutputStream(clientSocket.getOutputStream());
                        outImg.writeBytes("HTTP/1.1 200 OK \r\n");
                        outImg.writeBytes("Content-Type: image/jpeg\r\n");
                        outImg.writeBytes("Content-Length: " + imageBy.length);
                        outImg.writeBytes("\r\n\r\n");
                        outImg.write(imageBy);
                        outImg.close();
                        out.println(outImg.toString());
                        out.close();
                    } catch (Exception e) {
                        error(clientSocket,out);
                    }                    
                }
                else if (inputLine.contains(".ico")){
                    try {
                        List<BufferedImage> images = ICODecoder.read(new File(System.getProperty("user.dir") + "images.ico"));
                        out.println("HTTP/1.1 200 OK\r");
                        out.println("Content-Type: image/vnd.microsoft.icon\r");
                        out.println("\r");
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ICOEncoder.write(images.get(0), baos);
                    } catch (Exception e) {
                        error(clientSocket, out);
                    }
                }
                if (!in.ready()) {
                    break;
                }

            }
            out.close();
            in.close();
            clientSocket.close();
        }
    }

    public static void error(Socket clientSocket, PrintWriter out) throws IOException {
        BufferedImage github = ImageIO.read(new File(System.getProperty("user.dir") + "/ejemplo/" +"error-2.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(github, "png", baos);
        byte[] imageBy = baos.toByteArray();
        DataOutputStream outImg = new DataOutputStream(clientSocket.getOutputStream());
        outImg.writeBytes("HTTP/1.1 200 OK \r\n");
        outImg.writeBytes("Content-Type: image/png\r\n");
        outImg.writeBytes("Content-Length: " + imageBy.length);
        outImg.writeBytes("\r\n\r\n");
        outImg.write(imageBy);
        outImg.close();
        out.println(outImg.toString());
    }
}
