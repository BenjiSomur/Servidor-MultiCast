/**
 * Mensaje
 */
public class Mensaje {

    private String contenido;
    private int tiempoEnCola;

    public Mensaje(String contenido) {
        this.contenido = contenido;
        this.tiempoEnCola = 0;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @return the tiempoEnCola
     */
    public int getTiempoEnCola() {
        return tiempoEnCola;
    }
}