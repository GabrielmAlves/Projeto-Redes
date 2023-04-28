// A Java program for a Client
import java.io.*;
import java.net.*;
import java.lang.*;

public class Client 
{
    private static Runnable envia = new Runnable() {
        private Socket socket = null;
        private DataInputStream input = null;
        private DataOutputStream out = null;
        int flag = 0;


        public void run() {
            while(flag == 0) {
                try {
                    socket = new Socket("127.0.0.1", 5000);
                    System.out.println("Connected");
                    // takes input from terminal
                    input = new DataInputStream(System.in);
                    // sends output to the socket
                    out = new DataOutputStream(socket.getOutputStream());
                    flag = 1;
                }
                catch (UnknownHostException u) {
                    continue;
                    // System.out.println(u);
                }
                catch (IOException i) {
                    continue;
                    // System.out.println(i);
                }
            }
            

            // string to read message from input
            String line = "";
            
            // keep reading until "Over" is input
            while (!line.equals("Over")) {
                try {
                    line = input.readLine();
                    out.writeUTF(line);
                }
                catch (IOException i) {
                    System.out.println(i);
                    break;
                }
            }
            // close the connection
            try {
                input.close();
                out.close();
                socket.close();
            }catch (IOException i) {
                System.out.println(i);
            }
        
        }
    };

    private static Runnable escuta = new Runnable() {
        //initialize socket and input stream
        private Socket socket = null;
        private ServerSocket server = null;
        private DataInputStream in = null;

        public void run() {
            // starts server and waits for a connection
            try
            {
                server = new ServerSocket(3000);
                System.out.println("Server started");
                System.out.println("Waiting for a client ...");
                socket = server.accept();
                System.out.println("Client accepted");
                // takes input from the client socket
                in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
                String line = "";
                // reads message from client until "Over" is sent
                while (!line.equals("Over"))
                {
                    try
                    {
                        line = in.readUTF();
                        System.out.println(line);
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                        break;
                    }
                }
                System.out.println("Closing connection");
                // close connection
                socket.close();
                in.close();
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
    };
    
    public static void main(String args[])
    {
        new Thread(escuta).start();
        new Thread(envia).start();
        // Client client = new Client("127.0.0.1", 5000);
    }
}