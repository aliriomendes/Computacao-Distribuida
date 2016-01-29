package com.rest.websocket;

import com.rest.crud.no.NO;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import java.util.*;

@ApplicationScoped
public class MySessionHandler {
    private final Set sessions = new HashSet<Session>();
    private final Set devices = new HashSet<String>();

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    public List getDevices() {
        return new ArrayList<String>(devices);
    }

    public void addDevice(NO device) {
    }

    public void removeDevice(int id) {
    }

    public void toggleDevice(int id) {
    }

    private NO getDeviceById(int id) {
        return null;
    }

    private JsonObject createAddMessage(NO device) {
        return null;
    }

    public void sendToAllConnectedSessions(JSONObject message) {
        Iterator iterator = sessions.iterator();
        while (iterator.hasNext()){
            Session session = (Session)iterator.next();
            try {
                session.getBasicRemote().sendObject(message);
            }catch (Exception ex){
                System.out.println("Nāo foi possivel enviar mensagem");
                System.out.println(message);
                System.out.println(session.getUserProperties());
            }

        }
    }

    public void sendToSession(Session session, JsonObject message) {
    }


}
