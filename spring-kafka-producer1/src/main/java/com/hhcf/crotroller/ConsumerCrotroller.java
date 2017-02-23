package com.hhcf.crotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dubbo.TestRegistryService;
import com.hhcf.service.KafkaProducerServer;

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

	@Autowired
	private KafkaProducerServer kafkaProducerServer;

	/**
	 * 通过kafka改善消息
	 * 
	 * @return Object
	 */
	@RequestMapping("/sndMesForTemplate")
	@ResponseBody
	public Object sndMesForTemplate() {
		String topic = "orderTopic";
		String value = "test";
		String ifPartition = "1";//是否使用分区 0是\1不是
		Integer partitionNum = 3;
		String role = "test";// 用来生成key
		return kafkaProducerServer.sndMesForTemplate(topic, value, ifPartition, partitionNum, role);
	}

	// @Reference
	// private UserService userService;

	@RequestMapping("/getDubboServerData")
	@ResponseBody
	public String getDubboServerData() {
		// String hello = testRegistryService.hello("dubbo 消费端-1");
		// System.out.println(hello);
		String hello = "消息中间件 kafka 生产端-1";
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
