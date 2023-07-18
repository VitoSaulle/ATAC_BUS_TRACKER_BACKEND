package it.atac.project.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import it.atac.project.services.LatestGtfsDataRetrieverService;
import it.atac.project.utils.WebsocketConstants;
import it.atac.project.websocket.services.MessageSenderService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SubscribeListener implements ApplicationListener<SessionSubscribeEvent> {

    @Autowired
    private MessageSenderService messageSenderService;

    @Autowired
    private LatestGtfsDataRetrieverService latestGtfsDataRetrieverService;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        String subscribedTopic = String.valueOf(event.getMessage().getHeaders().get("simpDestination"));

        // Check if the client is subscribed to the POSITION_QUEUE topic
        if (WebsocketConstants.POSITION_QUEUE.equals(subscribedTopic)) {
            try {
                // Log that the client is subscribed to the POSITION_QUEUE
                log.info("Subscribed to POSITION_QUEUE!");

                // Retrieve the latest registered position data
                var latestRegisteredPosition = latestGtfsDataRetrieverService.retrieveLastData();

                // Send the latest registered position data to the subscribed topic
                messageSenderService.sendMessageToTopic(subscribedTopic, latestRegisteredPosition);
            } catch (Exception e) {
                // Log any errors that occurred while sending the response to the subscriber
                log.error("Error while sending the response to the subscriber. {}", e.getMessage());
            }
        } else {
            // Log if the client is subscribed to a different topic
            log.info("Subscribed to a different topic: {}", subscribedTopic);
        }
    }
}

