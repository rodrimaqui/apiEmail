package parcial2.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
public class User {

    private int id;
    private String email;
    private String contrasenia;
    private Person p;
   // private List<Message> listMessage;

    public User(){}

    public User(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
        this.p = new Person();
        //listMessage = new ArrayList<Message>();
    }

    public User(int id, String email, String contrasenia, Person p/*, List<Message> listMessage*/) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.p = p;
        //this.listMessage = listMessage;
    }

   /* public User(int id, String email, String contrasenia, Person p) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.p = p;

    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    /*public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }*/
}
