/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：Listener.java
 *  版本变更记录（可选）：修改日期2017年9月21日  上午11:06:30，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.job;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/** 
 * @Description:
 * <p>创建日期：2017年9月21日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Listener implements JobListener {

	/**
	 *(非 Javadoc) 
	 * <p>Title: getName</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see org.quartz.JobListener#getName() 
	 */ 
	@Override
	public String getName() {
		return "Job_Listener";
	}

	/**
	 *(非 Javadoc) 
	 * <p>Title: jobToBeExecuted</p> 
	 * <p>Description: </p> 
	 * @param context 
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext) 
	 */ 
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobKey = context.getJobDetail().getKey().getName();
		boolean isCancel = JobScheduler.getInstance().isRunning(jobKey);
		context.getJobDetail().getJobDataMap().put("cancel", isCancel);
	}

	/**
	 *(非 Javadoc) 
	 * <p>Title: jobExecutionVetoed</p> 
	 * <p>Description: </p> 
	 * @param context 
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext) 
	 */ 
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS"))+ context.getJobDetail().getKey().getName() + " jobExecutionVetoed execute ");
	}

	/**
	 *(非 Javadoc) 
	 * <p>Title: jobWasExecuted</p> 
	 * <p>Description: </p> 
	 * @param context
	 * @param jobException 
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException) 
	 */ 
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS")) + context.getJobDetail().getKey().getName() + " jobWasExecuted execute ");
	}

}
