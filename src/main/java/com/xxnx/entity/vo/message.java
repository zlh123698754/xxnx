package com.xxnx.entity.vo;

public class message {
    private Integer code = 1;//1成功 0失败
    private  String mag = "成功！";
    private Object object;//回显对象

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
