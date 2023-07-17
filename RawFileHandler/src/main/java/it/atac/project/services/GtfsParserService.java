package it.atac.project.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

import it.atac.project.data.AtacGtfsData;
import it.atac.project.kafka.handlers.utils.KafkaGroupIds;
import it.atac.project.kafka.handlers.utils.KafkaTopics;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GtfsParserService {

	@Autowired
	private GtfsDataExtractorService gtfsDataExtractotService;

	@Autowired
	private GtfsDataToDocumentService dataToDocumentService;

	@Autowired
	private GtfsDataPersister gtfsDataPersister;

	@KafkaListener(topics = KafkaTopics.RAW_FILE_TOPIC, groupId = KafkaGroupIds.ATAC_QUEUE)
	public void parseGtfsFile(byte[] gtfsFile) {
		try {
			FeedMessage feedMessage = FeedMessage.parseFrom(gtfsFile);
			List<AtacGtfsData> gtfsDataList = gtfsDataExtractotService.extractGtfsData(feedMessage);

			// Do further processing with the parsed GTFS data
			parseAndSaveData(gtfsDataList);
		} catch (Exception e) {
			log.error("Error while parsing gtfs file: {}", e.getMessage());
		}
	}

	private void parseAndSaveData(List<AtacGtfsData> gtfsDataList) {
		
		var acquisitionTimestamp = Instant.now();
		var documentList = dataToDocumentService.generateGtfsDataDocuments(gtfsDataList, acquisitionTimestamp);
		try {
			gtfsDataPersister.persistGtfsDataDocuments(documentList);
		} catch (DataAccessException e) {
			log.error("Error while saving document(s), {}", e.getMessage());
		}

	}

}
