package it.atac.project.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import it.atac.project.data.AtacGtfsData;
import it.atac.project.mongo.document.AtacGtfsDataDocument;

@Service
public class GtfsDataToDocumentService {

	public List<AtacGtfsDataDocument> generateGtfsDataDocuments(List<AtacGtfsData> gtfsDataList, Instant acquiredAt) {
	    return gtfsDataList.stream()
	        .map(data -> {
	            AtacGtfsDataDocument doc = new AtacGtfsDataDocument();
	            BeanUtils.copyProperties(data, doc);
	            doc.setAcquiredAt(acquiredAt);
	            return doc;
	        })
	        .toList();
	}
}
