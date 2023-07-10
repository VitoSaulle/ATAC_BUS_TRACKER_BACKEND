package it.atac.project.services;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import it.atac.project.kafka.handlers.service.RawFileTemplateHandlerService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

@Slf4j
@Service
public class FileRetrieverService {

	// Set the endpoint URL
	private static final String FILE_ENDPOINT_URL = "https://romamobilita.it/sites/default/files/rome_rtgtfs_vehicle_positions_feed.pb";

	// Set the download interval
	private static final Duration DOWNLOAD_INTERVAL = Duration.ofSeconds(30);

	@SuppressWarnings("unused")
	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	@Autowired
	private RawFileTemplateHandlerService rawFileTemplateHandlerService;

	@PostConstruct
	public void downloadGTFSFile() {
		// Create a WebClient instance
		WebClient webClient = WebClient.create();

		// Set up a Flux with a fixed download interval
		Flux.interval(DOWNLOAD_INTERVAL).flatMap(i -> webClient.get().uri(FILE_ENDPOINT_URL).retrieve()
				.bodyToMono(byte[].class).retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1))))
				.subscribe(responseBody -> {
					try {
						log.info("GTFS file downloaded successfully. length: {}", responseBody.length);

						// Send the downloaded GTFS file as a message to the Kafka topic
						rawFileTemplateHandlerService.sendRawFile(responseBody);
					} catch (Exception e) {
						// Handle file saving errors
						log.error("Error sending GTFS file: {}", e.getMessage());
					}
				});
	}

}
