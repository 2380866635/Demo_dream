package com.dream.common.pojo;

import java.io.Serializable;

public class SearchItem implements Serializable {
    /**商品的Id*/
    private String id;
    /**商品的标题*/
    private String title;
    /*商品卖点*/
    private String sellPoint;
    /**商品图片路径*/
    private String image;
    /**商品价格*/
    private Long price;
    /**商品分类*/
    private String categoryName;
    /**商品描述*/
    private String itemDesc;
    public String[] getImages() {
        if (this.image != null && !"".equals(this.image) && this.image.indexOf(",") != -1) {
            return this.image.split(",");
        }
        return new String[]{this.image};
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
