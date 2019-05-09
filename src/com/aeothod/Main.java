package com.aeothod;

import java.io.File;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aeothod.controller.MainController;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.properties.PropertiesAppUtils;
import com.aeothod.utils.BaseKeyName;
import com.aeothod.utils.UrlUtils;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    public static final String OK = "OK";
    public static final String CANCEL = "CANCEL";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    MainController mainController = null;

    @Override
    public void start(Stage primaryStage) {
        try {
            URL location = getClass().getResource("/assets/Application.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent root = fxmlLoader.load();
            // 设置main属性
            primaryStage.setTitle(PropertiesAppUtils.getInstance().getProperty("main.title"));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/image/icon.jpg")));
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            mainController = fxmlLoader.getController();
//            if (checkBase()) {
//                mainController.settingAction(new ActionEvent());
//            }
            primaryStage.show();
            mainController.initData();

//            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent event) {
//                    if (null != mainController.serialport) {
//                        mainController.serialport.close();
//                        logger.info("串口关闭");
//                    }
//                }
//            });
        } catch (Exception e) {
            mainController.BasicMessage.setText(e.getMessage());
            logger.debug(e.getMessage());
        }
    }

    /**
     * @description: 检测是否存在配置地址
     * @return
     * @throws BasicBOException
     */
    private static boolean checkBase() throws BasicBOException {
        boolean result = true;
        String baseUrl = UrlUtils.getRunUrl() + BaseKeyName.configUrl;
        File file = new File(baseUrl + "/" + BaseKeyName.urlFileName);
        if (file.exists()) {
            result = false;
        }
        return result;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
