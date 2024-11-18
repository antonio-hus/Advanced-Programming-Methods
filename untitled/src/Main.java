import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Random random = new Random();

            int sr = 1;
            int er = (int) Math.pow(2, 5) - 1;
            int finished = 0;
            int stepCount = 0;

            byte[] buffer = new byte[1024];
            int length = in.read(buffer);
            if (length > 0) {
                System.out.println(new String(buffer, 0, length, "ASCII"));
            }

            while (finished == 0) {
                int myNum;

                if ( sr >= er ) {
                    sr = 1;
                    er = (int) Math.pow(2, 5) - 1;
                }
                myNum = random.nextInt(er - sr + 1);

                // Send the number to the server
                out.write(ByteBuffer.allocate(4).putInt(myNum).array());
                out.flush();

                // Receive the server's response
                int response = in.read();
                stepCount++;

                if (response == -1) {
                    System.out.println("Connection closed by server");
                    break;
                }

                char answer = (char) response;
                System.out.println("Sent " + myNum + " Answer " + answer);

                boolean chaos = random.nextBoolean();
                switch (answer) {
                    case 'H':
                        if(!chaos)
                            sr = myNum;
                        break;
                    case 'S':
                        if(!chaos)
                            er = myNum;
                        break;
                    case 'G':
                        finished = 1;
                        break;
                    case 'L':
                        finished = -1;
                        break;
                }

                Thread.sleep(250);
            }

            if (finished != 0) {
                if (finished == 1) {
                    System.out.println("I am the winner with " + buffer[0] + " in " + stepCount + " steps");
                } else {
                    System.out.println("I lost !!!");
                }
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
