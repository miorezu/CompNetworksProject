import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {

    private ServerSocket serverSocket = null;
    private final int serverPort;

    public Server(int port) {
        this.serverPort = port;
    }

    @Override
    public void run() {

        try {
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started");
            while (true) {
                System.out.println("Enter 0 to end the server or another num to continue: ");
                Scanner inScanner = new Scanner(System.in);
                if (inScanner.nextInt() == 0) {
                    return;
                }

                System.out.println("New Client Waiting...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client: " + clientSocket);

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                String classFile = (String) in.readObject();
                classFile = classFile.replaceFirst("////", "Server_CN");
                byte[] bytes = (byte[]) in.readObject();
                FileOutputStream fos = new FileOutputStream(classFile);
                fos.write(bytes);

                Executable ex = (Executable) in.readObject();
                double startTime = System.nanoTime();
                Object output = ex.execute();
                double endTime = System.nanoTime();
                double completionTime = endTime - startTime;

                ResultImplementation result = new ResultImplementation(output, completionTime);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                classFile = "out/production/Server_CN/ResultImplementation.class";
                out.writeObject(classFile);
                FileInputStream fis = new FileInputStream(classFile);
                byte[] bytes1 = new byte[fis.available()];

                fis.read(bytes1);
                out.writeObject(bytes1);
                out.writeObject(result);
                fis.close();
                fos.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Server closed.");
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(7891);
        server.start();
    }
}