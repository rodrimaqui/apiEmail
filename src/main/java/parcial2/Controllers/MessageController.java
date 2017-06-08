package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import parcial2.Models.Message;
import parcial2.Services.MessageService;


/**
 * Created by rodri on 06/06/17.
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    MessageService m;

    @RequestMapping(value ="/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@RequestBody Message message)
    {
        m.saveMessage(message);
    }
}
