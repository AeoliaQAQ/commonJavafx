package com.aeothod.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeothod.properties.PropertiesAppUtils;

/**
 * @author weijian.wu
 * @description:地址拼接方法
 * @date 2019年4月11日 上午10:40:47
 */
public class UrlUtils {
    
    public static final Logger logger=LoggerFactory.getLogger(UrlUtils.class);
    
    /**
     * @description: 返回main.url(主目录的值)
     * @return
     */
    public static String getRunUrl(){
        String sysPath=PropertiesAppUtils.getInstance().getProperty("main.url");
        return sysPath;
    }
    /**
     * @description: 返回设置的系统Host
     * @return
     */
    public static String getHost(){
        String baseUrl = getRunUrl() + BaseKeyName.configUrl;
        File file = new File(baseUrl + "/" + BaseKeyName.urlFileName);
        Properties pro=new Properties();
        try {
            pro.load(new FileInputStream(file));
            return pro.getProperty(BaseKeyName.URL);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return "";
    }
    public static void main(String[] args) {
        String host=getHost();
        System.out.println(host);
    }
}
