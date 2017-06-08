package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcial2.Models.User;
import parcial2.Services.UserService;

import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService u;

    @RequestMapping(value ="/user", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUser()
    {
        return u.getAllUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User usuario)
    {
        u.saveUser(usuario);

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestHeader int id)
    {
        u.removeOneUser(id);
    }
}
