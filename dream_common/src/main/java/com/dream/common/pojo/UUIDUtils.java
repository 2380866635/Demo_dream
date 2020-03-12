package com.dream.common.pojo;

import java.io.Serializable;
import java.util.Random;

public class UUIDUtils implements Serializable {
    /**
     *生成商品的随机ID
     */
    public static long getItemId(){
        //根据时间戳设置id 得到时间戳
        long millis = System.currentTimeMillis();
        //产生1-100的随机数字
        int nextInt = new Random().nextInt(99);
        //将产生的个位数前面加0
        String id=millis+String.format("%02d",nextInt);
        return new Long(id);
    }


}
