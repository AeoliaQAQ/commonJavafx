package com.aeothod;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author weijian.wu
 * @description:SPRING的配置类
 * @date 2019年4月3日 上午9:35:28
 */
@Configuration
@ComponentScan("com.jt.service")
public class SpringConfiguration {
    public static List<String> list = new ArrayList<String>();
    public static void main(String[] args) {
        String a = "222";
        list.add(a);
        list.remove(0);
    }
}
