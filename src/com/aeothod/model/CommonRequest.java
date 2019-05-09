package com.aeothod.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.utils.BaseKeyName;
import com.aeothod.utils.BussinessUtils;
import com.aeothod.utils.UrlUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonRequest<T> {
    private String site;
    private String userId;
    private String data;
    private String activity;//作业

    public CommonRequest(String site, String userId, Object data) throws BasicBOException {
        super();
        this.site = site;
        this.userId = userId;
        this.data = JSON.toJSON(data).toString();
        
        String baseUrl = UrlUtils.getRunUrl() + BaseKeyName.configUrl;
        File file = new File(baseUrl + "/" + BaseKeyName.userFileName);
        if (file.exists()) {// authentication文件存在
            Properties proFile = new Properties();
            try {
                proFile.load(new FileInputStream(file));
                String activity = proFile.getProperty(BaseKeyName.ACTIVITY);
                if(!BussinessUtils.isEmpty(activity)) {
                    this.activity=activity;
                }else {
                    throw new BasicBOException("activity missing!");
                }
            }catch (Exception e) {
                throw new BasicBOException("activity missing!");
            }

        } else {
            throw new BasicBOException("activity missing!");
        }
        
        
    }

}
