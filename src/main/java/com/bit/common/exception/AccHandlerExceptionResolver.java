package com.bit.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * <bean id="exceptionHandler" class="com.bit.common.exception.AccHandlerExceptionResolver"/>
 * @author ZL
 *
 */
public class AccHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("error", ex);
        /*// 根据不同错误转向不同页面  
        if(ex instanceof BusinessException) {
            return new ModelAndView("business_error", model);
        }else if(ex instanceof ParameterException) {
            return new ModelAndView("parameter_error", model);
        } else {
            return new ModelAndView("error", model);
        }*/
        return null; //new ModelAndView("error", model);
	}

}
