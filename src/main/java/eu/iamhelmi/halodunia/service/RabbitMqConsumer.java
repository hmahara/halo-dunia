package eu.iamhelmi.halodunia.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import eu.iamhelmi.halodunia.config.CustomWebsocketHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMqConsumer {
	
	@Value("${rabbitmq.queue.name1}")
    private String queue1;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    
    @Value("${rabbitmq.queue.name1}")
    private String q1;
    
    @Value("${rabbitmq.queue.name2}")
    private String q2;
    
	@Autowired
	CustomWebsocketHandler websocketHandler;

	@RabbitListener(queues = {"${rabbitmq.queue.name1}"})
	public void consumeTopic1 (String message) {
		Map<String, WebSocketSession> sessions = websocketHandler.getSessions();
		log.info("** Received message RabbitMq: Queue: {} body: {}", q1, message);
		message = "{ \"queue\":\""+q1+"\", \"data\":"+message+"}";
		 try {
	    	  for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
	    	        log.info("Sending message to websocket session {} with message {} ", entry.getKey(), entry.getValue());
	    	        TextMessage t = new TextMessage(message.getBytes());
	    	        entry.getValue().sendMessage(t);
	    	   }
			
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		
	}
	
	@RabbitListener(queues = {"${rabbitmq.queue.name2}"})
	public void consumeTopic2 (String message) {
		Map<String, WebSocketSession> sessions = websocketHandler.getSessions();
		log.info("## Received message RabbitMq: Queue: {} body: {}",q2, message);
		message = "{ \"queue\":\""+q2+"\", \"data\":"+message+"}";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 try {
	    	  for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
	    	        log.info("Sending message to websocket session {} with message {} ", entry.getKey(), entry.getValue());
	    	        TextMessage t = new TextMessage(message.getBytes());
	    	        entry.getValue().sendMessage(t);
	    	   }
			
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		
	}
}
