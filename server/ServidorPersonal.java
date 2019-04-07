import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

/**
 * ServidorPersonal
 */
public class ServidorPersonal extends Thread {
    private Socket socket;
    private String mensaje;
    private List<Mensaje> mensajes;
    private int usuario;

    public ServidorPersonal(Socket socket, List<Mensaje> mensajes, int usuario) {
        this.socket = socket;
        this.mensajes = mensajes;
        this.usuario = usuario;
    }

    public void run() {
        while (true) {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                mensaje = input.readLine();
                Mensaje mensajeAux = new Mensaje(mensaje, usuario);
                this.mensajes.add(mensajeAux);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}