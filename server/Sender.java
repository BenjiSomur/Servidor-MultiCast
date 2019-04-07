import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author BenjiSomur
 * @version 1.0
 */
public class Sender {
    private DatagramSocket serverSocket;
    private String group;
    private int port;

    public Sender(String group, int port) throws SocketException, IOException {
        this.group = group;
        this.port = port;
        serverSocket = new DatagramSocket();
    }

    public void send(String mensajeEnviar) throws IOException {
        byte[] mensaje = mensajeEnviar.getBytes();
        DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(group), port);
        serverSocket.send(paquete);
    }

    public void close() {
        serverSocket.close();
    }
}