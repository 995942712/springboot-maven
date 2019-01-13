package com.ming.kafka.server;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.UUID;

/**
 * 消息生产者
 */
@Component
public class Producer<T> {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息
     * @param obj
     */
    public void sendMessage(T obj){
        String str = JSON.toJSONString(obj);
        System.out.println("message: " + str);
        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send("myKafka1", str);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("生成: 消息未能发送," + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //TODO业务处理
                System.out.println("生成: 消息已成功发送,");
                System.out.println("生成: result," + stringObjectSendResult.toString());
            }
        });
    }

    /**
     * 发送消息
     */
    public void send(){
        String msg = UUID.randomUUID().toString();
        ListenableFuture listenableFuture = kafkaTemplate.send("myKafka2", msg);
        listenableFuture.addCallback(o -> System.out.println("发送成功."), throwable -> System.out.println("发送失败."));
    }

}