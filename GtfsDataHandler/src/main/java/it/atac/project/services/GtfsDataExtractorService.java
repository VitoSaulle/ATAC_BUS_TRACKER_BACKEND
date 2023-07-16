package it.atac.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

import it.atac.project.data.AtacGtfsData;

@Service
public class GtfsDataExtractorService {

    public List<AtacGtfsData> extractGtfsData(FeedMessage feedMessage) {
        return feedMessage.getEntityList().stream()
                .map(entity -> {
                    var gtfsData = new AtacGtfsData();
                    var vehiclePosition = entity.getVehicle().getPosition();
                    var vehicleDescriptor = entity.getVehicle().getVehicle();

                    gtfsData.setRouteId(entity.getVehicle().getTrip().getRouteId());
                    gtfsData.setLatitude(vehiclePosition.getLatitude());
                    gtfsData.setLongitude(vehiclePosition.getLongitude());
                    gtfsData.setCurrentStop(entity.getVehicle().getCurrentStopSequence());
                    gtfsData.setMatricola(vehicleDescriptor.getLabel());
                    gtfsData.setTarga(vehicleDescriptor.getLicensePlate());

                    return gtfsData;
                })
                .toList();
    }
}