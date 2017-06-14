package parcial2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcial2.Models.Message;
import parcial2.Services.MessageService;

import java.util.List;


/**
 * Created by rodri on 06/06/17.
 */
@RestController
/*@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)*/
public class MessageController {

    @Autowired
    MessageService m;

    @RequestMapping(value ="/api/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMessage(@RequestBody Message message)
    {
        try {
            m.saveMessage(message);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeMessage(@RequestHeader int id)
    {
        try {
            m.removeMessage(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/message/Inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getInboxMessage()
    {
        List<Message> mList = m.getInboxMessage();
        if(mList != null)
        {
            return new ResponseEntity<List<Message>>(mList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/message/Trash", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getTrashMessage()
    {
        List<Message> mList = m.getTrashMessage();

        if(mList != null)
        {
            return new ResponseEntity<List<Message>>(mList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/api/message/Send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getSendMessage()
    {
        List<Message> mList = m.getSendMessage();

        if(mList != null)
        {
            return new ResponseEntity<List<Message>>(mList, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
        }
    }
}
