package com.aeothod.properties;

import com.aeothod.exceptions.BasicBOException;
import com.aeothod.utils.AbstractPropertiesUtils;

public class PropertiesErrorUtils extends AbstractPropertiesUtils {

    private static PropertiesErrorUtils utils;

    public static PropertiesErrorUtils getInstance() throws BasicBOException {
        if (null == utils) {
            utils = new PropertiesErrorUtils();
            utils.init();
        }
        return utils;
    }

    @Override
    protected String setPath() {
        return "config/error.properties";
    }

}
