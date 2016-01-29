package com.rest.websocket;

import com.rest.crud.no.NO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/actions")
public class WebSocketServer {

    private SessionHandler sessionHandler = new SessionHandler();
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
        Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, session.toString());
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        Logger.getLogger(WebSocketServer.class.getName()).log(Level.SEVERE, "Hello world!");
        try {
            JsonReader reader = Json.createReader(new StringReader(message));
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                NO no = new NO();
                no.setIP("123.123.213.123");
                sessionHandler.addDevice(no);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeDevice(id);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.toggleDevice(id);
            }
        }catch (Exception ex){}
    }
}