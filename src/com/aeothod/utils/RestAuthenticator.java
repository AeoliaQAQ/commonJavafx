package com.aeothod.utils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @author weijian.wu
 * @description:RESTFUL调用账号密码设置
 * @date 2019年4月11日 上午10:39:29
 */
class RestAuthenticator extends Authenticator{
    
    private String user;
    private String password;
    
    public RestAuthenticator(String user,String password){
        this.user=user;
        this.password=password;
    }
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(null==user?"":user, null==password?"".toCharArray():password.toCharArray());
    }
}
