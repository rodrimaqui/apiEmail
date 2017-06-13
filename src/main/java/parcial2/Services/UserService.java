package parcial2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcial2.Models.Person;
import parcial2.Models.User;
import parcial2.Persistence.DaoUser;

import java.security.Permission;
import java.util.List;

/**
 * Created by rodri on 06/06/17.
 */
@Service
public class UserService {

    DaoUser a;

    @Autowired
    public UserService(DaoUser a)
    {
        this.a = a;
    }

    public void saveUser(User u)
    {
        a.saveUser(u);
    }

    public List<User> getAllUser()
    {
        return a.getAllUser();
    }


    public void removeOneUser(int id)
    {
        a.removeOneUser(id);
    }

    ///
    public User login(String nombreUsuario, String password) {
        return a.getOne(nombreUsuario,password);
    }
}
