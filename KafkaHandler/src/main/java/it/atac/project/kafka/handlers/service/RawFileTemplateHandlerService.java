package it.atac.project.kafka.handlers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.atac.project.kafka.handlers.utils.KafkaTopics;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RawFileTemplateHandlerService {

	@Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    /**
     * Sends a raw file to the raw file topic.
     *
     * @param rawFile the raw file as a byte array
     */
    public void sendRawFile(byte[] rawFile) {
        if (rawFile == null || rawFile.length == 0) {
            log.warn("Invalid raw file. Skipping sending to Kafka.");
            return;
        }

        try {
            log.info("Producing message of size: {} bytes", rawFile.length);
            kafkaTemplate.send(KafkaTopics.RAW_FILE_TOPIC, rawFile);
        } catch (RuntimeException e) {
            log.error("Error while sending raw file to Kafka: {}", e.getMessage());
        }
    }
}
