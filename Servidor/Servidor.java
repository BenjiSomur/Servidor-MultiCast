import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author BenjiSomur Servidor
 */
public class Servidor {

    private static final int PORT = 5000;
    private static DatagramPacket entrada, salida;
    private static DatagramSocket socketComunicacion;
    private static byte[] buffer;

    public static void main(String[] args) {
        run();
    }

    private static void run() {

    }

}