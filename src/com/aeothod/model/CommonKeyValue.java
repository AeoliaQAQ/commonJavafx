package com.aeothod.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author weijian.wu
 * @description:通用键值类
 * @date 2019年4月1日 下午1:23:49
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonKeyValue {
    private String key;// 实际值
    private String value;// 显示值
}
