package parcial2.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import parcial2.Models.Person;
import parcial2.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@Repository
public class DaoUser {

    private Connection cn;

    public DaoUser(@Value("${db.username}") String dbUsername,
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

    public void saveUser(User u) {
        try {
            PreparedStatement st = cn.prepareStatement("INSERT INTO persons(nombre,apellido,direccion,telefono,ciudad,pais,provincia) VALUES(?,?,?,?,?,?,?)");
            st.setString(1, u.getP().getNombre());
            st.setString(2, u.getP().getApellido());
            st.setString(3, u.getP().getDireccion());
            st.setString(4, u.getP().getTelefono());
            st.setString(5, u.getP().getCiudad());
            st.setString(6, u.getP().getPais());
            st.setString(7, u.getP().getProvincia());
            st.execute();

            Statement st1 = cn.createStatement();
            ResultSet rs = st1.executeQuery("SELECT buscarUltimoId()");

            int i = 0;

            while (rs.next()) {
                i = rs.getInt("buscarUltimoId()");
            }

            PreparedStatement st2 = cn.prepareStatement("INSERT INTO users(email,pass,person_id) VALUES(?,?,?);");
            st2.setString(1, u.getEmail());
            st2.setString(2, u.getContrasenia());
            st2.setInt(3, i);

            st2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOneUser(int id) {
        try {
            PreparedStatement st = cn.prepareStatement("DELETE FROM users WHERE id = ?");
            st.setInt(1, id);

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser() {

        List<User> listUser = new ArrayList<User>();

        try {
            Statement st1 = cn.createStatement();
            ResultSet rs = st1.executeQuery("SELECT u.id AS idUser, u.email, u.pass, p.id AS idPerson,p.nombre,p.apellido,p.direccion,p.telefono,p.ciudad,p.pais,p.provincia FROM users AS u INNER JOIN persons AS p ON u.person_id = p.id");

            while (rs.next()) {
                Person p = new Person(rs.getInt("idPerson"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("pais"), rs.getString("provincia"));
                User ps = new User(rs.getInt("idUser"), rs.getString("email"), rs.getString("pass"), p);
                listUser.add(ps);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }
    ///

    public User getOne(String email, String password) {
        User ps = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.id AS idUser, u.email, u.pass, p.id AS idPerson,p.nombre,p.apellido,p.direccion,p.telefono,p.ciudad,p.pais,p.provincia FROM users AS u INNER JOIN persons AS p ON u.person_id = p.id  WHERE email = '" + email + "' AND pass = '" + password + "';");

            while (rs.next()) {
                Person p = new Person(rs.getInt("idPerson"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("ciudad"), rs.getString("pais"), rs.getString("provincia"));
                ps = new User(rs.getInt("idUser"), rs.getString("email"), rs.getString("pass"), p);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    ///
}
