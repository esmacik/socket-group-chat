import java.net.*;
import java.io.*;

/**
 * A thread responsible for receiving and displaying (echoing) messages from a single client.
 * This class extends Runnable, allowing it the be treated as a thread.
 * @author Erik Macik, Spring 2022
 */
public class EchoThread implements Runnable {

    /**
     * The underlying Java Socket for this message server.
     * This socket connects direstly to the client.
     */
    Socket socket;

    /**
     * The data input stream our message client will read messages from.
     * This input stream is obtained from the Java Socket once it is connected to the client.
     */
    DataInputStream inputStream;

    /**
     * The full name of the client that the server is reading messages from.
     */
    String name;

    /**
     * Creates a new Runnable with the given socket.
     * @param socket The socket connected to the client.
     * @throws IOException This exception is thrown if we are unable to get the input stream from the clieint.
     */
    public EchoThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
    }

    /**
     * Receives the name of the client as the first message.
     * @throws IOException This exception is thrown if the server is unable to read the client name.
     */
    public void receiveClientName() throws IOException {
        this.name = inputStream.readUTF();
    }

    /**
     * Run method that executes once the thread is started.
     * Implemented from the Runnable interface.
     */
    @Override
    public void run() {

        // Receive the client name as the first message.
        System.out.println("\n----------");
        try {
            receiveClientName();
            System.out.println("New client connected: " + this.name);
        } catch (IOException e) {
            System.out.println("\nClient connected, but unable to receive client name.");
            return;
        }
        System.out.println("----------");

        // Read and display messages until the client sends "DONE".
        String line = "";
        while (!line.equals("DONE")) {
            try {
                line = inputStream.readUTF();
            } catch (IOException e) {
                line = "DONE";
            }
            System.out.println("\n" + this.name + ": " + line);
        }

        // Display the name of the client once they disconnect.
        System.out.println("\n----------");
        System.out.println(this.name + " has disconnected.");
        System.out.println("----------");
        return;
    }
}
