package com.aeothod.service;

import com.aeothod.exceptions.BasicBOException;
import com.aeothod.model.CommonRequest;
import com.aeothod.model.CommonResponse;
import com.aeothod.model.browser.ReelBrowerResponseEntity;
import com.aeothod.utils.CustomizingInitializable;

/**
 * @author fengxing.wen
 * @description:线盘
 * @date 2019年4月15日 下午3:58:41
 */
public interface IReelBrowserService {
	
	public CommonResponse<ReelBrowerResponseEntity> doReelBrowse(CommonRequest<ReelBrowerResponseEntity> request, CustomizingInitializable main) throws BasicBOException;

}
