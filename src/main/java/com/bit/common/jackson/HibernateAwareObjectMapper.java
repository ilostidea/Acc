package com.bit.common.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * 
 * @author ZL
 * 解決Hibernate延遲加載的問題，避免將對象转换为json的时候将延迟加载的内容也显示出来，从而出现多条语句
 * 
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1705843215168245772L;

	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate5Module());
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);//避免将日期输出为数值
		//configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));//设置日期类型的输出格式
	}

}
