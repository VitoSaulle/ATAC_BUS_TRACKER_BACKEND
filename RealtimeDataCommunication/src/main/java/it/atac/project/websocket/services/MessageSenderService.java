package it.atac.project.websocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageSenderService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Sends a message to the specified topic using the SimpMessagingTemplate.
     *
     * @param topic   The topic to which the message will be sent
     * @param message The message object to be sent
     */
    public void sendMessageToTopic(String topic, Object message) {
        try {
            messagingTemplate.convertAndSend(topic, message);
            log.info("Message sent to the topic: {}", topic);
        } catch (Exception e) {
            log.error("Error while sending message to the topic: {}", e.getMessage());
        }
    }
}
