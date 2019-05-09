package com.aeothod.utils;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

/**
 * @author weijian.wu
 * @description:开启非FX线程更新UI(用于调用后台接口)
 * @date 2019年4月5日 上午11:05:56
 */
public abstract class ServiceIndicator extends Service<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {

            @Override
            protected String call() throws Exception{
//                try {
                    getIndicator().setVisible(true);
                    runTask();
//                } catch (Exception e) {
//                    Platform.runLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            getMessageLabel().setTextFill(Color.web("#ff0000"));
//                            getMessageLabel().setText("EROOR:" + e.getMessage());
//
//                        }
//                    });
//                } finally {
                    getIndicator().setVisible(false);
//                }
                return "success";
            }
        };
    }

    /**
     * @description: 具体跑的方法
     */
    public abstract void runTask();

    /**
     * @description: 实例化loading控件
     * @return
     */
    public abstract ProgressIndicator getIndicator();

    public abstract Label getMessageLabel();

    @Override
    public void start() {
        // 绑定Property
        getIndicator().progressProperty().unbind();
        getIndicator().progressProperty().bind(this.progressProperty());
        super.start();
    }

}
