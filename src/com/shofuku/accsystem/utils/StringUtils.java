package com.shofuku.accsystem.utils;

public class StringUtils {

	public StringUtils() {
		// TODO Auto-generated constructor stub
	}

	public String appendWithDelimiter( String original, String addition, String delimiter ) {
		if ( original.equals( "" ) ) {
			return addition;
		} else {
			return original + delimiter + addition;
		}
	}
}
