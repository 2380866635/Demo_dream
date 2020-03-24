package com.dream.item.pojo;

import com.dream.pojo.TbItem;

public class Item extends TbItem {
    //提供getImages方法
    public String[] getImages() {
        String image = this.getImage();
        if (image != null && !"".equals(image) && image.indexOf(",") != -1) {
            return image.split(",");
        }
        return new String[]{image};
    }

    public Item() {
    }

    //把查询到的Tbitem传到该对象中封装成自己的属性
    public Item(TbItem tbItem) {
    this.setId(tbItem.getId());
    this.setCid(tbItem.getCid());
    this.setBarcode(tbItem.getBarcode());
    this.setCreated(tbItem.getCreated());
    this.setImage(tbItem.getImage());
    this.setNum(tbItem.getNum());
    this.setPrice(tbItem.getPrice());
    this.setUpdated(tbItem.getUpdated());
    this.setTitle(tbItem.getTitle());
    this.setSellPoint(tbItem.getSellPoint());
    this.setStatus(tbItem.getStatus());
    }


}
