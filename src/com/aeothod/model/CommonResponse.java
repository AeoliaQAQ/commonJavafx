package com.aeothod.model;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.aeothod.utils.BussinessUtils;

/**
 * @author weijian.wu
 * @description:通用返回模板
 * @date 2019年4月2日 下午2:52:14
 */
public class CommonResponse<T> {
    private String code;
    private String message;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public List<T> getData(T t) {
        List<T> tList = null;
        if (!BussinessUtils.isEmpty(data)) {
            tList = (List<T>) JSONArray.parseArray(data, t.getClass());
        }
        return tList;
    }

}
