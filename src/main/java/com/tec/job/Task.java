/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：Task.java
 *  版本变更记录（可选）：修改日期2017年9月21日  上午11:15:09，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @Description:
 * <p>创建日期：2017年9月21日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Task implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(Task.class);
	/**
	 *(非 Javadoc) 
	 * <p>Title: execute</p> 
	 * <p>Description: </p> 
	 * @param context
	 * @throws JobExecutionException 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		JobInfo info = (JobInfo) data.get("jobInfo");
		if (data.getBoolean("cancel")) {
			LOG.error(info.getName() + " new task just be canceled, this job is in running");
			return;
		}
		String[] params = info.getParams();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS"));
        LOG.info(time + " " + info.getName() + " 执行" );
		
	}

}
