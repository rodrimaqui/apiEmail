package parcial2.Models;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import parcial2.Models.Message;
import parcial2.Models.Person;
import parcial2.Models.User;
import parcial2.Response.LoginResponseWrapper;
import parcial2.Response.UserWrapper;


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


        Person p2 = new Person("das","asd","ad","sefd","df","sdf","sad");

        LoginResponseWrapper lrw = new LoginResponseWrapper();

        lrw.setSessionId("asdasas");

        person = new Person();

        person.setId(1);
        person.setNombre("Rodrigo");
        person.setApellido("Maqui");
        person.setDireccion("35 1414");
        person.setTelefono("2291466575");
        person.setCiudad("Miramar");
        person.setPais("Argentina");
        person.setProvincia("Buenos Aires");

        User u2 = new User("jjj","qqq");

        user = new User();

        user.setId(1);
        user.setEmail("asd");
        user.setContrasenia("asd");
        user.setP(person);
       // message = new Message(1,"asd","asd","asd","asd","123",true);

        Message message2 = new Message("asd","asd","asd","asd",true);
        message = new Message();
        message.setId(1);
        message.setRemitente("asd");
        message.setReceptor("asd");
        message.setAsunto("asd");
        message.setMensaje("asd");
        message.setFecha("123");
        message.setEstado(true);

        UserWrapper uWp = new UserWrapper();


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

    public void testGetIdMessage()
    {
        assertEquals(1,message.getId());
    }

    public void testGetReceptorMessage()
    {
        assertEquals("asd",message.getReceptor());
    }

    public void testGetRemitenteMessage()
    {
        assertEquals("asd",message.getRemitente());
    }
    public void testGetAsuntoMessage()
    {
        assertEquals("asd",message.getAsunto());
    }

    public void testGetMensajeMessage()
    {
        assertEquals("asd",message.getMensaje());
    }

    public void testGetFechaMessage()
    {
        assertEquals("123",message.getFecha());
    }

    public void testGetEstadoMessage()
    {
        assertEquals(true,message.isEstado());
    }

}
