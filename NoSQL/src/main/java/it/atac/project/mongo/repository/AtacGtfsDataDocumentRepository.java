package it.atac.project.mongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import it.atac.project.mongo.document.AtacGtfsDataDocument;

public interface AtacGtfsDataDocumentRepository extends MongoRepository<AtacGtfsDataDocument, ObjectId>{

}
