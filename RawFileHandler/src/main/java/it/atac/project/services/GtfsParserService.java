package it.atac.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

import it.atac.project.data.AtacGtfsData;
import it.atac.project.kafka.handlers.utils.KafkaTopics;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GtfsParserService {

//	@Autowired
//	private ParsedFileTemplateHandlerService parsedFileTemplateHandlerService;

	@KafkaListener(topics = KafkaTopics.RAW_FILE_TOPIC)
	public void parseGtfsFile(byte[] gtfsFile) {
		List<AtacGtfsData> gtfsDataList;
		try {

			gtfsDataList = FeedMessage.parseFrom(gtfsFile).getEntityList().stream()
					//.filter(entity -> entity.hasTripUpdate())
					.map(entity -> {

						var gtfsData = new AtacGtfsData();

						gtfsData.setRouteId(entity.getVehicle().getTrip().getRouteId());
						gtfsData.setLatitude(entity.getVehicle().getPosition().getLatitude());
						gtfsData.setLongitude(entity.getVehicle().getPosition().getLongitude());
						gtfsData.setCurrentStop(entity.getVehicle().getCurrentStopSequence());

						gtfsData.setMatricola(entity.getVehicle().getVehicle().getLabel());
						gtfsData.setTarga(entity.getVehicle().getVehicle().getLicensePlate());

						return gtfsData;

					}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error while parsing gtfs file: {}", e.getMessage());
			return;
		}
//
//		// Do further processing with the parsed GTFS data
		processParsedGtfsData(gtfsDataList);
	}

	private void processParsedGtfsData(List<AtacGtfsData> gtfsDataList) {
		log.info("\n\nstarting logging...\n\n");
		
		log.info("total data legth: {}", gtfsDataList.size());
	
		gtfsDataList.forEach(data -> log.info("{}", data));

	}

}
