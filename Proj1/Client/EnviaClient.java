import java.io.*;
import java.net.*;

public class EnviaClient implements Runnable{
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out = null;

    public EnviaClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }

        String line = "";
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                
            } catch (IOException i) {
                System.out.println(i);
                break;
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
