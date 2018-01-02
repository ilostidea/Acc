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

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

public class SimpleHandlerExceptionResolver extends
		SimpleMappingExceptionResolver {

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
			writer.write( ex.getMessage() );
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return mv;
	}
	
}
