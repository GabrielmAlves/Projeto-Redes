import java.io.*;
import java.net.*;

public class EscutaClient implements Runnable{
    private Socket socket;
    private DataInputStream in;

    public EscutaClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());

            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                    Thread.sleep(1000);

                } catch(Exception i) {
                    System.out.println(i);
                    break;
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();

        } catch(IOException i) {
            System.out.println(i);
        }
    }
}
