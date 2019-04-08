
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private static final int PORT = 1234;
    private static Socket socket;
    private static MulticastSocket ms;
    private static String group;

    public static void main(String[] args) {
        group = args[0];
        try {
            ms = new MulticastSocket(PORT);
            ms.joinGroup(InetAddress.getByName(group));
        } catch (SocketException ex) {
            System.out.println("No se encontro la dirección");
            ex.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("No se encontro en HOST!");
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Ingrese su mensaje");
        run();
    }

    public static void run() {
        while (true) {
            try {
                byte buf[] = new byte[1024];
                DatagramPacket pack = new DatagramPacket(buf, buf.length);
                ms.receive(pack);
                if (!pack.getData().toString().isEmpty()) {
                    System.out.write(pack.getData(), 0, pack.getLength());
                    System.out.println();
                } else {

                    Scanner sc = new Scanner(System.in);
                    String mensaje = sc.nextLine();
                    System.out.println(mensaje);
                    if (!"exit".equals(mensaje)) {
                        try (PrintStream outputStream = new PrintStream(socket.getOutputStream())) {
                            outputStream.println(mensaje);
                            outputStream.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        socket.close();
                        System.exit(0);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
