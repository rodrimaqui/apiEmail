package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import parcial2.Models.Message;
import parcial2.Services.MessageService;

import java.util.List;


/**
 * Created by rodri on 06/06/17.
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    MessageService m;

    @RequestMapping(value ="/api/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@RequestBody Message message)
    {
        m.saveMessage(message);
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeMessage(@RequestHeader int id)
    {
        m.removeMessage(id);
    }

    @RequestMapping(value = "/api/message/Inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getInboxMessage()
    {
        return m.getInboxMessage();
    }

    @RequestMapping(value = "/api/message/Trash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getTrashMessage()
    {
        return m.getTrashMessage();
    }
}
