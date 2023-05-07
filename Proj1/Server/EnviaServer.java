import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class EnviaServer implements Runnable {
    private Socket socket;
    private ArrayList<String> buffer;
    private DataOutputStream out;

    public EnviaServer(Socket socket,ArrayList<String> buffer) {
        super();
        this.socket = socket;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException i) {
            System.out.println(i);
        }
            
        int k=1;
        int j=0;
        while(true) {
            try {
                if(buffer.size() == (j+1)) {   
                    if(buffer.get(j).equals("Over"))
                        break;
                    out.writeUTF(buffer.get(j));
                    j++;
                } else {
                    out.writeUTF(Integer.toString(k));
                    k++;
                }
                Thread.sleep(1000);

            } catch (Exception i) {
                System.out.println(i);
                break;
            }
        }

        try {
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
