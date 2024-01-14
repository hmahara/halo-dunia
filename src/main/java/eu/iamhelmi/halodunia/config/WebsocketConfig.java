package eu.iamhelmi.halodunia.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

	private final Map<String, WebSocketSession> sessions = new HashMap<>();


    @Bean("sessions")
    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new CustomWebsocketHandler(sessions), "/event-websocket").setAllowedOriginPatterns("*");
        
    }
}
