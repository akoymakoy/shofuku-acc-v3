package com.shofuku.accsystem.utils;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;


public class TimestampConverter extends StrutsTypeConverter {

	 @Override
	 @SuppressWarnings("rawtypes")
     public Object convertFromString(Map context, String[] values, 
                                     Class clazz) {
	 DateFormatHelper df = new DateFormatHelper();
     Timestamp timestamp = df.parseStringToTimestamp(values[0]);
     return timestamp;
	 }
	 
	@SuppressWarnings("rawtypes")
	@Override
	 public String convertToString(Map context, Object value) {
	      DateFormatHelper df = new DateFormatHelper();
	      Timestamp ts = (Timestamp) value;
	      return ts == null ? null : df.parseTimestampToString(ts);
	 }	
}
