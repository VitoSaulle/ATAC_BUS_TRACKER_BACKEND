package it.atac.project.position_retriever;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class PositionRetrieverJobConfiguration {
	
	@Value("${PositionRetriever.job.cronExpression:0 * * ? * * *}")
	private String cronExpression;
 
    @Value("${PositionRetriever.job.description:Job per il recupero delle ultime posizioni}")
    private String description;
 
    @Value("${PositionRetriever.job.key:PositionRetriever}")
    private String key;
    
    @Bean
    public CronTriggerFactoryBean positionRetrieverJobTrigger(JobDetail positionRetrieverJobDetails) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(positionRetrieverJobDetails);
        triggerFactoryBean.setCronExpression(cronExpression);
        triggerFactoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return triggerFactoryBean;
    }
   
    @Bean
    public JobDetailFactoryBean positionRetrieverJobDetails(PositionRetrieverJob positionRetrieverJob) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(PositionRetrieverJob.class);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(key);
        return jobDetailFactoryBean;
    }
}
