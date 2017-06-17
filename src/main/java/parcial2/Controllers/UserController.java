package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcial2.Converter.UserConverter;
import parcial2.Models.User;
import parcial2.Response.LoginResponseWrapper;
import parcial2.Response.UserWrapper;
import parcial2.Services.UserService;
import parcial2.util.SessionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@RestController
//@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService u;

    @Autowired
    UserConverter converter;
    ///
    @Autowired
    SessionData sessionData;
    ///
    @RequestMapping(value ="/api/user/", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserWrapper>> getUser()
    {
        List<User> uList = u.getAllUser();
        if(uList != null)
        {
            return new ResponseEntity<List<UserWrapper>>(this.convertList(uList), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<UserWrapper>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/user/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User usuario)
    {
        try {
            u.saveUser(usuario);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/api/user/", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestHeader int id)
    {
        try {
            u.removeOneUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWrapper> getUserWithName(@RequestParam("email") String email)
    {
        User aux = u.getUserWithName(email);

        if (aux != null) {
            UserWrapper uW = converter.converterUser(aux);
            return  new ResponseEntity<UserWrapper>(uW,HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
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

    private List<UserWrapper> convertList(List<User> users)
    {
        List<UserWrapper> listW = new ArrayList<UserWrapper>();

        for(User u : users)
        {
            listW.add(converter.converterUser(u));
        }
        return listW;
    }
}
