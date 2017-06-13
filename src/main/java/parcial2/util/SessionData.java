package parcial2.util;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import parcial2.Models.User;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rodri on 11/06/17.
 */
@Service
public class SessionData {
    final static Logger logger = Logger.getLogger(SessionData.class);
    HashMap<String, AuthenticationData> sessionData;

    @Value("${session.expiration}")
    int expirationTime;

    @Autowired
    AuthenticationData aData;

    public SessionData() {
        this.sessionData = new HashMap<String, AuthenticationData>();
    }

    public String addSession(User usuario) {
        String sessionId = UUID.randomUUID().toString();
        //AuthenticationData aData = new AuthenticationData();
        aData.setUsuario(usuario);
        aData.setLastAction(new DateTime());
        this.sessionData.put(sessionId, aData);
        return sessionId;
    }


    public void removeSession(String sessionId) {
        sessionData.remove(sessionId);
    }

    public AuthenticationData getSession(String sessionId) {
        AuthenticationData aData = this.sessionData.get(sessionId);
        if (aData != null) {
            return aData;
        } else {
            return null;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
       // System.out.println("Checking sessions");
        Set<String> sessionsId = this.sessionData.keySet();
        for (String sessionId : sessionsId) {
            AuthenticationData aData = this.sessionData.get(sessionId);
            if (aData.getLastAction().plusSeconds(expirationTime).isBefore(System.currentTimeMillis())) {
                System.out.println("Deleting sessionId = " + sessionId);
                this.sessionData.remove(sessionId);
            }
        }
    }
}
