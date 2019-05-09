package com.aeothod.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeothod.annotation.Autowired;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.model.CommonKeyValue;
import com.aeothod.model.CommonRequest;
import com.aeothod.model.browser.ReelBrowerResponseEntity;
import com.aeothod.properties.PropertiesAppUtils;
import com.aeothod.service.IReelBrowserService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * @author fengxing.wen
 * @description:线盘browserController
 * @date 2019年4月17日 上午10:57:45
 */
public class ReelBrowserController extends BrowserController<ReelBrowerResponseEntity> {
    public static Logger logger = LoggerFactory.getLogger(ReelBrowserController.class);
    @Autowired
    private IReelBrowserService reelBrowserService;
    @FXML
    private TextField wireField;//主界面线盘
    @FXML
    private Label title;
    
    @Override
    public List<CommonKeyValue> setKeys() {
        List<CommonKeyValue> keys = new ArrayList<CommonKeyValue>();
        CommonKeyValue key = new CommonKeyValue("reel", PropertiesAppUtils.getInstance().getProperty("reel"));
        title.setText(PropertiesAppUtils.getInstance().getProperty("reel"));
        keys.add(key);
        return keys;
    }
    /**
     * 设置值
     */
    @Override
    public List<ReelBrowerResponseEntity> setValues() {
        MainController main = (MainController) mainController;
        ReelBrowerResponseEntity data = new ReelBrowerResponseEntity(main.browserField.getText());
        
        List<ReelBrowerResponseEntity> result = null;
        try {
            CommonRequest<ReelBrowerResponseEntity> request = new CommonRequest<>("9031", "aeothod", data);
            result = reelBrowserService.doReelBrowse(request, mainController).getData(new ReelBrowerResponseEntity());
        } catch (BasicBOException e) {
            result = new ArrayList<ReelBrowerResponseEntity>();
            logger.debug(e.getMessage());
        }
        return result;
    }
    
    /**
     * 双击
     */
    @Override
    public void setOndbclickValue(int index) {
        MainController main = (MainController) mainController;
        if(-1!= index) {
            //获取双击行的值，当双击时给父界面赋值，并关闭browser
            String reel = tableView.getItems().get(index).getReel();
            main.browserField.setText(reel);
            close();
        }
    }
    @Override
    public void setSelectValue(int index) {
        MainController main = (MainController) mainController;
        if(-1!= index) {
            //获取双击行的值，当双击时给父界面赋值，并关闭browser
            String reel = tableView.getItems().get(index).getReel();
            main.browserField.setText(reel);
            close();
        }
        
    }


}
