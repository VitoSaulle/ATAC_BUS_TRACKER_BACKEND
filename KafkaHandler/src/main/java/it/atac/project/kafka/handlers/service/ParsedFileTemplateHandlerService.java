package it.atac.project.kafka.handlers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.atac.project.data.AtacGtfsData;
import it.atac.project.kafka.handlers.utils.KafkaTopics;

@Service
public class ParsedFileTemplateHandlerService {
	@Autowired
	private KafkaTemplate<String, AtacGtfsData> kafkaTemplate;
	
	public void sendParsedFile(AtacGtfsData parsedFile) {
		kafkaTemplate.send(KafkaTopics.PARSED_FILE_TOPIC, parsedFile);
	}
}
