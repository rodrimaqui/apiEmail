package parcial2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcial2.Models.Message;
import parcial2.Persistence.DaoMessage;
import parcial2.Persistence.DaoUser;

import java.util.List;

/**
 * Created by rodri on 07/06/17.
 */
@Service
public class MessageService {

    DaoMessage m;

    @Autowired
    public MessageService(DaoMessage m)
    {
        this.m = m;
    }

    public void saveMessage(Message message) {
        m.saveMessage(message);
    }

    public void removeMessage(int id) {
        m.removeMessage(id);
    }

    public List<Message> getInboxMessage() {
        return m.getInboxMessage();
    }

    public List<Message> getTrashMessage() {
        return m.getTrashMessage();
    }

    public List<Message> getSendMessage() {
        return m.getSendMessage();
    }
}
