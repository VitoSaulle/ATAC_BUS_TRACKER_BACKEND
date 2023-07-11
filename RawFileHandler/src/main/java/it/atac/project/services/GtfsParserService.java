package it.atac.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

import it.atac.project.data.AtacGtfsData;
import it.atac.project.kafka.handlers.utils.KafkaGroupIds;
import it.atac.project.kafka.handlers.utils.KafkaTopics;
import it.atac.project.mongo.document.AtacGtfsDataDocument;
import it.atac.project.mongo.repository.AtacGtfsDataDocumentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GtfsParserService {

	@Autowired
	private AtacGtfsDataDocumentRepository atacGtfsDataDocumentRepository;

	@KafkaListener(topics = KafkaTopics.RAW_FILE_TOPIC, groupId = KafkaGroupIds.ATAC_QUEUE)
	public void parseGtfsFile(byte[] gtfsFile) {

		List<AtacGtfsData> gtfsDataList;
		try {

			gtfsDataList = FeedMessage.parseFrom(gtfsFile).getEntityList().stream()
					.map(entity -> {
						log.info("Entity arrived: {}", entity.toString());
						var gtfsData = new AtacGtfsData();

						gtfsData.setRouteId(entity.getVehicle().getTrip().getRouteId());
						gtfsData.setLatitude(entity.getVehicle().getPosition().getLatitude());
						gtfsData.setLongitude(entity.getVehicle().getPosition().getLongitude());
						gtfsData.setCurrentStop(entity.getVehicle().getCurrentStopSequence());

						gtfsData.setMatricola(entity.getVehicle().getVehicle().getLabel());
						gtfsData.setTarga(entity.getVehicle().getVehicle().getLicensePlate());

						return gtfsData;

					}).toList();
		} catch (Exception e) {
			log.error("Error while parsing gtfs file: {}", e.getMessage());
			return;
		}

		// Do further processing with the parsed GTFS data
		processParsedGtfsData(gtfsDataList);
	}

	private void processParsedGtfsData(List<AtacGtfsData> gtfsDataList) {
	    try {
	        List<AtacGtfsDataDocument> documents = new ArrayList<>();

	        gtfsDataList.forEach(data -> {
	            var doc = new AtacGtfsDataDocument();
	            BeanUtils.copyProperties(data, doc);
	            documents.add(doc);
	        });

	        atacGtfsDataDocumentRepository.saveAll(documents);
	    } catch (DataAccessException e) {
	        log.error("Error while saving on DB: {}", e.getMessage());
	    }
	}


}
