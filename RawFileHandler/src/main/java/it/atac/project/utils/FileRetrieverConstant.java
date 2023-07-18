package it.atac.project.utils;

import java.time.Duration;

/**
 * Utility class containing constants used by the FileRetrieverService for configuration.
 */
public class FileRetrieverConstant {

	/**
	 * The URL endpoint for retrieving the file.
	 * Value is set to "https://romamobilita.it/sites/default/files/rome_rtgtfs_vehicle_positions_feed.pb".
	 */
	public static final String FILE_ENDPOINT_URL = "https://romamobilita.it/sites/default/files/rome_rtgtfs_vehicle_positions_feed.pb";

	/**
	 * The interval in seconds for downloading the file.
	 * Value is set to 30 seconds.
	 */
	public static final Duration DOWNLOAD_INTERVAL_SECONDS = Duration.ofSeconds(30L);

	/**
	 * The interval in seconds for retrying the download.
	 * Value is set to 5 seconds.
	 */
	public static final Duration RETRY_INTERVAL_SECONDS = Duration.ofSeconds(5L);

	/**
	 * The number of retries for downloading the file.
	 * Value is set to 3.
	 */
	public static final Integer NUMBER_OF_RETRY = 3;

	// Private constructor to hide the implicit public one
	private FileRetrieverConstant() {
		// Empty constructor
	}
}
