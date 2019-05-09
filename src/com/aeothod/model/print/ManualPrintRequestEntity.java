package com.aeothod.model.print;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManualPrintRequestEntity {
    private String printType;// 打印请求类型/manual手动打印,group手动停止组托,box手动停止装箱
    private String sfc;// 车间作业控制 不隐藏
    private String productionDate;// 生产日期
    private String financialYear;// 财务年份
    private String attchmentType;// 标签种类
    private String workingGroup;// 作业班组
    private String packingLine;// 打包线
    private String hiddenSfc;// 隐藏sfc 车间作业控制
    private String lineSpecification;// 线盘规格
    private String lacqueringNumber;// 包漆工号
    private String productionOrder;// 生产订单
    private String carType;// 车型
    private String salesOrder;// 销售订单
    private String itemDesc;// 物料描述
    private String customName;// 客户名称
    private String labelSpec;// 标签规格
    private String executiveStandard;// 执行标准
    private String labelModel;// 标签型号
    private String remark;// 备注
    private String pz;// 皮重
    private String mz;// 毛重
    private String electronicScale;// 电子秤
    private String switchs;// 称开关
    private String printAutomatically;// 是否自动打印
    private String jz;// 净重
    private String weightOfBoxMz;// 箱总毛重
    private String unit;// 单位
    private String weightOfBoxJz;// 箱总净重
    private String cumulativeJz;// 累计净重
    private String cumulativeNumber;// 累计箱数
    private List<String> sfcBeingBox;// 正在装箱的sfc
    private List<String> assemblyCertificate;// 正在组托的合格证
    private String barCode;// 托条码
    private String certificateCode;// 合格证号
    private String packingBatch;// 装箱批次
    private String numberOfBoxes;// 箱包装数
    private String groupBoxProgress;// 组箱进度
    private String whetherOrNot;// 是否组托
    private String numberOfPackages;// 托包装数
    private String groupSchedule;// 组托进度
    private String itemBatch;// 物料批次

}
