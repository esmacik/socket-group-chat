import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * A simple Socket client that sends text messages to a server.
 * This class contains a main method that allows a user to input information about a running server to connect to.'
 * Complete this version by following the lecture slides.
 * @author Erik Macik, Spring 2022
 */
public class MessageClientForStudents {

    // STEP 3

    /** 
     * The underlying Java Socket for this message client.
     * This socket connects directly to the server.
     */


    /**
     * The data output stream our message client will write messages to.
     * This output stream is obtained from the Java Socket once it is connected to the server.
     */


    /**
     * The full name of the user that is using this message client.
     */

    
    /**
     * Creates a new client and connects to the server with the provided network information.
     * This constructor must be called AFTER the server has been started.
     * @param address The IP address of the machine running the server.
     * @param port The port the server is listening on.
     * @throws IOException This exception is thrown if the client fails to connect to the server.
     */
    public MessageClientForStudents(String address, int port) throws IOException {
        // STEP 4.A
    }

    /**
     * Set the name of the user that is using this message client.
     * A message will be sent to the server confirming that this client is connected.
     * @param name The full name of the user that is using this client.
     * @throws IOException This exception is thrown if the client fails to send the user's name to the server.
     */
    public void setClientName(String name) throws IOException {
        // STEP 4.B
    }

    /**
     * This method continually asks the user for a text message and sends it to the server.
     * @throws IOException This exception is thrown if the client fails to connect to the server.
     */
    public void startClient() throws IOException {
        // STEP 4.C
    }

    /**
     * Allows the user to connect to a client by entering network information and send messages.
     * @param args Default main method arguments.
     */
    public static void main(String[] args) {
        // STEP 4.D
    }
}
