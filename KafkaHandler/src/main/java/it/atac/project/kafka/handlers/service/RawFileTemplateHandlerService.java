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

	public void sendRawFile(byte[] rawFile) {
		log.info("Producing message...");
		kafkaTemplate.send(KafkaTopics.RAW_FILE_TOPIC, rawFile);
	}
}
