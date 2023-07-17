package it.atac.project.utils;

import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteArrayParserUtils {

	private ByteArrayParserUtils() {
		// Private constructor to hide the implicit public one
	}

	/**
	 * Converts a byte array to a string using the UTF-8 character set.
	 *
	 * @param payload the byte array to convert
	 * @return the converted string
	 */
	public static String byteArrayToStringParser(byte[] payload) {
		try {
			return new String(payload, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("Error while parsing byte[] to String: {}", e.getMessage());
			return ""; // Empty string literal
		}
	}

	/**
	 * Converts a string to a byte array using the UTF-8 character set.
	 *
	 * @param payload the string to convert
	 * @return the converted byte array
	 */
	public static byte[] stringToByteArrayParser(String payload) {
		try {
			return payload.getBytes(StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("Error while parsing string to byte[]: {}", e.getMessage());
			return new byte[0];
		}
	}

}
