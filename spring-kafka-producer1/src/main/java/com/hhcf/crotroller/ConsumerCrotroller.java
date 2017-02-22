package com.hhcf.crotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dubbo.TestRegistryService;

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
	// @Autowired
	@Reference
	private TestRegistryService testRegistryService;

	// @Reference
	// private UserService userService;

	@RequestMapping("/getDubboServerData")
	@ResponseBody
	public String getDubboServerData() {
		String hello = testRegistryService.hello("dubbo 消费端-1");
		System.out.println(hello);
		return hello;
	}

	/**
	 * 
	 * @return String
	 */
	@RequestMapping("/goIndex")
	@ResponseBody
	public String goIndex() {
		Object resultBean = testRegistryService.setDate("15456235896");
		System.out.println(JSON.toJSONString(resultBean));
		return JSON.toJSONString(resultBean);
	}

	// @RequestMapping("/list")
	// @ResponseBody
	// public List<User> getUsers() {
	// return userService.getUsers();
	// }
	//
	// @RequestMapping("/one")
	// @ResponseBody
	// public User getUserById() {
	// return userService.getUserByPrimaryKey("1");
	// }

	// public static void main(String[] args) throws Exception {
	// ClassPathXmlApplicationContext context = new
	// ClassPathXmlApplicationContext(
	// new String[] { "spring-dubbo.xml" });
	// context.start();
	//
	// TestRegistryService testRegistryService = (TestRegistryService) context
	// .getBean("testRegistryService");
	// for (int i = 0; i < 10; i++) {
	// String hello = testRegistryService.hello("dubbo 消费端-1，");
	// System.out.println("次数" + i + ";" + hello);
	//
	// }
	//
	// System.in.read();
	// }
}
