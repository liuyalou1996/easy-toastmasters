package com.universe.toastmasters.util;

import com.universe.toastmasters.common.WebApplicationContextHolder;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author 刘亚楼
 * @date 2020/12/5
 */
public class MessageUtils {

	private static MessageSource messageSource = WebApplicationContextHolder.getBean(MessageSource.class);

	public static String getMessage(String code, Object[] args, Locale locale, String defaultValue) {
		return messageSource.getMessage(code, args, defaultValue, locale);
	}

	public static String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, locale);
	}

}
