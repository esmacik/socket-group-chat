import java.io.IOException;
import java.net.*;

/**
 * A class responsible for starting a multithreaded message server.
 * @author Erik Macik, Spring 2022
 */
public class MessageServer {

    /**
     * The entry point for the multithreaded message server.
     * This method displays the IP address of the host machine and the port
     * the server is listening on for incoming messages.
     * @param args Default command line arguments.
     */
    public static void main(String[] args) {

        String ip = "";

        // A random port that seems to be reliably open on my machine.
        int port = 1978;

        // Fancy code to get the server machine's IP address.
        // Don't worry too much about this.
        // Source: https://stackoverflow.com/a/38342964
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            socket.close();
        } catch (IOException ioException) {
            System.out.println("Unable to get the IP address of this machine.");
            return;
        }

        // Display the IP address and port for clients to connect.
        System.out.println("\n----------");
        System.out.println("Server IP Address: " + ip);
        System.out.println("Server listening on port: " + port);
        System.out.println("----------");

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            // Connect to new clients forever.
            while (true) {

                // Wait for a client to connect.
                Socket socket = serverSocket.accept();

                // Create a new thread with the socket connection.
                Thread clientThread = new Thread(new EchoThread(socket));

                // Execute the client thread's "run" method.
                clientThread.start();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
