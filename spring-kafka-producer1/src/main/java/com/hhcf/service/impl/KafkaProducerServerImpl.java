package com.hhcf.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.constant.KafkaMesConstant;
import com.hhcf.service.KafkaProducerServer;

/**
 * 
 * @Title: KafkaProducerServerImpl
 * @Description:
 * @Author: zhaotf
 * @Since:2017年2月23日 上午10:52:25
 * @Version:1.0
 */
@Service("kafkaProducerServer")
@Component
public class KafkaProducerServerImpl implements KafkaProducerServer {
	protected final Logger logger = Logger.getLogger(KafkaProducerServerImpl.class);
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void sendMessage(String topic, String data) {
		kafkaTemplate.setDefaultTopic(topic);
		kafkaTemplate.sendDefault(data);
	}

	@Override
	public void sendMessage(String topic, String key, String data) {
		kafkaTemplate.setDefaultTopic(topic);
		for (int i = 0; i < 3; i++) {
			ListenableFuture<SendResult<String, String>> rlst = kafkaTemplate.send(topic, key + i, data + i);
			System.out.println(JSON.toJSONString(rlst));
		}
	}

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
	@Override
	public Map<String, Object> sndMesForTemplate(String topic, Object value, String ifPartition, Integer partitionNum,
			String role) {
		String key = role + "-" + value.hashCode();
		String valueString = JSON.toJSONString(value);
		logger.info("消息中间件：kafka 生产端：" + key + ";" + valueString + ";" + ifPartition + ";" + partitionNum);

		ListenableFuture<SendResult<String, String>> result = null;

		for (int i = 0; i < 3; i++) {
			if (ifPartition.equals("0")) {
				// 表示使用分区
				int partitionIndex = getPartitionIndex(key, partitionNum);
				result = kafkaTemplate.send(topic, partitionIndex, key, valueString);
			} else {
				result = kafkaTemplate.send(topic, key + i, valueString + i);
			}
		}

		Map<String, Object> res = checkProRecord(result);
		logger.info("消息中间件：kafka 生产端：" + JSON.toJSONString(result));
		logger.info("消息中间件：kafka 生产端：" + JSON.toJSONString(res));

		return res;
	}

	/**
	 * 根据key值获取分区索引
	 * 
	 * @param key
	 * @param partitionNum
	 * @return
	 */
	private int getPartitionIndex(String key, int partitionNum) {
		if (key == null) {
			Random random = new Random();
			return random.nextInt(partitionNum);
		} else {
			int result = Math.abs(key.hashCode()) % partitionNum;
			return result;
		}
	}

	/**
	 * 检查发送返回结果record
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, Object> checkProRecord(ListenableFuture<SendResult<String, String>> res) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (res != null) {
			try {
				SendResult sr = res.get();// 检查result结果集
				/* 检查recordMetadata的offset数据，不检查producerRecord */
				Long offsetIndex = sr.getRecordMetadata().offset();
				if (offsetIndex != null && offsetIndex >= 0) {
					m.put("code", KafkaMesConstant.SUCCESS_CODE);
					m.put("message", KafkaMesConstant.SUCCESS_MES);
					return m;
				} else {
					m.put("code", KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
					m.put("message", KafkaMesConstant.KAFKA_NO_OFFSET_MES);
					return m;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
				m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
				return m;
			} catch (ExecutionException e) {
				e.printStackTrace();
				m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
				m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
				return m;
			}
		} else {
			m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
			m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
			return m;
		}
	}

}
