package it.atac.project.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.atac.project.mongo.document.AtacGtfsDataDocument;
import it.atac.project.mongo.repository.AtacGtfsDataDocumentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LatestGtfsDataRetrieverService {

    @Autowired
    private AtacGtfsDataDocumentRepository atacGtfsDataDocumentRepository;

    /**
     * Retrieves the last GTFS data documents based on the latest acquisition timestamp.
     *
     * @return List of AtacGtfsDataDocument containing the last acquired data
     * @throws Exception if an error occurs while retrieving the documents
     */
    public List<AtacGtfsDataDocument> retrieveLastData() throws Exception {
        try {
            Instant latestAcquisition = getLatestAcquisitionAt();
            log.info("Retrieved Last Acquisition: {}", latestAcquisition);

            List<AtacGtfsDataDocument> latestAcquired = getAllByLatestTimestamp(latestAcquisition);
            log.info("Retrieved {} document(s)", latestAcquired.size());

            return latestAcquired;
        } catch (Exception e) {
            log.error("Error while retrieving document(s): {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves the latest acquisition timestamp from the repository.
     *
     * @return Instant representing the latest acquisition timestamp
     * @throws IllegalStateException if no latest acquisition is found
     */
    private Instant getLatestAcquisitionAt() {
        return atacGtfsDataDocumentRepository.findLatestAcquiredAt()
                .orElseThrow(() -> new IllegalStateException("No latest acquisition found"))
                .getAcquiredAt();
    }

    /**
     * Retrieves all GTFS data documents with the given latest acquisition timestamp.
     *
     * @param latestAcquisition Instant representing the latest acquisition timestamp
     * @return List of AtacGtfsDataDocument matching the latest acquisition timestamp
     */
    private List<AtacGtfsDataDocument> getAllByLatestTimestamp(Instant latestAcquisition) {
        return atacGtfsDataDocumentRepository.findAllByLatestAcquiredAt(latestAcquisition);
    }
}
