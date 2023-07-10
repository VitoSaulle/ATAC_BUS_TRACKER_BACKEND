package it.atac.project.data;

import lombok.Data;

@Data
public class AtacGtfsData {
	
	private String matricola;
	
	private String targa;
	
	private String routeId;
	
	private Float latitude;
	
	private Float longitude;
	
	private Integer currentStop;
	
	//UNUSED
	private Integer occupancyPercentage;
}
