package parcial2.util;

import org.joda.time.DateTime;
import parcial2.Models.User;

/**
 * Created by rodri on 11/06/17.
 */
public class AuthenticationData {

    private User usuario;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
