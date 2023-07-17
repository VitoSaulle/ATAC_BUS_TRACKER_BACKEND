package it.atac.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import it.atac.project.mongo.document.AtacGtfsDataDocument;
import it.atac.project.mongo.repository.AtacGtfsDataDocumentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GtfsDataPersister {
	
	@Autowired
	private AtacGtfsDataDocumentRepository atacGtfsDataDocumentRepository;

	public void persistGtfsDataDocuments(List<AtacGtfsDataDocument> documents) {
		try {
			atacGtfsDataDocumentRepository.saveAll(documents);
			log.info("Saved {} document(s).", documents.size());
		} catch (DataAccessException e) {
			log.error("Error while saving to DB: {}", e.getMessage());
			throw e;
		}
	}
}
