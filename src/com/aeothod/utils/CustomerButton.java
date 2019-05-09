package com.aeothod.utils;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;

/**
 * @author weijian.wu
 * @description:特制Button,可以带参数
 * @date 2019年4月7日 下午3:24:34
 */
public class CustomerButton extends Button {

    public CustomerButton(String text) {
        super(text);
    }

    private static Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }
}
