package com.ming.kafka.server;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * 消息消费者
 */
@Component
public class Consumer {

    @KafkaListener(topics = "myKafka1")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();
            System.out.println("Receive: Topic," + topic);
            System.out.println("Receive: Record," + record);
            System.out.println("Receive: Message," + message);
        }
    }

}