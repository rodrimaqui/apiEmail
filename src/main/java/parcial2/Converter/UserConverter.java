package parcial2.Converter;

import parcial2.Models.User;
import parcial2.Response.UserWrapper;

/**
 * Created by rodri on 14/06/17.
 */
public class UserConverter {

    public UserWrapper converterUser(User u)
    {
        UserWrapper aux = new UserWrapper(u.getEmail(),u.getP().getNombre(),u.getP().getApellido(),u.getP().getDireccion(),u.getP().getTelefono(),u.getP().getCiudad(),u.getP().getProvincia(),u.getP().getPais());
        return aux;
    }
}
