package com.aeothod.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.model.CommonResponse;
import com.aeothod.service.IAuthenticationService;
import com.aeothod.utils.RestfulUtils;
import com.aeothod.utils.URLEnum;

@Component("authenticationService")
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Override
    public CommonResponse doConnection(String userName, String password) throws BasicBOException {
        CommonResponse response = null;
        String result = RestfulUtils.postAuthen("", userName, password, URLEnum.AUTHENTICATION);
        JSONObject json = new JSONObject();
        response = json.parseObject(result, CommonResponse.class);
        return response;
    }

}
