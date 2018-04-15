package com.oll.util;

/**
 * Created by NewDarker on 2018/1/10.
 * 返回结果封装
 */
public class BaseRtM {
    private String rtMCode;
    private String rtMsg;
    private Object rtMData;

    public String getRtMCode() {
        return rtMCode;
    }

    public void setRtMCode(String rtMCode) {
        this.rtMCode = rtMCode;
    }

    public String getRtMsg() {
        return rtMsg;
    }

    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }

    public Object getRtMData() {
        return rtMData;
    }

    public void setRtMData(Object rtMData) {
        this.rtMData = rtMData;
    }
}
