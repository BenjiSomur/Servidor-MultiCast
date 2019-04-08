import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * @author BenjiSomur
 * @version 1.0
 */
public class Sender {
    private String group;
    private int port;
    private MulticastSocket ms;

    public Sender(String group, int port) throws SocketException, IOException {
        this.group = group;
        this.port = port;
        ms = new MulticastSocket(port);
    }

    public void send(String mensajeEnviar) throws IOException {
        byte[] mensaje = mensajeEnviar.getBytes();
        DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(group), port);
        ms.send(paquete);
    }

    public void close() {
        ms.close();
    }
}