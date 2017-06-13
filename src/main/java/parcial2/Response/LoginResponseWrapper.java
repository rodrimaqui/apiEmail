package parcial2.Response;

/**
 * Created by rodri on 12/06/17.
 */
public class LoginResponseWrapper {

    private String sessionId ;


    public LoginResponseWrapper() {}

    public LoginResponseWrapper(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
