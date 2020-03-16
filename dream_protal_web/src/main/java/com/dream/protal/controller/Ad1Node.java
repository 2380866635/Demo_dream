package com.dream.protal.controller;

import java.io.Serializable;

public class Ad1Node implements Serializable {
    private String  src;
    private String href;
    private String height;
    private String width;
    private String strB;
    private String heightB;
    private String widthB;
    private String alt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStrB() {
        return strB;
    }

    public void setStrB(String strB) {
        this.strB = strB;
    }

    public String getHeightB() {
        return heightB;
    }

    public void setHeightB(String heightB) {
        this.heightB = heightB;
    }

    public String getWidthB() {
        return widthB;
    }

    public void setWidthB(String widthB) {
        this.widthB = widthB;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
