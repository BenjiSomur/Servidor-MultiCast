/**
 * Mensaje
 */
public class Mensaje {

    private String contenido;
    private int usuario;

    public Mensaje(String contenido, int usuario) {
        this.contenido = contenido;
        this.usuario = usuario;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @return the usuario
     */
    public int getUsuario() {
        return usuario;
    }
}