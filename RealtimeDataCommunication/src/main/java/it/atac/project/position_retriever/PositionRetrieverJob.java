package it.atac.project.position_retriever;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@DisallowConcurrentExecution
public class PositionRetrieverJob implements Job {

	@Value("${PositionRetriever.job.enabled:false}")
	private boolean enabled;

	@Autowired
	private PositionRetrieverService positionRetrieverService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (enabled) {
			log.info("PositionRetriever started!");
			positionRetrieverService.execute();
			log.info("PositionRetriever ended!");
		} else {
			log.info("PositionRetriever NOT enabled");
		}
	}
}
