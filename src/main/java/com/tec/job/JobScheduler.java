/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：JobScheduler.java
 *  版本变更记录（可选）：修改日期2017年9月21日  上午11:01:22，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.job;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

import java.util.Collection;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/** 
 * @Description:
 * <p>创建日期：2017年9月21日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class JobScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(JobScheduler.class);
    private Scheduler scheduler;
    private static JobScheduler instance = new JobScheduler();
    
    private JobScheduler() {
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
			scheduler = sf.getScheduler();
			scheduler.getListenerManager().addJobListener(new Listener(), allJobs());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }
    
    public static JobScheduler getInstance() {
        return instance;
    }
    
    public JobScheduler pushJobs(Collection<JobInfo> infos) {
    	infos.forEach(info -> {
            JobDetail job = newJob(Task.class).withIdentity(info.getName(),"jbos").build();
            job.getJobDataMap().put("jobInfo", info);
            CronTrigger trigger = newTrigger().withIdentity(info.getName(), "triggers").withSchedule(cronSchedule(info.getCron())).build();
    		try {
				scheduler.scheduleJob(job,trigger);
			} catch (Exception e) {
				LOG.error("pushJobs Error : " + e.getMessage());
				e.printStackTrace();
			}
    	});
		return this;
    }
    
    public boolean isRunning(String jobKey) {
    	try {
			for(JobExecutionContext context : scheduler.getCurrentlyExecutingJobs()){
				if (context.getJobDetail().getKey().getName().equals(jobKey)) {
                    return true;
                }
			}
		} catch (SchedulerException e) {
			LOG.info("get jobs status failed");
            e.printStackTrace();
		}
    	return false;
    }
    public void start() throws SchedulerException {
        scheduler.start();
    }
    public void shutdown() {
        try {
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
        	LOG.error("shutdown exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
