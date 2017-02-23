package com.interceptors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.listener.MessageListener;

/**
 * 
 * @Title: KafkaConsumerListener
 * @Description:kafka消息中间件，消费端监听，发现、拉取 消息
 * @Author: zhaotf
 * @Since:2017年2月23日 下午4:54:13
 * @Version:1.0
 */
public class KafkaConsumerListener implements MessageListener<String, String> {
	protected final Logger logger = Logger.getLogger(KafkaConsumerListener.class);

	/**
	 * 监听器自动执行该方法 消费消息 自动提交offset 执行业务代码 （high level api
	 * 不提供offset管理，不能指定offset进行消费）
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		logger.info("=============kafkaConsumer开始消费=============");
		String topic = record.topic();
		String key = record.key();
		String value = record.value();
		long offset = record.offset();
		int partition = record.partition();
		logger.info("-------------topic:" + topic);
		logger.info("-------------value:" + value);
		logger.info("-------------key:" + key);
		logger.info("-------------offset:" + offset);
		logger.info("-------------partition:" + partition);
		logger.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
	}

}
