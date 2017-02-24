package com.interceptors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;

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
		logger.info("kafkaConsumer开始消费，键:" + JSON.toJSONString(record.key()));
		logger.info("kafkaConsumer开始消费，内容:" + JSON.toJSONString(record.value()));
		logger.info("kafkaConsumer开始消费，topic:" + JSON.toJSONString(record.topic()));
		logger.info("kafkaConsumer开始消费，offset:" + JSON.toJSONString(record.offset()));
		logger.info("kafkaConsumer开始消费，partition:" + JSON.toJSONString(record.partition()));
	}

}
