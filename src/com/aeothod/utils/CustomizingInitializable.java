package com.aeothod.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aeothod.Main;
import com.aeothod.SpringConfiguration;
import com.aeothod.annotation.Autowired;
import com.aeothod.exceptions.BasicBOException;
import com.aeothod.properties.PropertiesAppUtils;
import com.aeothod.properties.PropertiesErrorUtils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author weijian.wu
 * @description:定制Initializable,使容器初始化
 * @date 2019年4月1日 上午11:15:04
 */
public abstract class CustomizingInitializable implements Initializable{
    
    public static final Logger logger=LoggerFactory.getLogger(CustomizingInitializable.class);
    
    public abstract void init(URL location, ResourceBundle resources);
    
    private ApplicationContext context;
    
    public CustomizingInitializable mainController;
    public CustomizingInitializable loginController;
    public URL mainUrl;
    public ResourceBundle mainResource;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("SPRING CONTAINER BEGIN");
        //初始化容器
        for(Field temp:this.getClass().getDeclaredFields()){
            String name=temp.getName();
            Autowired auto=temp.getAnnotation(Autowired.class);
            if(null!=auto){
                temp.setAccessible(true);
                try {
                    temp.set(this, getContext().getBean(name));
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                }
            }
        }
        logger.info("SPRING CONTAINER END");
        init(location,resources);
    }
    private ApplicationContext getContext(){
        if(null==context){
            context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        }
        return context;
    }
    public abstract Label getMessageEntity();
    
    
    /**
     * @description: 顯示信息
     * @param message
     * @param status-true樣式為綠色，false為紅色
     */
    public void showMessage(String message, boolean status) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (status) {
                    getMessageEntity().setTextFill(Color.web("#008000"));
                } else {
                    getMessageEntity().setTextFill(Color.web("#ff0000"));
                }
                getMessageEntity().setText(message);                
            }
        });
        
    }

    /**
     * @description: 顯示錯誤信息
     * @param message
     */
    public void showError(String message) {
        showMessage(message, false);
    }
    public void showError(int code) throws BasicBOException {
    	showMessage(PropertiesErrorUtils.getInstance().getProperty(String.valueOf(code)), false);
    }
    
    /**
     * @description: 顯示正確信息
     * @param message
     */
    public void showSuccess(String message) {
        showMessage(message, true);
    }
    public void showSuccess(int code) {
    	showMessage(PropertiesAppUtils.getInstance().getProperty(String.valueOf(code)), true);
    }
    /**
     * @description: 主界面打开副界面(同时把主界面对象带给副界面)
     * @param assets
     * @param title
     * @param icon
     * @return
     * @throws IOException
     */
    public CustomizingInitializable setStage(String assets,String title,String icon) throws IOException {
        Stage primaryStage = new Stage();
        URL location = Main.class.getResource(assets);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        CustomizingInitializable controller = fxmlLoader.getController();
        controller.mainController = this;
        controller.mainUrl = location;
        // 这里设置的是null(没有设置资源)
        controller.mainResource = null;
        primaryStage.setTitle(PropertiesAppUtils.getInstance().getProperty(title));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(icon)));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        return controller;
    }
    public CustomizingInitializable setStage2(String assets,String title,String icon) throws IOException {
    	Stage primaryStage = new Stage();
    	URL location = Main.class.getResource(assets);
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(location);
    	fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
    	Parent root = fxmlLoader.load();
    	CustomizingInitializable controller = fxmlLoader.getController();
    	controller.mainController = this;
    	controller.mainUrl = location;
    	// 这里设置的是null(没有设置资源)
    	controller.mainResource = null;
    	primaryStage.setTitle(PropertiesAppUtils.getInstance().getProperty(title));
    	primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(icon)));
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setResizable(false);
    	primaryStage.initModality(Modality.APPLICATION_MODAL);
    	primaryStage.show();
    	return controller;
    }
    public CustomizingInitializable setBrowser(String assets,String title,String icon,Object controllerObj) throws IOException {
        Stage primaryStage = new Stage();
        URL location = Main.class.getResource(assets);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setController(controllerObj);
        Parent root = fxmlLoader.load();
        CustomizingInitializable controller = fxmlLoader.getController();
        controller.mainController = this;
        controller.mainUrl = location;
        // 这里设置的是null(没有设置资源)
        controller.mainResource = null;
        primaryStage.setTitle(PropertiesAppUtils.getInstance().getProperty(title));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(icon)));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        return controller;
    }
    /**
     * @description: 关闭窗口(前提要设置根容器)
     */
    public void close() {
        Stage stage=(Stage)getRoot().getScene().getWindow();
        stage.close();
    }
    
    /**
     * @description: 设置根容器
     * @return
     */
    public abstract AnchorPane getRoot();
    
}
