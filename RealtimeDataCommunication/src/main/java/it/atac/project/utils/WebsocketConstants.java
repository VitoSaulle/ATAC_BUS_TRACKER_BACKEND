package it.atac.project.utils;

/**
 * Utility class containing constants related to WebSocket configuration.
 * These constants are used for configuring WebSocket endpoints and message brokers.
 */
public class WebsocketConstants {

    /**
     * The WebSocket endpoint URL for registering Stomp endpoints.
     * Value is set to "/atac-ws".
     */
    public static final String STOMP_ENDPOINT_URL = "/atac-ws";

    /**
     * The allowed origin patterns for WebSocket connections.
     * Value is set to "*".
     */
    public static final String ALLOWED_ORIGIN_PATTERNS = "*";

    /**
     * The prefix for application destination mappings for WebSocket.
     * Value is set to "/app".
     */
    public static final String APPLICATION_DESTINATION_PREFIX = "/app";

    /**
     * The prefix for message broker destinations to push messages to clients.
     * Value is set to "/topic".
     */
    public static final String MESSAGE_BROKER_DESTINATION_PREFIX = "/topic";
    
    /**
     * The destination queue for sending position messages.
     * Value is set to "/topic/position".
     */
    public static final String POSITION_QUEUE = "/topic/position";

    // Private constructor to hide the implicit public one
    private WebsocketConstants() {
        // Empty constructor
    }
}

