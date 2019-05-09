package com.aeothod.utils;

import com.aeothod.Main;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author weijian.wu
 * @description:设置按钮样式
 * @date 2019年4月11日 上午10:39:15
 */
public class ImageUtils {

    public static void searchButton(Button button) {
        Image image;
        image = new Image(Main.class.getResourceAsStream("/assets/image/icon_browse.gif"), 15, 15, false, false);
        button.setGraphic(new ImageView(image));
    }
}
