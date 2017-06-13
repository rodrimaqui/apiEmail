package parcial2.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import parcial2.Models.Message;
import parcial2.util.AuthenticationData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@Repository
public class DaoMessage {

    private Connection cn;

    @Autowired
    private AuthenticationData as;

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

        try {

            //Obtengo el id del Receptor
            PreparedStatement st1 = cn.prepareStatement("SELECT id FROM users WHERE email LIKE ?");
            st1.setString(1, message.getReceptor());
            ResultSet rs1 = st1.executeQuery();

            int idReceptor = 0;
            while(rs1.next())
            {
                idReceptor = rs1.getInt("id");
            }

            //Obtengo el id del Remitente
            PreparedStatement st2 = cn.prepareStatement("SELECT id FROM users WHERE email LIKE ?");
            st2.setString(1, message.getRemitente());
            ResultSet rs2 = st2.executeQuery();

            int idRemitente = 0;
            while(rs2.next())
            {
                idRemitente = rs2.getInt("id");
            }

            //Guardo toda la info en la base de datos

            PreparedStatement st3 = cn.prepareStatement("INSERT INTO messages(remitente,receptor,asunto,mensaje,userReceptor,userRemitente) VALUES(?,?,?,?,?,?)");
            st3.setString(1,message.getRemitente());
            st3.setString(2,message.getReceptor());
            st3.setString(3,message.getAsunto());
            st3.setString(4,message.getMensaje());
            st3.setInt(5,idReceptor);
            st3.setInt(6,idRemitente);

            st3.execute();

      }catch(SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void removeMessage(int id) {

        try {

            PreparedStatement ps = cn.prepareStatement("UPDATE messages SET estado = 0 WHERE id = ?");
            ps.setInt(1, id);

            ps.execute();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public List<Message> getInboxMessage() {
        List<Message> listMessage = new ArrayList<Message>();
        try{

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM messages WHERE userReceptor = ? AND estado = 1");
            ps.setInt(1,as.getUsuario().getId());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                Message auxMessage = new Message(rs.getInt("id"),rs.getString("remitente"),rs.getString("receptor"),rs.getString("asunto"),rs.getString("mensaje"),rs.getString("fecha"),rs.getBoolean("estado"));
                listMessage.add(auxMessage);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return listMessage;
    }

    public List<Message> getTrashMessage() {

        List<Message> listMessage = new ArrayList<Message>();
        try{

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM messages WHERE userReceptor = ? AND estado = 0");
            ps.setInt(1,as.getUsuario().getId());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                Message auxMessage = new Message(rs.getInt("id"),rs.getString("remitente"),rs.getString("receptor"),rs.getString("asunto"),rs.getString("mensaje"),rs.getString("fecha"),rs.getBoolean("estado"));
                listMessage.add(auxMessage);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return listMessage;
    }

    public List<Message> getSendMessage() {
        List<Message> listMessage = new ArrayList<Message>();
        try{

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM messages WHERE userRemitente = ? AND estado = 1");
            ps.setInt(1,as.getUsuario().getId());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                Message auxMessage = new Message(rs.getInt("id"),rs.getString("remitente"),rs.getString("receptor"),rs.getString("asunto"),rs.getString("mensaje"),rs.getString("fecha"),rs.getBoolean("estado"));
                listMessage.add(auxMessage);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return listMessage;
    }
}
