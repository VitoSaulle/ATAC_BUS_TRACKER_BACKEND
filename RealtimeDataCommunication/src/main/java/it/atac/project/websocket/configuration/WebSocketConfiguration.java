package it.atac.project.websocket.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import it.atac.project.utils.WebsocketConstants;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(WebsocketConstants.STOMP_ENDPOINT_URL)
				.setAllowedOriginPatterns(WebsocketConstants.ALLOWED_ORIGIN_PATTERNS).withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(WebsocketConstants.MESSAGE_BROKER_DESTINATION_PREFIX);
		registry.setApplicationDestinationPrefixes(WebsocketConstants.APPLICATION_DESTINATION_PREFIX);
	}

}
