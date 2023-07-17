package it.atac.project.position_retriever;

import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class PositionRetrieverJobConfiguration {

	@Value("${PositionRetriever.job.cronExpression:0/30 * * ? * * *}")
	private String cronExpression;

	@Value("${PositionRetriever.job.description:Job per il recupero delle ultime posizioni}")
	private String description;

	@Value("${PositionRetriever.job.key:PositionRetriever}")
	private String key;

	@Bean(name = "PositionRetrieverJobTrigger")
	public CronTriggerFactoryBean getTrigger() {
		var factoryBean = new CronTriggerFactoryBean();
		var jobDetails = getJobDetails().getObject();

		if (jobDetails != null) {
			factoryBean.setJobDetail(jobDetails);
			factoryBean.setCronExpression(cronExpression);
			factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		} else {
			throw new IllegalStateException("Job details cannot be null");
		}

		return factoryBean;
	}

	@Bean(name = "PositionRetrieverJobDetails")
	public JobDetailFactoryBean getJobDetails() {
		var jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(PositionRetrieverJob.class);
		jobDetailFactoryBean.setDescription(description);
		jobDetailFactoryBean.setDurability(true);
		jobDetailFactoryBean.setName(key);

		return jobDetailFactoryBean;
	}
}
