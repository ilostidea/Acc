package com.bit.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 示例，未进行配置，不会捕捉到异常，可直接使用Spring的SimpleMappingExceptionResolver类进行配置：
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">  
    <!-- 定义默认的异常处理页面，当该异常类型的注册时使用 -->  
    <property name="defaultErrorView" value="error"></property>  
    <!-- 定义异常处理页面用来获取异常信息的变量名，默认名为exception -->  
    <property name="exceptionAttribute" value="ex"></property>  
    <!-- 定义需要特殊处理的异常，用类名或完全路径名作为key，异常也页名作为值 -->  
    <property name="exceptionMappings">  
        <props>  
            <prop key="cn.bit.core.exception.BusinessException">error-business</prop>
            <prop key="cn.bit.core.exception.ParameterException">error-parameter</prop>
  
            <!-- 这里还可以继续扩展对不同异常类型的处理 -->  
        </props>  
    </property>  
</bean> 
 * error-business是error-business.jsp界面；error-parameter是error-parameter.jsp
 * @author ZL
 * @see spring-shiro.xml中对于SimpleMappingExceptionResolver的配置
 */
public class SimpleHandlerExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody responseBodyAnn = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if(responseBodyAnn == null){
			return super.resolveException(request, response, handlerMethod, ex);
		}
		ModelAndView mv = new ModelAndView();
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			ex.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.write( "{error:" + ex.getMessage() + "}");
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return mv;
	}
	
}
