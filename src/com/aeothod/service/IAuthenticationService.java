package com.aeothod.service;

import com.aeothod.exceptions.BasicBOException;
import com.aeothod.model.CommonResponse;

public interface IAuthenticationService {
    public CommonResponse doConnection(String userName, String password) throws BasicBOException;
}
