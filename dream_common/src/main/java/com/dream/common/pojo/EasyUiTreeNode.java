package com.dream.common.pojo;

import java.io.Serializable;

public class EasyUiTreeNode implements Serializable {
    //树的Id
    private Long id;
    /**
     * 树的内容
     */
    private String text;
    //树的状态
    private  String state;

    public EasyUiTreeNode() {
    }

    public EasyUiTreeNode(Long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
