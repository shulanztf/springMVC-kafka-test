package com.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: ApplicationContextUtil
 * @Description:
 * @author: zhaotf
 * @date: 2017年2月19日 下午6:30:41
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

}
