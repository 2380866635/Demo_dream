package com.dream.search.listener;

import com.dream.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemUppdateListener implements MessageListener {
    @Autowired
    private SearchItemService searchItemService;
    @Override
    public void onMessage(Message message) {
       try {
           //判断发送消息类型是否是TextMwssage
           if (message instanceof TextMessage) {
               TextMessage message1 = (TextMessage) message;
               long itemId = Long.parseLong(message1.getText());
                searchItemService.updateSearchItemById(itemId);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }

    }
}
