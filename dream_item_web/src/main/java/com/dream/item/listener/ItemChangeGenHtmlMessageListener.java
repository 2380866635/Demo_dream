package com.dream.item.listener;

import com.dream.item.pojo.Item;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemDesc;
import com.dream.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemChangeGenHtmlMessageListener  implements MessageListener {
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            TextMessage message1 = (TextMessage) message;

            try {
                String text = message1.getText();
                Long itemId = Long.valueOf(text);
                //调用商品查询功能查询商品信息
                TbItem tbItem = itemService.selectByKey(itemId);
                TbItemDesc itemDescById = itemService.getItemDescById(itemId);
                this.getHtml("item.ftl",tbItem,itemDescById);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void getHtml(String telmplatename, TbItem tbItem, TbItemDesc itemDescById) throws IOException, TemplateException {
        //创建连接
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //根据模板
        Template template = configuration.getTemplate(telmplatename);
        //添加数据
        Map map = new HashMap();
        Item item = new Item(tbItem);
        System.out.println("这是商品信息"+item.getTitle());
        map.put("item",item);
        map.put("itemDesc",itemDescById);
        //生成文件位置
        FileWriter fileWriter = new FileWriter(new File("D:\\IDEA\\Demo_dream\\demo\\" + item.getId() + ".html"));
        //创建数据
        template.process(map,fileWriter);
        fileWriter.close();
    }
}
