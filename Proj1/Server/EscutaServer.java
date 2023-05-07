import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class EscutaServer implements Runnable{

    private Socket socket;
    private ArrayList<String> buffer;
    private DataInputStream in;

    public EscutaServer(Socket socket,ArrayList<String> buffer) {
        super();
        this.socket = socket;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            
            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    buffer.add(line);
                    System.out.println(line);

                } catch(IOException i) {
                    System.out.println(i);
                    break;
                }
            }
            in.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
