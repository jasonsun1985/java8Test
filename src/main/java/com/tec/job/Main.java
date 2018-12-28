/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：Main.java
 *  版本变更记录（可选）：修改日期2017年9月21日  下午3:13:08，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.job;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * @Description:
 * <p>创建日期：2017年9月21日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Main {
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	ClassPathXmlApplicationContext context;
	
	public Main(){
		this.context = new ClassPathXmlApplicationContext("spring/root.xml");
	}
	
	public void start() {
		JobScheduler jobScheduler = JobScheduler.getInstance();
		Optional<Set<JobInfo>> optional = Optional.of((Set<JobInfo>) context.getBean("jobs"));
		optional.ifPresent(infos -> {
			try {
				jobScheduler.pushJobs(infos).start();
			} catch (Exception e) {
                JobScheduler.getInstance().shutdown();
                System.exit(1);
			}
		});
	}
	
    public static void main(String[] args) {
        new Main().start();
    }
}
