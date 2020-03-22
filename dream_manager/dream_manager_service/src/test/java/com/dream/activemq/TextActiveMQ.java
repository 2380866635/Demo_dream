package com.dream.activemq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.IOException;


public class TextActiveMQ {
    //测试发布
    @Test
    public void testSpringQueueProdice(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath" +
                ":spring/applicationContext-activemq.xml");
        //从容器中取出
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        //取出Destion 点对点   还是一对多
        Queue queue = context.getBean(Queue.class);
        jmsTemplate.send((Destination) queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage =
                        session.createTextMessage("这是使 用Spring和ActiveMq整个发送的消息-;queue");
                return textMessage;
            }
        });
    }

    //测试消息接收器
    @Test
    public void testQueueConsumer() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath" +
                ":spring/applicationContext-activemq.xml");
        System.in.read();

    }

}
