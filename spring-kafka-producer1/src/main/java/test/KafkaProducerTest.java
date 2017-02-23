package test;

import java.util.Map;

import com.git.kafka.producer.KafkaProducerServer;

/**
 * 
 * @ClassName: KafkaProducerTest
 * @Description:
 * @author: zhaotf
 * @date: 2017年2月22日 下午9:47:07
 */
public class KafkaProducerTest {

	public static void main(String[] args) {

		KafkaProducerServer kafkaProducer = new KafkaProducerServer();
		String topic = "orderTopic";
		String value = "test";
		String ifPartition = "0";
		Integer partitionNum = 3;
		String role = "test";// 用来生成key
		Map<String, Object> res = kafkaProducer.sndMesForTemplate(topic, value, ifPartition, partitionNum, role);

		System.out.println("测试结果如下：===============");
		String message = (String) res.get("message");
		String code = (String) res.get("code");

		System.out.println("code:" + code);
		System.out.println("message:" + message);
	}
}
