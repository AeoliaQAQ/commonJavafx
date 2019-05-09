package com.aeothod.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weijian.wu
 * @description:配置文件读取入口
 * @date 2019年4月3日 上午9:30:06
 */
public abstract class AbstractPropertiesUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractPropertiesUtils.class);
    
    private Properties properties;
    
    public void init(){
        Properties proFile=new Properties();
        InputStream input=getInputStream();
        try {
            proFile.load(new InputStreamReader(input, "UTF-8"));
            setProperties(proFile);
        } catch (Exception e) {
            logger.debug("there are not found '"+setPath()+"'");
        }
    }
    /**
     * @description: 获取application.properties文件的流
     * @return
     */
    private InputStream getInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream(setPath());
    }
    /**
     * @description: 设置application.properties文件路径
     * @return
     */
    protected abstract String setPath();
    

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    
    public Properties getProperties() {
        return properties;
    }

    /**
     * @description: 通过key值获取value
     * @param key
     * @return
     */
    public String getProperty(String key){
        return getProperties().getProperty(key);
    }
}
