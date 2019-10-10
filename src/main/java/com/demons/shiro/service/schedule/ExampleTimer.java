package com.demons.shiro.service.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.demons.shiro.service.UserService;
import com.demons.shiro.util.DateUtil;

@SuppressWarnings("all")
@PropertySource(value = { "classpath:db.properties" })
@Component
public class ExampleTimer {
	private static Logger logger = LoggerFactory.getLogger(ExampleTimer.class);
//	@Value("${spring.callUrl}")
//	private String calDataUrl;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private UserService userService;

	/**
	 * 定时任务：需要放在业务逻辑层才能起作用
	 * 比如方法controller层就不起作用
	 * 本项目业务逻辑层在service
	 * @Title: tests   
	 * @Description: TODO  
	 * @return: void      
	 * @throws
	 */
	@Scheduled(cron="${spring.calldata.cron}")   //每5秒执行一次
	public void tests() {
		System.out.println("定时任务 五秒执行一次："+DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd hh:mm:ss"));
		taskExecutor.execute(new MyThread(userService));
	}
	
}
