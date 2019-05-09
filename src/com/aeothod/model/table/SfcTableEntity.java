package com.aeothod.model.table;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author weijian.wu
 * @description:正在装箱的sfc的Entity
 * @date 2019年4月2日 上午10:51:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SfcTableEntity {

    private String sfc;
    private Button del;

}
