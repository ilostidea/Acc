package com.bit.common.log;

import com.bit.acc.model.SysLog;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.SysLogService;
import com.bit.common.util.IConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Aspect
@Component
public class LogAdvice{
	
	// 注入Service用于把日志保存到数据库
	@Resource(name="sysLogService")
	private SysLogService sysLogService;
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	// Service层切点
	@Pointcut("@annotation(com.bit.common.log.ServiceLog)")
	public void serviceAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.bit.common.log.ControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()&&@annotation(log)")
	public void doBefore(JoinPoint joinPoint, ControllerLog log) {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser user = (SysUser) session.getAttribute(IConstants.SHIRO_SESSION_USER);
		// 请求的IP
		String ip = session.getHost();
		try {
			// *========数据库日志=========*//
			SysLog sysLog = new SysLog();
			sysLog.setIp(ip);
			sysLog.setUserId(user==null ? null : user.getId());
			sysLog.setLog( log.value() );
			sysLog.setEntityName(joinPoint.getTarget().getClass().getName());
			sysLog.setOperate(joinPoint.getSignature().getName());
			StringBuffer argString = new StringBuffer();
            Object[] argTypes = ((CodeSignature)joinPoint.getSignature()).getParameterTypes();
            String[] argNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
            Object[] args = joinPoint.getArgs();
			for(int i = 0 ; i < args.length; i++ ){
			    String argType = null;
				if(argTypes[i] instanceof Class)
                    argType = ( (Class) argTypes[i] ).getName();
				else
                    argType = argTypes[i].getClass().getName();
                argString.append( argType ).append(" ");

                if(argType.indexOf("com.bit.acc.model") >=  0) {/*
                    JSONValue jsonValue = JSONMapper.toJSON(args[i]);
                    String jsonStr = jsonValue.render(true);// 是否格式化
                    argString.append(argNames[i]).append(":").append(jsonStr);*/

					/**
					 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
					 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存至File、OutputStream等不同的介质中。
					 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
					 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
					 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
					 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
					 */
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString( args[i] );
					argString.append(argNames[i]).append(":").append(json);

				} else {
                    argString.append(argNames[i]).append(":").append(args[i]);
                }
                if( i < args.length - 1)
                    argString.append(", ");
            }
			sysLog.setArgs( argString.toString() );
			sysLog.setOprtTime(new Date());
			sysLogService.save(sysLog);
			
			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 异常通知 用于拦截controller层，记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	/*@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterControllerThrowing(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e);
	}

	*//**
	 * 异常通知 用于拦截service层，记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 *//*
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterServiceThrowing(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e);
	}
	
	private void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		//User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += String.valueOf(joinPoint.getArgs()[i]) + ";";
			}
		}
		*//* ==========记录本地异常日志========== *//*
		logger.error("\r\n\t异常方法:{}\r\n\t方法参数:{}\r\n\t异常代码:{}\r\n\t异常信息:{}\r\n\t堆栈打印:{}",
				joinPoint.getTarget().getClass().getName() +"." + joinPoint.getSignature().getName(), 
				params,
				e.getClass().getName(),
				e.getMessage(),
				e.getStackTrace()
				);
		
	}*/

}
