package parcial2.Persistence;

import org.springframework.stereotype.Repository;
import parcial2.Models.Message;

import java.sql.Connection;

/**
 * Created by rodri on 06/06/17.
 */
@Repository
public class DaoMessage {

    private Connection cn;

    private static DaoMessage instance;

    public static DaoMessage getInstance() {
        if (instance == null) {
            instance = new DaoMessage();
        }
        return instance;
    }
    public void saveMessage(Message message) {


    }
}
