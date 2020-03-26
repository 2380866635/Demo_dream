package com.dream.common.pojo;

import java.io.Serializable;

public class DreamResult  implements Serializable {
    //状态码 响应后的状态码
    private Integer status;
    //响应后的提示
    private String msg;
    //封装响应需要的所带的数据
    private Object data;
    //这是为登录界面写的方法
    public static DreamResult build(Integer status,String name){
        return new DreamResult(status,name);
    }
    //一旦返回有数据了  则代表登录界面请求成功
    public DreamResult(Integer status, String name) {
        this.data=null;
        this.status=status;
        this.msg=name;
    }
    public DreamResult(Object data) {
        this.data=data;
        this.msg="ok";
        this.status=200;
    }

    /**
     * 提供几个静态方法用来设置响应结果
     */
    //如果成功 返回对象
    public static DreamResult ok(Object data){
        return new DreamResult(data);
    }
    //如果失败自定义对象
    public static DreamResult build(Integer status,String name,Object data){
        return new DreamResult(status,name,data);
    }
    //如果成功且不需要数据返回
    public  static DreamResult ok(){
        return new DreamResult(null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DreamResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
