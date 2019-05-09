package com.aeothod.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.aeothod.model.CommonKeyValue;
import com.aeothod.utils.CustomizingInitializable;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author fengxing.wen
 * @description:线盘搜索帮助框控制层
 * @date 2019年4月16日 下午3:56:42
 */
public abstract class BrowserController<T> extends CustomizingInitializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label totalCount;
    @FXML
    public TableView<T> tableView;


    /**
     * 初始化界面
     */
    @Override
    public void init(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            //初始化browser
            @Override
            public void run() {
                List<CommonKeyValue> keys = setKeys();
                //设置列
                for(CommonKeyValue key:keys) {
                    TableColumn<T, String> tableColum = new TableColumn<T, String>();
                    tableColum.setCellValueFactory(new PropertyValueFactory<>(key.getKey()));
                    tableColum.setText(key.getValue());
                    tableView.getColumns().add(tableColum);
                }
                //设置具体值
                List<T> values=setValues();
                totalCount.setText(String.valueOf(values.size()));//总计录
                tableView.setItems(FXCollections.observableArrayList(values));
            }
        });
        // 双击带出所选行
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tableView.setOnMouseClicked(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        MouseEvent e = (MouseEvent) event;
                        if (e.getClickCount()==2) {
                            setOndbclickValue(tableView.getSelectionModel().getSelectedIndex());
                            close();
                        }
                        
                    }
                    
                });
            }
        });
    }  
    public abstract void setOndbclickValue(int index);
    public abstract List<CommonKeyValue> setKeys();
    public abstract List<T> setValues();
    public abstract void setSelectValue(int index);

    @Override
    public Label getMessageEntity() {
        return null;
    }

    /**
     * @description: 取消按钮触发
     * @param event
     */
    @FXML
    public void cancelAction(Event event) {
        close();
    }
    /**
     * @description: 确认按钮
     * @param event
     */
    @FXML
    public void doConfirm(Event event) {
     // 双击带出所选行
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setSelectValue(tableView.getSelectionModel().getSelectedIndex());
                close();
            }
        });
    }
    @Override
    public AnchorPane getRoot() {
        return rootPane;
    }

}
