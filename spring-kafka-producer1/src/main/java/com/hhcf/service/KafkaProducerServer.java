package com.hhcf.service;

import java.util.Map;

/**
 * 
 * @Title: KafkaProducerServer
 * @Description:
 * @Author: zhaotf
 * @Since:2017年2月23日 上午10:53:47
 * @Version:1.0
 */
public interface KafkaProducerServer {
	/**
	 * kafka发送消息模板
	 * 
	 * @param topic
	 *            主题
	 * @param value
	 *            messageValue
	 * @param ifPartition
	 *            是否使用分区 0是\1不是
	 * @param partitionNum
	 *            分区数 如果是否使用分区为0,分区数必须大于0
	 * @param role
	 *            角色:bbc app erp...
	 */
	public Map<String, Object> sndMesForTemplate(String topic, Object value, String ifPartition, Integer partitionNum,
			String role);
}
