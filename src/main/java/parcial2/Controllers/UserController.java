package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcial2.Models.User;
import parcial2.Response.LoginResponseWrapper;
import parcial2.Services.UserService;
import parcial2.util.SessionData;

import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@RestController
//@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService u;
    ///
    @Autowired
    SessionData sessionData;
    ///
    @RequestMapping(value ="/api/user/", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUser()
    {
        System.out.println("asdasdasdasdasddsdasdasdas");
        return u.getAllUser();
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User usuario)
    {
        u.saveUser(usuario);

    }

    @RequestMapping(value = "/api/deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestHeader int id)
    {
        u.removeOneUser(id);
    }


//////////
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LoginResponseWrapper> getById(@RequestParam("user") String user, @RequestParam("pwd") String pwd){
        User us = u.login(user, pwd);
        if (null != us) {
            String sessionId = sessionData.addSession(us);
            return new ResponseEntity<LoginResponseWrapper>(new LoginResponseWrapper(sessionId), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity getById(@RequestHeader("sessionid") String sessionId) {
        sessionData.removeSession(sessionId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    /////////
}
