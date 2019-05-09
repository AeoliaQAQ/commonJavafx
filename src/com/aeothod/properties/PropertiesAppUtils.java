package com.aeothod.properties;

import com.aeothod.utils.AbstractPropertiesUtils;

public class PropertiesAppUtils extends AbstractPropertiesUtils {

    private static PropertiesAppUtils utils;

    public static PropertiesAppUtils getInstance() {
        if (null == utils) {
            utils = new PropertiesAppUtils();
            utils.init();
        }
        return utils;
    }

    @Override
    protected String setPath() {
        return "config/application.properties";
    }

}
