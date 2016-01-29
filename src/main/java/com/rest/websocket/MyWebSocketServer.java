package com.rest.websocket;

import com.rest.crud.no.NO;
import com.rest.crud.no.NoService;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/actions")
public class MyWebSocketServer {

    private MySessionHandler sessionHandler = new MySessionHandler();
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
        Logger.getLogger(MyWebSocketServer.class.getName()).log(Level.SEVERE, session.toString());
    }

    @OnClose
    public void close(Session session) {
        Logger.getLogger(MyWebSocketServer.class.getName()).log(Level.SEVERE,"Closed");

        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(MyWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            if ("getNo".equals(message)) {
                NoService noService = new NoService();
                List<NO> noList = noService.getNosAsList();
                NO no = new NO();
                no.setIP("324,123,213,123");
                noList.add(no);
                JSONObject obj = new JSONObject(noList);

                sessionHandler.sendToAllConnectedSessions(obj);
            }
        }catch (Exception ex){}
    }
}