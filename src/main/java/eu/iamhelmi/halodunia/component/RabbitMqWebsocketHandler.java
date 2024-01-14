package eu.iamhelmi.halodunia.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import eu.iamhelmi.halodunia.config.CustomWebsocketHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMqWebsocketHandler extends TextWebSocketHandler {
	
	@Autowired
	//private SimpMessagingTemplate simpMessagingTemplate;
	CustomWebsocketHandler websocketHandler;

	
}
