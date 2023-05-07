import java.net.*;
import java.util.concurrent.Semaphore;

public class Server {

    private Socket socket;
    private ServerSocket server;
    private Semaphore semaforo = new Semaphore(3);

    public void IniciaServerliza() {
        try {
            server = new ServerSocket(5000);
            System.out.println("Server started");

            while(true) {
                semaforo.acquire();

                System.out.println("Waiting for a client ...");
                socket = server.accept();
                System.out.println("Client accepted");
        
                IniciaServer inicia= new IniciaServer(socket,semaforo);
                new Thread(inicia).start();
            }

        } catch(Exception i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new  Server();
        server.IniciaServerliza();
    }
}