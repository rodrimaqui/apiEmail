package parcial2.Models;

/**
 * Created by rodri on 06/06/17.
 */
public class Message {

    private int id;
    private String remitente;
    private String receptor;
    private String asunto;
    private String mensaje;
    private String fecha;
    private boolean estado;

    public Message(){}
    public Message(String remitente, String receptor, String asunto, String mensaje, boolean estado) {
        this.remitente = remitente;
        this.receptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public Message(int id, String remitente, String receptor, String asunto, String mensaje, String fecha,boolean estado) {
        this.id = id;
        this.remitente = remitente;
        this.receptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
