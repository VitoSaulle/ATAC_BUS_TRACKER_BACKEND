package it.atac.project.kafka.handlers.utils;

/**
 * Utility class containing Kafka topics used in the application.
 */
public class KafkaTopics {
	/**
	 * Kafka topic for raw file processing.
	 */
	public static final String RAW_FILE_TOPIC = "raw-files-queue";

	/**
	 * Kafka topic for parsed file processing.
	 */
	public static final String PARSED_FILE_TOPIC = "parsed-file-queue";

	// Private constructor to hide the implicit public one
	private KafkaTopics() {
		// Empty constructor
	}
}