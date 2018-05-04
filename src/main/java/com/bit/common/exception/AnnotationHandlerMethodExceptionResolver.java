package com.bit.common.exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

public class AnnotationHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {

	private String defaultErrorView;

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

	protected ModelAndView doResolveHandlerMethodException(
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception) {

		if (handlerMethod == null) {
			return null;
		}

		Method method = handlerMethod.getMethod();

		if (method == null) {
			return null;
		}

		ModelAndView mv = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);

		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
		RestController restCotrollerAnn = AnnotationUtils.findAnnotation(handlerMethod.getMethod().getDeclaringClass(), RestController.class);
		//当类上面加了@RestController，或者方法上面加了@ResponseBody后，
		if (responseBodyAnn != null || restCotrollerAnn != null) {
			try {
				/*
				//当方法上添加了@ResponseStatus注解时，直接发送该方法
				ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method, ResponseStatus.class);
				if (responseStatusAnn != null) {
					HttpStatus responseStatus = responseStatusAnn.value();
					String reason = responseStatusAnn.reason();
					if (!StringUtils.hasText(reason)) {
						response.setStatus(responseStatus.value());
					} else {
						try {
							response.sendError(responseStatus.value(), reason);
						} catch (IOException e) {
						}
					}
				}
				//test response.sendError(HttpStatus.BAD_REQUEST.value(), "请求失败！");
				*/
				return handleResponseBody(mv, request, response, handlerMethod, exception);
			} catch (Exception e) {
				return null;
			}
		}
		
		if( mv == null ) {
			mv = new ModelAndView();
		}

		if ( mv.getViewName() == null) {
			mv.setViewName(defaultErrorView);
		}
		mv.addObject("error", exception.getMessage());

		return mv;

	}

	@SuppressWarnings({ "unchecked", "rawtypes"/*, "resource"*/ })
	private ModelAndView handleResponseBody(ModelAndView mv,
			HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception exception)
			throws ServletException, IOException {
		HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}
		MediaType.sortByQualityValue(acceptedMediaTypes);
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		//Map value = mv.getModelMap();
		//Class<?> returnValueType = value.getClass();
		if (messageConverters != null) {
			for (MediaType acceptedMediaType : acceptedMediaTypes) {
				for (HttpMessageConverter messageConverter : messageConverters) {
					//if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
						messageConverter.write(exception.getMessage(), acceptedMediaType, outputMessage);
						//return new ModelAndView();
					//}
				}
			}
		}
		if (logger.isWarnEnabled()) {
			logger.warn("Could not find HttpMessageConverter that supports return type " + acceptedMediaTypes);
		}
		return new ModelAndView();
	}

}
