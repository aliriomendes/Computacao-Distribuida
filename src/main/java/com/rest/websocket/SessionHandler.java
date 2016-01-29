package com.rest.websocket;

import com.rest.crud.no.NO;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class SessionHandler {
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

    private void sendToAllConnectedSessions(JsonObject message) {
    }

    private void sendToSession(Session session, JsonObject message) {
    }


}
