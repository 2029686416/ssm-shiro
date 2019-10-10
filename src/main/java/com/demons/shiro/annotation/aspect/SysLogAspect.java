package com.demons.shiro.annotation.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
 

/**
 * 系统日志，切面处理类
 * 
 * @author hbj @2017 2017年11月2日 下午8:34:00
 * @version 1.0
 * @Title:SysLogAspect
 * @Description: TODO
 * @Company:
 */
@Aspect
@Component
public class SysLogAspect {
	/**
	 * 说明：
	 * 有这么一个表DictLog，只要方法上添加了SysLog注解，每次执行带这个注解的方法就能添加信息加到日志表中
	 */
	/*@Autowired
	private LogService logService;

	@Pointcut("@annotation(com.zemso.common.annotation.SysLog)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		DictLog sysLog = new DictLog();
		SysLog slog = method.getAnnotation(SysLog.class);
		if (slog  != null) {
			// 注解上的描述
			sysLog.setOperation(slog.value());
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			String params = JSONUtils.beanToJson(args[0]);
			sysLog.setParams(params);
		} catch (Exception e) {

		}
		// 获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		// 设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		// 用户名
		String userId = "-1";
		try {
			userId = ShiroUtils.getUserId();
		} catch (Exception e) {

		}
		if (CommonUtils.isNullOrEmpty(userId) || userId.equals("-1")) {
			if (CommonUtils.isNullOrEmpty(sysLog.getParams())) {
				sysLog.setStaffId("-1");
				sysLog.setStaffName(sysLog.getParams());
			} else {
				sysLog.setStaffId("-1");
				sysLog.setStaffName("获取用户信息为空");
			}
		} else {
			sysLog.setStaffId(String.valueOf(userId));
			sysLog.setStaffName(ShiroUtils.getUserEntity().getLoginName());
		}
		sysLog.setTime(BigDecimal.valueOf(time));
		//((java.lang.BigDecimal) time);
//		sysLog.setGmtcreate(new Timestamp(new Date().getTime()));
		sysLog.setCreateTime(new Date());
		// 保存系统日志
		logService.saveLog(sysLog);
	}*/
}