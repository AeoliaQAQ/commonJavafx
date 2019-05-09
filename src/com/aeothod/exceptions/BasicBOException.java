package com.aeothod.exceptions;

import com.aeothod.properties.PropertiesErrorUtils;
import com.aeothod.utils.BussinessUtils;

public class BasicBOException extends Exception {

    private static final long serialVersionUID = 1177073845381156548L;
    private String message;

    /**
     * 通用信息异常
     * 
     * @param errorCode
     * @param errorData
     * @throws BasicBOException
     */
    public BasicBOException(int errorCode, ErrorData errorData) throws BasicBOException {

        message = PropertiesErrorUtils.getInstance().getProperty(String.valueOf(errorCode));
        if (null == message) {
            // 该errorCode找不到
            message = PropertiesErrorUtils.getInstance().getProperty("10000");
        } else {
            String key = errorData.getKey();
            String value = errorData.getValue();
            if (!BussinessUtils.isEmpty(key) && !BussinessUtils.isEmpty(value)) {
                message = message.replace("%" + key + "%", value);
            }
        }
    }

    /**
     * 10001全信息打印 10001=%MESSAGE%
     * 
     * @param message
     * @throws BasicBOException
     */
    public BasicBOException(String value) throws BasicBOException {
        message = PropertiesErrorUtils.getInstance().getProperty("10001").replace("%MESSAGE%", value);
    }

    /**
     * 
     * @param error
     * @throws BasicBOException
     */
    public BasicBOException(int error) throws BasicBOException {
        message = PropertiesErrorUtils.getInstance().getProperty(String.valueOf(error));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
