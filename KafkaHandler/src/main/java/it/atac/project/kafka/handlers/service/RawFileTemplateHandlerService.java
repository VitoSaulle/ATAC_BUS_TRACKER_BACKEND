package it.atac.project.kafka.handlers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.atac.project.kafka.handlers.utils.KafkaTopics;

@Service
public class RawFileTemplateHandlerService {

	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	public void sendRawFile(byte[] rawFile) {

		kafkaTemplate.send(KafkaTopics.RAW_FILE_TOPIC, rawFile);
	}
}
