package com.work.library.mq;

import com.work.library.LibraryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Date 2021/1/19 14:50
 */
@SpringBootTest(classes = LibraryApplication.class)
@RunWith(SpringRunner.class)
public class TestMq {

    @Autowired
    AmqpTemplate amqpTemplate;

    String exchange = "e.test.hello";
    String queue = "q.test.hello";
    String routKey = "testHello";

    @Test
    public void sendTest() {
        String message = "hello world!";
        Message build = MessageBuilder.withBody(message.getBytes()).build();
        amqpTemplate.send(exchange,routKey,build);
//        amqpTemplate.convertAndSend(message, String.class);
    }

    @Test
    public void testMessage(){

    }
}
