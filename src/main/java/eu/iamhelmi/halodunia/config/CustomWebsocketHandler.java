package eu.iamhelmi.halodunia.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomWebsocketHandler extends AbstractWebSocketHandler {

    private final Map<String, WebSocketSession> sessions;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
//        var principal = session.getPrincipal();
//
//        if (principal == null || principal.getName() == null) {
//            session.close(SERVER_ERROR.withReason("User must be authenticated"));
//            return;
//        }
//
//        sessions.put(principal.getName(), session);
    	//sessions.put("name", "helmi");
    	sessions.put(session.getId(), session);
    	log.info("Session is added {}", sessions.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        //var principal = session.getPrincipal();
    	log.info("A websocket client is disconnected {} with remote IP ", session.getId(), session.getRemoteAddress() );
       sessions.remove(session.getId());
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
      String payload = message.getPayload();
      log.info("Received text: {}", payload);
      log.info("Sessions size: {}",sessions.size());
      try {
    	  for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
    	        log.info("Sending message to websocket session {} with message {} ", entry.getKey(), entry.getValue());
    	        entry.getValue().sendMessage(message);
    	   }
		
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }
    }

	public Map<String, WebSocketSession> getSessions() {
		return sessions;
	}
    
    
}
