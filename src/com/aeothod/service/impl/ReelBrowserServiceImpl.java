package com.aeothod.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.model.CommonRequest;
import com.aeothod.model.CommonResponse;
import com.aeothod.model.browser.ReelBrowerResponseEntity;
import com.aeothod.service.IReelBrowserService;
import com.aeothod.utils.CustomizingInitializable;

/**
 * @author fengxing.wen
 * @description:线盘browser实现类
 * @date 2019年4月15日 下午4:06:10
 */
@Component("reelBrowserService")
public class ReelBrowserServiceImpl implements IReelBrowserService {

    @Override
    public CommonResponse<ReelBrowerResponseEntity> doReelBrowse(CommonRequest<ReelBrowerResponseEntity> request, CustomizingInitializable main) throws BasicBOException {
//		 CommonResponse<ReelBrowerResponseEntity> response = null;
//	        String requestJson = JSON.toJSON(request).toString();
//	        String result = RestfulUtils.post(requestJson, true, URLEnum.REELBROWSER, main);
//	        JSONObject json = new JSONObject();
//	        response = json.parseObject(result, CommonResponse.class);
        // TODO 测试数据
        CommonResponse<ReelBrowerResponseEntity> response = new CommonResponse<ReelBrowerResponseEntity>();
        response.setCode("S");
        response.setMessage("success!");
        List<ReelBrowerResponseEntity> data = Arrays.asList(new ReelBrowerResponseEntity("线盘1"));
        response.setData(JSON.toJSONString(data));
        return response;
    }

}
