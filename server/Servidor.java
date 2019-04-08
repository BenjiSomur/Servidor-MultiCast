import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.lang.InterruptedException;

/**
 * @author BenjiSomur Server
 */

public class Servidor {

    private static ServerSocket servidor;
    private static List<Mensaje> mensajes = new ArrayList<>();
    private final static int SENDER_PORT = 1234;
    private static final String SENDER_IP = "224.0.0.1";

    public static void main(String[] args) {
        try {
            final String ip = args[0];
            final int port = Integer.parseInt(args[1]);
            int contador = 0;
            Sender distribuidor = new Sender(SENDER_IP, SENDER_PORT);
            InnerServidor servidorPrincipal = new InnerServidor(mensajes, distribuidor);
            servidorPrincipal.start();
            servidor = new ServerSocket(port);
            mensajes.add(new Mensaje("Hola a todos", 1));
            while (true) {
                Socket socket = servidor.accept();
                contador++;
                ServidorPersonal servidorPersonal = new ServidorPersonal(socket, mensajes, contador);
                servidorPersonal.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void run() {
        while (true) {
            if (!mensajes.isEmpty()) {
                String mensaje = mensajes.get(0).getContenido();
                int usuario = mensajes.get(0).getUsuario();
                String cadena = "Mensaje de usuario: " + usuario + "\n" + mensaje;
                System.out.println(cadena);
                mensajes.remove(0);
                try {
                    Thread.sleep(5000);
                    distribuidor.send(cadena);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(5000);
                    distribuidor.send(".");
                    System.out.println(".");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}
