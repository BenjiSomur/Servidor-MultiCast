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

    public ServidorPersonal(Socket socket, List<Mensaje> mensajes) {
        this.socket = socket;
        this.mensajes = mensajes;
    }

    public void run() {
        while (true) {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                mensaje = input.readLine();
                Mensaje mensajeAux = new Mensaje(mensaje);
                this.mensajes.add(mensajeAux);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}