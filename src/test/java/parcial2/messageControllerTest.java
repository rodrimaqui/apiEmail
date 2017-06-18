package parcial2;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import parcial2.Models.Message;
import parcial2.Models.Person;
import parcial2.Models.User;
import parcial2.util.SessionData;

import java.net.URL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rodri on 17/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class messageControllerTest extends TestCase {


    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private User user;
    private Message message;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
        Person person = new Person();

        person.setId(5);
        person.setNombre("rodri");
        person.setApellido("Maqui");
        person.setDireccion("calle falsa 123");
        person.setTelefono("666");
        person.setCiudad("Springfild");
        person.setPais("Atlantida");
        person.setProvincia("NNNN");

        user = new User();

        user.setId(1);
        user.setEmail("asd");
        user.setContrasenia("asd");
        user.setP(person);

        this.sessionid = this.sessionData.addSession(user);
    }

    @After
    public void tearDown() throws Exception{
        this.sessionData.removeSession(this.sessionid);
    }

    //Test Crear Mensaje

    @Test
    public void testAddMessageCreated() throws Exception
    {
        URL url  = Resources.getResource("message.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(
                post("/api/message")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());

    }

    //Test Recibir mensajes Inbox
    @Test
    public void testGetMessageInbox() throws Exception{

        mockMvc.perform(
                get("/api/message/Inbox")
                        .header("sessionid", this.sessionid)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    //Test Recibir mensajes Trash
    @Test
    public void testGetMessageTrash() throws Exception{

        mockMvc.perform(
                get("/api/message/Trash")
                        .header("sessionid", this.sessionid)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    //Test Recibir mensajes Send
    @Test
    public void testGetMessageSend() throws Exception{

        mockMvc.perform(
                get("/api/message/Send")
                        .header("sessionid", this.sessionid)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    //Test Borrar mensaje

    @Test
    public void testDeleteMessage() throws Exception{

        mockMvc.perform(delete
                ("/api/message")
                .header("sessionid", this.sessionid)
                .header("id", 3)
        )
                .andExpect(status().isOk());
    }


}
