import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "172.30.251.179";
        int port = 5555;
        String message;

        try (Socket socket = new Socket(serverAddress, port);
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            // Send message to server
            System.out.println("Please enter your message:");
            Scanner scanner = new Scanner(System.in);
            message = scanner.nextLine();
            writer.println(message);

            // Receive response from server
            char[] buffer = new char[128];
            int charsRead = reader.read(buffer);
            String response = new String(buffer, 0, charsRead);
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}