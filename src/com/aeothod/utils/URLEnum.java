package com.aeothod.utils;

import com.aeothod.exceptions.BasicBOException;

/**
 * @author weijian.wu
 * @description:接口地址(用于RESTFUL调用)
 * @date 2019年4月11日 上午10:40:21
 */
public enum URLEnum {

    DBX("/webservice004war/rest/WeighingPacking/doInit"), // 打包线下拉框
    ACTIVITY("/webservice004war/rest/WeighingPacking/doRetrieve"), // 车间作业控制TAB
    NETWEIGHT("/webservice004war/rest/WeighingPacking/doAdd"), // 净重失去焦点
    DEVANNING("/webservice004war/rest/WeighingPacking/doRetrieveSfcByHgzh"), // 删除界面搜索
    WIRE("/webservice004war/rest/WeighingPacking/doGetPz"), // 线盘带出皮重
    SFCDEL("/webservice004war/rest/WeighingPacking/doDeleteSfc"), // 正在装箱的SFC删除按钮;
    CERTIFICATIONDEL("/webservice004war/rest/WeighingPacking/doDeleteCerCode"), // 正在组托的合格证删除按钮;
    DEVANNINGDEL("/webservice004war/rest/WeighingPacking/doUnboxing"), // 删除界面删除;
    PRINTRETRIEVE("/webservice004war/rest/WeighingPacking/doRetrieveCdbq"), // 重打标签界面搜索功能
    PRINT("/webservice004war/rest/WeighingPacking/doPrintRelabel"), // 重打标签界面打印功能
    AUTHENTICATION("/webservice004war/rest/WeighingPacking/doConnection"), // 登陆验证
    MANUALPRINT("/webservice004war/rest/WeighingPacking/doPrint"), // 手动打印
    MANUALENDBOX("/webservice004war/rest/WeighingPacking/doStopBoxing"), // 手动结束装箱
    MANUALENDPARKING("/webservice004war/rest/WeighingPacking/doStopGroup"),// 手动结束组托
    REELBROWSER("/webservice004war/rest/WeighingPacking/doReelBrowse"),// 线盘搜索帮助框
    REMARKBROWSER("/webservice004war/rest/WeighingPacking/doRemarkBrowse");// 备注搜索帮助框

    private String url;

    URLEnum(String url) {
        this.url = url;
    }

    /**
     * @description: 完整接口路径拼接
     * @return
     * @throws BasicBOException
     */
    public String getUrl() throws BasicBOException {
        return getBase() + url;
    }

    /**
     * @description: 获取基础地址
     * @return
     * @throws BasicBOException
     */
    private String getBase() throws BasicBOException {
        String base = UrlUtils.getHost();
        if (null == base || "".equals(base)) {
            throw new BasicBOException(10011);
        }
        return UrlUtils.getHost();
    }
}
