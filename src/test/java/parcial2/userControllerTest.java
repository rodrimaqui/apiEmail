package parcial2;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import junit.framework.TestCase;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import parcial2.Models.Person;
import parcial2.Models.User;
import parcial2.util.SessionData;
import parcial2.App;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
/**
 * Created by rodri on 16/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class userControllerTest extends TestCase{

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String sessionid;
    private User user;

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

    //Test Agregar Usuario

    @Test
    public void testAddUserCreated() throws Exception{
        URL url  = Resources.getResource("user.json");
        String json = Resources.toString(url, Charsets.UTF_8);

        mockMvc.perform(
                post("/api/user/")
                        .header("sessionid", this.sessionid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        )
                .andExpect(status().isCreated());

    }

    //Test Traer todos los users

    @Test
    public void testAllUsersOk() throws Exception{
        mockMvc.perform(
                get("/api/user/")
                        .header("sessionid", this.sessionid)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    //Test Buscar por email

    @Test
    public void testGetByEmailOk() throws Exception{
        mockMvc.perform(
                get("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("email", this.user.getEmail())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetByEmailNotFound() throws Exception{
        mockMvc.perform(
                get("/api/user")
                        .header("sessionid", this.sessionid)
                        .param("email", "carlos")
        )
                .andExpect(status().isNotFound());
    }

    //Test del Delete
    @Test
    public void testDeleteUserOk() throws Exception{

        mockMvc.perform(delete
                ("/api/user/")
                .header("sessionid", this.sessionid)
                .header("id", 5)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserBadRequest() throws Exception{

        mockMvc.perform(delete
                ("/api/user/")
                .header("sessionid", this.sessionid)
                .header("id", "casa")
        )
                .andExpect(status().isBadRequest());
    }

    // Test Sobre el logIn
    @Test
    public void testLogOUtOk() throws Exception{

        mockMvc.perform(post("/logout")
                .header("sessionid", this.sessionid)

        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void testLoginForbbiden() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("user", "asd"),
                                new BasicNameValuePair("pwd", "asa")
                        ))))
        )
                .andExpect(status().isForbidden());
    }
    @Test
    public void testLoginOk() throws Exception{
        mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(asList(
                                new BasicNameValuePair("user", "asd"),
                                new BasicNameValuePair("pwd", "asd")
                        ))))
        )
                .andExpect(status().isOk());
    }

}
