import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * A simple Socket client that sends text messages to a server.
 * This class contains a main method that allows a user to input information about a running server to connect to.
 * @author Erik Macik, Spring 2022
 */
public class MessageClient {
    
    // STEP 3

    /** 
     * The underlying Java Socket for this message client.
     * This socket connects directly to the server.
     */
    private Socket socket;

    /**
     * The data output stream our message client will write messages to.
     * This output stream is obtained from the Java Socket once it is connected to the server.
     */
    private DataOutputStream outputStream;

    /**
     * The full name of the user that is using this message client.
     */
    private String name;
    
    /**
     * Creates a new client and connects to the server with the provided network information.
     * This constructor must be called AFTER the server has been started.
     * @param address The IP address of the machine running the server.
     * @param port The port the server is listening on.
     * @throws IOException This exception is thrown if the client fails to connect to the server.
     */
    public MessageClient(String address, int port) throws IOException {
        // STEP 4.A

        this.socket = new Socket(address, port);
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Set the name of the user that is using this message client.
     * A message will be sent to the server confirming that this client is connected.
     * @param name The full name of the user that is using this client.
     * @throws IOException This exception is thrown if the client fails to send the user's name to the server.
     */
    public void setClientName(String name) throws IOException {
        // STEP 4.B

        this.name = name;
        outputStream.writeUTF(this.name);
    }

    /**
     * This method continually asks the user for a text message and sends it to the server.
     * @throws IOException This exception is thrown if the client fails to connect to the server.
     */
    public void startClient() throws IOException {
        // STEP 4.C

        Scanner scnr = new Scanner(System.in);
        String line = "";

        while (!line.equals("DONE")) {
            System.out.print("\n > ");
            line = scnr.nextLine();
            
            System.out.print("Sending message... ");
            outputStream.writeUTF(line);
            System.out.println("Message sent!");
        }
    }

    /**
     * Allows the user to connect to a client by entering network information and send messages.
     * @param args Default main method arguments.
     */
    public static void main(String[] args) {
        // STEP 4.D

        Scanner scnr = new Scanner(System.in);

        System.out.println("\n----------");
        // User enters the server IP address.
        System.out.print("Enter the IP address of the server: ");
        String serverIP = scnr.nextLine();

        // User enters the server port number.
        System.out.print("Enter the port numnber of the server: ");
        int port = scnr.nextInt();
        scnr.nextLine();

        // User enters their first and last name.
        System.out.print("Enter your first and last name: ");
        String name = scnr.nextLine();
        System.out.println("----------");

        MessageClient messageClient = null;
        try {
            // Connect to the server with the given IP and port.
            messageClient = new MessageClient(serverIP, port);
            System.out.println("\n----------");
            System.out.println("Connected to server!");
            System.out.println("Enter messages (or 'DONE' to quit): ");
            System.out.println("----------");
            
            // Set the client name and send to the server.
            messageClient.setClientName(name);

            // Continuously send messages.
            messageClient.startClient();
        } catch (IOException e) {
            // Display stack trace if something goes wrong.
            e.printStackTrace();
        }
    }
}
