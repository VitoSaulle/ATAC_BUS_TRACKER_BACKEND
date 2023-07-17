package it.atac.project.websocket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/atac-ws") //previous stomp-endpoint
			.setAllowedOriginPatterns("*").withSockJS();
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		registry.enableSimpleBroker("/topic");//per pushare su un topic
		registry.setApplicationDestinationPrefixes("/app");//per far arrivare le chiamate
	}

}

