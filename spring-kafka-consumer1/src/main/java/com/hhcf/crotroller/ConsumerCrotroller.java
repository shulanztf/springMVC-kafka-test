package com.hhcf.crotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName: ConsumerCrotroller
 * @Description:
 * @author: zhaotf
 * @date: 2017年2月19日 下午2:34:30
 */
@Controller
@RequestMapping("/consumerCrotroller")
public class ConsumerCrotroller {

	// @Reference
	// private UserService userService;

	@RequestMapping("/getDubboServerData")
	@ResponseBody
	public String getDubboServerData() {
		// String hello = testRegistryService.hello("dubbo 消费端-1");
		// System.out.println(hello);
		return "dubbo 消费端-1";
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping("/goIndex")
	@ResponseBody
	public String goIndex() {
		// Object resultBean = testRegistryService.setDate("15456235896");
		// System.out.println(JSON.toJSONString(resultBean));
		// return JSON.toJSONString(resultBean);
		return "aaaaaaaaaaaaaaa";

	}

}
