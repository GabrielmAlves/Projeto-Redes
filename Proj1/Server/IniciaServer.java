import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class IniciaServer implements Runnable {

    private Semaphore semaforo; 
    private Socket socket;

    public IniciaServer(Socket socket, Semaphore semaforo) {
        this.socket = socket;
        this.semaforo = semaforo;
    }

    public void run() {
        try {
            ArrayList<String> buffer = new ArrayList<>();
            EscutaServer escuta = new EscutaServer(socket,buffer);
            EnviaServer envia = new EnviaServer(socket,buffer);            
            Thread threadEscuta = new Thread(escuta);
            Thread threadEnvia = new Thread(envia);

            threadEscuta.start();
            threadEnvia.start();
            threadEscuta.join();
            threadEnvia.join();

            System.out.println("Closing connection");
            socket.close();
            semaforo.release();
            
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
