package it.atac.project.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

    private final SimpMessagingTemplate messagingTemplate;

    public SubscribeListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        String subscribedTopic = String.valueOf(event.getMessage().getHeaders().get("simpDestination"));

        if ("/topic/events".equals(subscribedTopic)) {
            log.info("Generating response for subscribed client to /topic/events");
        } else if ("/topic/mintings".equals(subscribedTopic)) {
            log.info("Generating response for subscribed client to /topic/mintings");
        }
    }

    public void generateResponse() {
        messagingTemplate.convertAndSend("/topic/events", "aaaaaaaaaa");
    }
}
