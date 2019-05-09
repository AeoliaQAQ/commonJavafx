package com.aeothod.model.browser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author fengxing.wen
 * @description:备注brower实体类
 * @date 2019年4月15日 下午3:31:45
 */
@Getter
@Setter
@NoArgsConstructor
public class RemarkBrowerResponseEntity {

    private String description;
    private String remark;

    public RemarkBrowerResponseEntity(String description) {
        super();
        this.description = description;
    }

}
