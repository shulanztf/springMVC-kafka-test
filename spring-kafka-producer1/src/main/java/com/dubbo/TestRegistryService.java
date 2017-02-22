package com.dubbo;

/**
 * 
 * @Title: TestRegistryService
 * @Description:
 * @Author: zhaotf
 * @Since:2017年2月20日 上午11:39:22
 * @Version:1.0
 */
public interface TestRegistryService {
	/**
	 * 
	 * @param name
	 * @return String
	 */
	public String hello(String name);

	/**
	 * dubbo 集成spring 事务
	 */
	public Object setDate(String name);

}
