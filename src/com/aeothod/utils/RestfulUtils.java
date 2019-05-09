package com.aeothod.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeothod.exceptions.BasicBOException;
import com.aeothod.exceptions.ErrorData;
import lombok.Cleanup;
import sun.net.www.protocol.http.AuthCacheImpl;
import sun.net.www.protocol.http.AuthCacheValue;

/**
 * @author weijian.wu
 * @description:RESTFUL主方法
 * @date 2019年4月11日 上午10:39:52
 */
public class RestfulUtils {

    public static final Logger logger = LoggerFactory.getLogger(RestfulUtils.class);

    public static String postAuthen(String parameter, String userName, String password, URLEnum urlEnum) throws BasicBOException {
        HttpURLConnection conn = null;
        String result = "";
        try {
            
            AuthCacheValue.setAuthCache(new AuthCacheImpl());
            Authenticator.setDefault(new RestAuthenticator(userName, password));
            
            URL url = new URL(urlEnum.getUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-type", "application/json");
            @Cleanup
            OutputStream out = conn.getOutputStream();

            logger.info("PARAMETER:" + parameter);
            if (!BussinessUtils.isEmpty(parameter)) {
                out.write(parameter.getBytes());
                out.flush();
            }
            int reponseCode = conn.getResponseCode();
            logger.info("response Code:" + reponseCode);
            if (200 == reponseCode) {
                @Cleanup
                InputStream in = conn.getInputStream();
                StringBuilder sb = new StringBuilder(50);
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = in.read(data)) != -1) {
                    String str = new String(data, 0, len, "UTF-8");
                    sb.append(str);
                }
                result = sb.toString();
                logger.info("RESULT:" + result);
            } else if (401 == reponseCode) {
                // 账号密码错误
                throw new BasicBOException(10403);
            } else {
                // 10010=发生不可预计错误，请联系管理员或者查看网络状态重试
                throw new BasicBOException(10010, new ErrorData("MESSAGE", urlEnum.getUrl() + "(" + reponseCode + ")"));
            }

        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new BasicBOException(e.getMessage());
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return result;
    }

    public static String post(String parameter, boolean isAuthen, URLEnum urlEnum, CustomizingInitializable main) throws BasicBOException {
        HttpURLConnection conn = null;
        String result = "";
        try {
//            if (isAuthen) {
//                User user = SessionUtils.getUser(main);
//                if (null != user) {
//                    AuthCacheValue.setAuthCache(new AuthCacheImpl());
//                    Authenticator.setDefault(new RestAuthenticator(user.getUserName().toUpperCase(), user.getPassword()));
//                } else {
//                    throw new BasicBOException(10002);
//                }
//            }
            URL url = new URL(urlEnum.getUrl());
            logger.info("URL:"+urlEnum.getUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-type", "application/json");
            @Cleanup
            OutputStream out = conn.getOutputStream();

            logger.info("PARAMETER:" + parameter);
            if (!BussinessUtils.isEmpty(parameter)) {
                out.write(parameter.getBytes());
                out.flush();
            }
            int reponseCode = conn.getResponseCode();
            logger.info("response Code:" + reponseCode);
            if (200 == reponseCode) {
                @Cleanup
                InputStream in = conn.getInputStream();
                StringBuilder sb = new StringBuilder(50);
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = in.read(data)) != -1) {
                    String str = new String(data, 0, len, "UTF-8");
                    sb.append(str);
                }
                result = sb.toString();
                logger.info("RESULT:" + result);
            } else if (401 == reponseCode) {
                // 账号密码错误
                throw new BasicBOException(10403);
            } else {
                // 10010=发生不可预计错误，请联系管理员或者查看网络状态重试
                throw new BasicBOException(10010, new ErrorData("MESSAGE", urlEnum.getUrl() + "(" + reponseCode + ")"));
            }

        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new BasicBOException(e.getMessage());
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return result;
    }
    public static String postTest(String parameter, boolean isAuthen, String urlEnum) throws BasicBOException {
        HttpURLConnection conn = null;
        String result = "";
        try {
            if (isAuthen) {
//                AuthCacheValue.setAuthCache(new AuthCacheImpl());
//                Authenticator.setDefault(new RestAuthenticator(user.getUserName().toUpperCase(), user.getPassword()));
            }
            URL url = new URL(urlEnum);
            logger.info("URL:"+urlEnum);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设定请求方式(默认为get)
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "utf-8");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");


            @Cleanup
            OutputStream out = conn.getOutputStream();

            logger.info("PARAMETER:" + parameter);
            if (!BussinessUtils.isEmpty(parameter)) {
                out.write(parameter.getBytes());
                out.flush();
            }
            int reponseCode = conn.getResponseCode();
            logger.info("response Code:" + reponseCode);
            if (200 == reponseCode) {
                @Cleanup
                InputStream in = conn.getInputStream();
                StringBuilder sb = new StringBuilder(50);
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = in.read(data)) != -1) {
                    String str = new String(data, 0, len, "UTF-8");
                    sb.append(str);
                }
                result = sb.toString();
                logger.info("RESULT:" + result);
            } else if (401 == reponseCode) {
                // 账号密码错误
                throw new BasicBOException(10403);
            } else {
                // 10010=发生不可预计错误，请联系管理员或者查看网络状态重试
                throw new BasicBOException(10010, new ErrorData("MESSAGE", urlEnum + "(" + reponseCode + ")"));
            }

        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new BasicBOException(e.getMessage());
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return result;
    }
    public static void main(String[] args) throws BasicBOException {
       String data= RestfulUtils.postTest("", false, "http://www.weather.com.cn/data/cityinfo/101010100.html");
       System.out.println(data);
    }
}
