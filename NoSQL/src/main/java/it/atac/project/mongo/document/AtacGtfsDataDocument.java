package it.atac.project.mongo.document;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "AtacGtfsDataDocument")
public class AtacGtfsDataDocument {

	@Id
	private ObjectId id;
	
	private String matricola;
	
	private String targa;
	
	private String routeId;
	
	private Float latitude;
	
	private Float longitude;
	
	private Integer currentStop;
}
