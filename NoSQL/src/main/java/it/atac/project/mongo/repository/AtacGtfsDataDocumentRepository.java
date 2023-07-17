package it.atac.project.mongo.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.atac.project.mongo.document.AtacGtfsDataDocument;

public interface AtacGtfsDataDocumentRepository extends MongoRepository<AtacGtfsDataDocument, ObjectId> {

	@Aggregation(pipeline = { "{$match: {acquiredAt: ?0}}" })
	public List<AtacGtfsDataDocument> findAllByLatestAcquiredAt(Instant latestAcquired);

	@Aggregation(pipeline = { 
			"{$sort: {acquiredAt: -1}}", 
			"{$limit: 1}"
	})
	public Optional<AtacGtfsDataDocument> findLatestAcquiredAt();
}
