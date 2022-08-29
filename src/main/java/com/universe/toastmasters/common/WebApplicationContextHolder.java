package com.universe.toastmasters.common;

import org.springframework.context.ApplicationContext;

/**
 * @author 刘亚楼
 * @date 2020/12/5
 */
public class WebApplicationContextHolder {

	private static ApplicationContext applicationContext;

	public static void setWebApplicationContext(ApplicationContext applicationContext) {
		WebApplicationContextHolder.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public static <T> T getBean(String name, Class<T> type) {
		return applicationContext.getBean(name, type);
	}
}
