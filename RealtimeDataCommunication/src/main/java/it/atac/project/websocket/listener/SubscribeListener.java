package it.atac.project.websocket.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import it.atac.project.services.LatestGtfsDataRetrieverService;
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

		try {
			log.info("Subscribed!");
			var latestRegisteredPosition = latestGtfsDataRetrieverService.retrieveLastData();
			messageSenderService.sendMessageToTopic(subscribedTopic, latestRegisteredPosition);
		} catch (Exception e) {
			log.error("Error while sending the response to the subscriber. {}", e.getMessage());
		}

	}

}
