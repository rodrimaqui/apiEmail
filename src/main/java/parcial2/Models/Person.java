package parcial2.Models;

/**
 * Created by rodri on 06/06/17.
 */
public class Person {

    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String pais;
    private String provincia;

    public Person(){}

    public Person(String nombre,String apellido,String direccion,String telefono,String ciudad,String pais,String provincia)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.pais = pais;
        this.provincia = provincia;
    }

    public Person(int id, String nombre,String apellido,String direccion,String telefono,String ciudad,String pais,String provincia)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.pais = pais;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
