import java.net.*;

public class Client {
    private Socket socket;

    public void inicializa() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");

            EnviaClient envia = new EnviaClient(socket);  
            EscutaClient escuta = new EscutaClient(socket); 
            Thread threadEnvia = new Thread(envia);
            Thread threadEscuta = new Thread(escuta);
            
            threadEnvia.start();
            threadEscuta.start();
            threadEnvia.join();
            threadEscuta.join();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        Client client = new  Client();
        client.inicializa();
    }
}