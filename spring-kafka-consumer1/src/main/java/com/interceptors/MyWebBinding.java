package com.interceptors;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @ClassName: MyWebBinding
 * @Description:
 * @author: zhaotf
 * @date: 2017年2月19日 下午6:40:51
 */
public class MyWebBinding implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		// 1. 使用spring自带的CustomDateEditor
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// binder.registerCustomEditor(Date.class, new
		// CustomDateEditor(dateFormat, true));
		// 2. 自定义的PropertyEditorSupport
		binder.registerCustomEditor(Date.class, new DateConvertEditor());

	}

}
