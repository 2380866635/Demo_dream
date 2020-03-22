package com.dream.service.impl;


import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener  implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage message1= (TextMessage) message;
        System.out.println(message1);
    }
}
