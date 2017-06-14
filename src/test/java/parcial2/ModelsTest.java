package parcial2;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import parcial2.Models.Message;
import parcial2.Models.Person;
import parcial2.Models.User;



/**
 * Created by rodri on 13/06/17.
 */
public class ModelsTest extends TestCase {

    User user;
    Person person;
    Message message;


    public ModelsTest(String name) {
        super(name);
    }

    protected void setUp(){

        person = new Person (1,"Rodrigo","Maqui","35 1414","2291466575","Miramar","Argentina","Buenos Aires");
        user = new User(1,"asd","asd",person);
        message = new Message();
    }

    protected void tearDown() {
        user = null;
    }

    public void testGetIdPerson()
    {
        assertEquals(1,person.getId());
    }

    public void testSetIdPerson()
    {
        person.setId(2);
        assertEquals(2,person.getId());
    }

    public void testGetNombrePerson(){
        assertEquals("Rodrigo",person.getNombre());
    }

    public void testGetApellidoPerson(){
        assertEquals("Maqui",person.getApellido());
    }

    public void testGetDireccionPerson(){
        assertEquals("35 1414",person.getDireccion());
    }

    public void testGetTelefonoPerson(){
        assertEquals("2291466575",person.getTelefono());
    }

    public void testGetCiudadPerson(){
        assertEquals("Miramar",person.getCiudad());
    }

    public void testGetPaisPerson(){
        assertEquals("Argentina",person.getPais());
    }

    public void testGetProvinciaPerson(){
        assertEquals("Buenos Aires",person.getProvincia());
    }

    public void testGetIdUser()
    {
        assertEquals(1,user.getId());
    }

    public void testGetEmail(){
        assertEquals("asd",user.getEmail());
    }
    public void testGetContrasenia()
    {
        assertEquals("asd",user.getContrasenia());
    }

    public void testGetP(){
        assertEquals(person,user.getP());
    }



}
