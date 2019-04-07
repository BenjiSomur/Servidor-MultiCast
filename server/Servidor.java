import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BenjiSomur Server
 */

public class Servidor {

    private static ServerSocket servidor;
    private static List<String> mensaje = new ArrayList<>();

    public static void main(String[] args) {
        try {
            final String ip = args[0];
            final int port = Integer.parseInt(args[1]);
            Sender distribuidor = new Sender(ip, port);
            servidor = new ServerSocket(port);
            while (true) {
                Socket socket = servidor.accept();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

/**
 * InnerServidor
 * 
 * @author BenjiSomur
 */
class InnerServidor extends Thread {
    private List<Mensaje> mensajes;
    private Sender distribuidor;

    public InnerServidor(List<Mensaje> mensajes, Sender distribuidor) {
        this.mensajes = mensajes;
        this.distribuidor = distribuidor;
    }

    // TODO Falta el mecanismo para que cada 5 s egundos envíe menmsajes
    public void run() {
        while (true) {

        }
    }
}
