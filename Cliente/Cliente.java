import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private static final int PORT = 1234;
    private static DatagramSocket dgramSocket;
    private static MulticastSocket ms;
    private static DatagramPacket inPkt, outPkt;
    private static byte[] buff;
    private static String msg = "", msgIn = "";
    
    private static final String GROUP = "225.4.5.6";
    private static InetAddress host;
    private static byte TTL = 3;
    
    public static void main(String[] args) {
        
        try {
            ms = new MulticastSocket();
        } catch (UnknownHostException e) {
            System.out.println("No se encontro en HOST!");
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        run();
    }

    public static void run() {
        try {

            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
            host = InetAddress.getByName(GROUP);
            do {
                System.out.print("Ingresar mensaje: ");
                msg = userEntry.readLine();

                //enviar mensajes hasta recibir BYEsend messages until BYE is sent
                if (!msg.equals("BYE")) {

                    outPkt = new DatagramPacket(msg.getBytes(), msg.length(), host, PORT);
                    dgramSocket.send(outPkt);
                    buff = new byte[256];
                    inPkt = new DatagramPacket(buff, buff.length);
                    ms.receive(inPkt);
                    msgIn = new String(inPkt.getData(), 0, inPkt.getLength());
                    System.out.println("SERVIDOR: " + msgIn);
                }
            } while (!msg.equals("BYE"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dgramSocket.close();
        }
    }
}
