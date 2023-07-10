package it.atac.project.utils;

import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteArrayParserUtils {
	
	public static String byteArrayToStringParser(byte[] payload) {
		try {
			return new String(payload, StandardCharsets.UTF_8);
		}catch(Exception e) {
			log.error("Error while parsing byte[] to String, {}", e.getMessage());
			return new String();
		}
	}
	
	
	public static byte[] stringToByteArrayParser(String payload) {
		try {
			return payload.getBytes(StandardCharsets.UTF_8);
		}catch(Exception e) {
	//		log.error("Error while parsing string to byte[], {}", e.getMessage());
			return new byte[0];
		}
	}

}
