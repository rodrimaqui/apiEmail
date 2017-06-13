package parcial2.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import parcial2.Models.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@Repository
public class DaoMessage {

    private Connection cn;

    public DaoMessage(@Value("${db.username}") String dbUsername,
                   @Value("${db.name}") String dbName,
                   @Value("${db.host}") String dbHost,
                   @Value("${db.port}") String dbPort,
                   @Value("${db.password}") String dbPassword
    ) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "api", "123456");
            this.cn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void saveMessage(Message message) {


    }

    public void removeMessage(int id) {
    }


    public List<Message> getInboxMessage() {
        return null;
    }

    public List<Message> getTrashMessage() {
        return null;
    }
}
