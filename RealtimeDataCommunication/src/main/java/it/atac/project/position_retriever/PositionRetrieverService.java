package it.atac.project.position_retriever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.atac.project.services.LatestGtfsDataRetrieverService;
import it.atac.project.utils.WebsocketConstants;
import it.atac.project.websocket.services.MessageSenderService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PositionRetrieverService {

	@Autowired
	private LatestGtfsDataRetrieverService latestGtfsDataRetrieverService;
	
	@Autowired
	private MessageSenderService messageSenderService;

	public void execute() {
		
		try{
			var lastAcquiredData = latestGtfsDataRetrieverService.retrieveLastData();
			messageSenderService.sendMessageToTopic(WebsocketConstants.POSITION_QUEUE, lastAcquiredData);
		}catch(Exception e) {
			log.error("Error while calling LatestGtfsDataRetrieverService, {}",e.getMessage());
		}finally {			
			log.info("PositionRetrieverService executed");
		}
		
	}
	
	
}
