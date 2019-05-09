package com.aeothod.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeothod.model.CommonKeyValue;
import com.aeothod.model.table.SfcTableEntity;
import com.aeothod.utils.CodeDesc;
import com.aeothod.utils.CustomizingInitializable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainController extends CustomizingInitializable {

    public static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    public Label stringLabel;// 下拉框(字符串类型)
    @FXML
    public ChoiceBox<String> stringCheckBox;// 下拉框(字符串类型)
    public static ObservableList<String> stringValue = FXCollections.observableArrayList();

    @FXML
    public Label objLabel;// 下拉框(对象类型)
    @FXML
    public ChoiceBox<CommonKeyValue> objCheckBox;// 下拉框(对象类型)
    public static ObservableList<CommonKeyValue> objValue = FXCollections.observableArrayList();

    @FXML
    private Label dateLabel;// 日期控件
    @FXML
    private DatePicker datePicker;// 日期控件
    @FXML
    private TextField inputField;// 输入框
    @FXML
    public HBox inputHbox;

    @FXML
    private Label colorLabel;// 输入框带颜色
    @FXML
    public TextField colorField;// 输入框带颜色
    @FXML
    private Label browserLabel;
    @FXML
    public TextField browserField;
    @FXML
    private TextField dateConnectField;

    @FXML
    public TextArea codeTextField;// 代码示例
    

    @FXML
    public Label BasicMessage;

    @FXML
    public ProgressIndicator loadingProcess;// loading组件

    @FXML
    private TableView<SfcTableEntity> sfcTableView;
    @FXML
    private TableColumn<SfcTableEntity, String> sfcColumn, delColum;// 正在装箱的sfc,删除

    @FXML
    public CheckBox openCheckBox;// checkbox
    
    
    @FXML
    public AnchorPane codePane;
    @FXML
    public TextFlow codeFlow;// 代码示例
    
    @FXML
    public ScrollPane scrollPane;
//
//    @FXML
//    public TextField electronField;// 电子秤
//
//    @FXML
//    private TableView<SfcTableEntity> sfcTableView;
//    @FXML
//    private TableColumn<SfcTableEntity, String> sfcColumn, delColum;// 正在装箱的sfc,删除
//
//    private static ObservableList<SfcTableEntity> sfcTableValue = FXCollections.observableArrayList();
//
//    public SerialPort serialport;
//
//
//    @FXML
//    private Button wireButton;// 线盘按钮
//
//    @FXML
//    public ProgressIndicator loadingProcess;// loading组件
//
//    // 毛重enter事件
//    public EventHandler<Event> grossEnterListing;
//
//    // 线盘值改变事件
//    public ChangeListener<String> wireFieldListening;
//
//    // 下拉框(string类型)监听事件
//    public ChangeListener<Boolean> stringListening;

    /**
     * @description: 清除按钮
     * @param event
     */
    public void clearAction(ActionEvent event) {
        codeFlow.getChildren().removeAll(codeFlow.getChildren());
//        inputField.setText("");
//        
//
//        // 装箱批次,物料批次,电子秤
//        electron`ield.setText("");
//       
//        sfcTableValue.removeAll(sfcTableView.getItems());
//        openCheckBox.setSelected(false);
    }

    @Override
    public void init(URL location, ResourceBundle resources) {
        listeningInit();// 监听器初始化
        codePane.prefHeightProperty().bind(codeFlow.widthProperty());
//        codeTextField.setFont(Font.);
//        checkBoxInit();//下拉框初始化
//
//        // 正在装箱SFC列表初始化
//        sfcTableViewInit();
//        // 样式设置
//        ImageUtils.searchButton(wireButton);
//        siteInit();// site下拉框初始化
//        datePickerInit();// 生产日期和财务年份初始化
//        tagChoiceBoxInit();// 标签种类初始化
//        shiftChoiceBoxInit();// 作业班组初始化
//        packingChoiceBoxInit();// 打包线初始化
//
//        activityInit();// 车间作业控制初始化
//        openCheckBoxInit();// 称开关初始化
//        wireFieldInit();// 线盘初始化

    }

    public void initData() {

    }

    /**
     * @description: 监听器初始化
     */
    private void listeningInit() {
        // input输入框监听
    	ChangeListener<Boolean> inputListening=new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    commonCodeDesc(CodeDesc.INPUT);
                }

            }
        };
        inputField.focusedProperty().addListener(inputListening);
        colorField.focusedProperty().addListener(inputListening);
        inputHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                commonCodeDesc(CodeDesc.INPUT);
            }
        });

    }
    /**
     * @description显示代码
     * @param code
     */
    private void commonCodeDesc(CodeDesc code) {
        String url = code.getUrl();
        codeFlow.getChildren().removeAll(codeFlow.getChildren());
        try (InputStream stream = getClass().getResourceAsStream(url); BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));) {
            String str = null;
            while((str = buffer.readLine()) != null){
                Text text=new Text();
                if(str.startsWith("-")) {
                    str=str.replace("-", "");
                    text.setFill(Color.RED);
                    text.setText(str+"\n");
                }else {
                    text.setFill(Color.BLACK);
                    text.setText(str+"\n");
                }
                codeFlow.getChildren().add(text);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @description复制
     * @param event
     */
    public void copyAction(ActionEvent event) {
    	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	StringBuffer buffer=new StringBuffer(200);
    	for(Node text:codeFlow.getChildren()) {
    		buffer.append(((Text)text).getText());
    	}
        Transferable trans=new StringSelection(buffer.toString());
		// 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }
    /**
     * 
     * @description打开新窗口
     * @param event
     * @throws IOException 
     */
    public void newSceenAction(ActionEvent event) throws IOException {
        setStage("/assets/Application.fxml", "screen.title", "/assets/image/icon.jpg");
    }

//    /**
//     * @description: 下拉框
//     */
//    private void checkBoxInit() {
//        //下拉框(string类型)
//        stringValue.add("string1");
//        stringValue.add("string2");
//        stringValue.add("string3");
//        stringCheckBox.setItems(stringValue);
//        
//        //下拉框(对象类型)
//        objValue.add(new CommonKeyValue("key1", "value1"));
//        objValue.add(new CommonKeyValue("key2", "value2"));
//        objValue.add(new CommonKeyValue("key3", "value3"));
//        objCheckBox.setItems(objValue);
//        //设置显示值
//        objCheckBox.setConverter(new StringConverter<CommonKeyValue>() {
//
//            @Override
//            public String toString(CommonKeyValue object) {
//                return object.getValue();
//            }
//
//            @Override
//            public CommonKeyValue fromString(String string) {
//                return null;
//            }
//        });
//    }
//
//    /**
//     * @description: 监听器初始化
//     */
//    private void listeningInit() {
//        // 毛重enter监听
//        grossEnterListing = new EventHandler<Event>() {
//
//            @Override
//            public void handle(Event event) {
//                KeyEvent temp = (KeyEvent) event;
//                if (KeyCode.ENTER == temp.getCode()) {
//                    if (!openCheckBox.isSelected()) {
//                        grossListeningService();
//                    }
//                }
//
//            }
//        };
//        //下拉框监听
//        stringListening=new ChangeListener<Boolean>() {
//            
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(newValue) {
//                    codeTextField.setText("下拉框");
//                }
//                
//            }
//        };
//        stringLabel.focusedProperty().addListener(stringListening);
//        stringCheckBox.focusedProperty().addListener(stringListening);
//        
//    }
//
//    @Override
//    public Label getMessageEntity() {
//        return BasicMessage;
//    }
//
//    /**
//     * @description: 正在装箱的SFC列表初始化
//     */
//    private void sfcTableViewInit() {
//        sfcColumn.setCellValueFactory(new PropertyValueFactory<>("sfc"));
//        delColum.setCellValueFactory(new PropertyValueFactory<>("del"));
//        sfcColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        sfcTableView.setItems(sfcTableValue);
//    }
//
//  
//
//    
//
//    /**
//     * @description: 线盘初始化
//     */
//    private void wireFieldInit() {
//        MainController main = this;
//        wireField.textProperty().addListener(new ChangeListener<String>() {// 值改变事件
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!BussinessUtils.isEmpty(newValue)) {// 值改变调用
//                    // 失去焦点
//                    try {
//                        CommonRequest<WireRequestEntity> request = new CommonRequest<WireRequestEntity>(site.getValue(), SessionUtils.getUser(main).getUserName(), new WireRequestEntity(wireField.getText()));
//                        CommonResponse<WireResponseEntity> response = packingService.wireGetPZ(request, main);
//                        String code = response.getCode();
//                        String message = response.getMessage();
//                        if ("S".equals(code)) {
//                            List<WireResponseEntity> dataList = response.getData(new WireResponseEntity());
//                            if (!BussinessUtils.isEmpty(dataList)) {
//                                WireResponseEntity data = dataList.get(0);
//                                Platform.runLater(new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        tareField.setText(data.getL_PZ());
//
//                                    }
//                                });
//
//                            }
//                            showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//                        } else {
//                            showError(message);
//                        }
//                    } catch (BasicBOException e) {
//                        logger.debug(e.getMessage());
//                        showError(e.getMessage());
//                    }
//
//                }
//
//            }
//        });
//    }
//
//   
//
//
//    /**
//     * @description: 毛重处理带出净重方法
//     */
//    public void grossListeningService() {
//        MainController main = this;
//        ServiceIndicator service = new ServiceIndicator() {
//            @Override
//            public void runTask() {
//
//                CommonResponse<ManualPrintRequestEntity> response = null;
//                try {
//                    CommonRequest<ManualPrintRequestEntity> request = getRequest("manual");
//                    response = packingService.netWeightLost(request, main);
//                } catch (BasicBOException e) {
//                    showError(e.getMessage());
//                }
//                String code = response.getCode();
//                String message = response.getMessage();
//                if ("S".equals(code)) {
//                    List<ManualPrintRequestEntity> vo = response.getData(new ManualPrintRequestEntity());
//                    if (!BussinessUtils.isEmpty(vo)) {
//                        Platform.runLater(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                ManualPrintRequestEntity data = vo.get(0);
//                                // 更新界面值
//                                refreshPrint(data, message);
//                            }
//                        });
//
//                    }
//                    showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//                } else {
//                    Platform.runLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            netWeightField.setText("");
//                            showError(message);
//                        }
//                    });
//
//                }
//
//            }
//
//            @Override
//            public ProgressIndicator getIndicator() {
//                return loadingProcess;
//            }
//
//            @Override
//            public Label getMessageLabel() {
//                return BasicMessage;
//            }
//        };
//        service.start();
//    }
//
//    /**
//     * @description: 称开关初始化
//     */
//    private void openCheckBoxInit() {
//        MainController main = this;
//        openCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//
//                if (newValue) {
//                    Platform.runLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            grossWeightField.setEditable(false);
//                            // 去除enter事件
//                            grossWeightField.onKeyPressedProperty().set(null);
//                            try {
//                                logger.info("ServiceIndicator open");
//                                String com = "";
////                                List<String> portNames = SerialPortManager.findPort();
//                                String baseUrl = UrlUtils.getRunUrl() + BaseKeyName.configUrl;
//                                File file = new File(baseUrl + "/" + BaseKeyName.userFileName);
//                                if (file.exists()) {// authentication文件存在
//                                    Properties proFile = new Properties();
//                                    try {
//                                        proFile.load(new FileInputStream(file));
//                                        com = proFile.getProperty(BaseKeyName.COM);
//                                        if (BussinessUtils.isEmpty(com)) {
//                                            throw new BasicBOException(40002);
//                                        }
//                                    } catch (Exception e) {
//                                        throw new BasicBOException(40002);
//                                    }
//
//                                } else {
//                                    throw new BasicBOException(40002);
//                                }
//
//                                if (!BussinessUtils.isEmpty(com)) {
//
//                                    logger.info("串口开启");
//                                    serialport = SerialPortManager.openPort(com, 9600);
//                                    SerialPortManager.addListener(serialport, new SerialListening(serialport, main));// electronField,
//
//                                    showSuccess(30002);
//                                } else {
//                                    showError(30004);
//                                    new Thread(new Runnable() {
//
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                Thread.sleep(2000);
//                                                openCheckBox.setSelected(false);
//                                            } catch (InterruptedException e) {
//                                                logger.debug(e.getMessage());
//                                            }
//
//                                        }
//                                    }).start();
//                                }
//                            } catch (NoSuchPort e) {
//                                logger.debug("Error:" + e.toString());
//                                showError("Error:" + e.toString());
//                            } catch (PortInUse e) {
//                                logger.debug("Error:" + e.toString());
//                                showError("Error:" + e.toString());
//                            } catch (Exception e) {
//                                logger.debug("Error:" + e.getMessage());
//                                showError("Error:" + e.getMessage());
//                            }
//
//                        }
//                    });
//
//                } else {
//
//                    Platform.runLater(new Runnable() {
//
//                        @Override
//                        public void run() {
//
//                            if (null != serialport) {
//                                serialport.removeEventListener();
//                                serialport.close();
//                                logger.info("串口关闭");
//                            }
//                            grossWeightField.setEditable(true);
//                            grossWeightField.onKeyPressedProperty().set(grossEnterListing);
//                            showSuccess(30001);
//                            electronField.setText("");
//                            grossWeightField.setText("");
//                            electronField.setBackground(new Background(new BackgroundFill(Color.valueOf("#ff8c00"), null, null)));
//
//                        }
//                    });
//
//                }
//
//            }
//        });
//    }
//
//    /**
//     * @description: 是否组托传值转换
//     * @return
//     * @throws BasicBOException
//     */
//    private String getWhetherValue() {
//
//        String text = whetherField.getText();
//        if (BussinessUtils.isEmpty(text)) {
//            text = "";
//        } else {
//            if (PropertiesAppUtils.getInstance().getProperty("common.Y").equals(text)) {
//                text = "Y";
//            } else {
//                text = "N";
//            }
//        }
//        return text;
//    }
//
//    /**
//     * @description: 是否自动打印值转换
//     * @return
//     */
//    private String getAutoPrintCheckBox() {
//        return autoPrintCheckBox.isSelected() ? "true" : "false";
//    }
//
//    /**
//     * @description:称开关值转换
//     * @return
//     */
//    private String getOpenCheckBox() {
//        return openCheckBox.isSelected() ? "true" : "false";
//    }
//
//    /**
//     * @description:车间作业控制初始化
//     */
//    private void activityInit() {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                activity.requestFocus();
//            }
//        });
//        // 车间作业控制enter触发
//        activity.setOnKeyPressed(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                KeyEvent temp = (KeyEvent) event;
//                if (KeyCode.ENTER == temp.getCode()) {
//                    ServiceIndicator task = new ServiceIndicator() {
//                        @Override
//                        public void runTask() {
//                            try {
//                                activityTab();
//                            } catch (BasicBOException e) {
//                                String message=e.getMessage();
//                                try {
//                                    if(message.contains("12001")) {
//                                        message=PropertiesErrorUtils.getInstance().getProperty("12001");
//                                    }else if(message.contains("31226")) {
//                                        message=PropertiesErrorUtils.getInstance().getProperty("31226");
//                                    }else if(message.contains("31211")) {
//                                        message=PropertiesErrorUtils.getInstance().getProperty("31211");
//                                    }
//                                }catch (Exception e1) {
//                                    message=e1.getMessage();
//                                }
//                                logger.debug(message);
//                                showError(message);
//                            }
//                        }
//
//                        @Override
//                        public ProgressIndicator getIndicator() {
//                            return loadingProcess;
//                        }
//
//                        @Override
//                        public Label getMessageLabel() {
//                            return BasicMessage;
//                        }
//                    };
//                    task.start();
//                }
//            }
//        });
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description:车间作业控制Tab触发
//     */
//    private void activityTab() throws BasicBOException {
//        if (BussinessUtils.isEmpty(packingChoiceBox.getValue().getValue())) {
//            // 打包线不可为空或数据错误
//            throw new BasicBOException(20001);
//        }
//        if (BussinessUtils.isEmpty(boxNumberField.getText())) {
//            // 箱包装数不可为空或数据错误
//            throw new BasicBOException(20002);
//        }
//        if (BussinessUtils.isEmpty(whetherField.getText())) {
//            // 是否组托不可为空或数据错误
//            throw new BasicBOException(20003);
//        }
//        if (BussinessUtils.isEmpty(packingNumberField.getText())) {
//            // 托包装数不可为空或数据错误
//            throw new BasicBOException(20004);
//        }
//        String sfc = activity.getText();
//        ActivityRequestEntiry entity = new ActivityRequestEntiry();
//        entity.setSfc(sfc);
//        entity.setProductionDate(datePicker.getValue().toString());
//        entity.setUserName(SessionUtils.getUserRef());
//        entity.setPackingLine(packingChoiceBox.getValue().getValue());
//        entity.setWhetherOrNot(getWhetherValue());
//        entity.setNumberOfPackages(packingNumberField.getText());// 托包装
//        entity.setNumberOfBoxes(boxNumberField.getText());// 箱包装
//        entity.setCarType(carField.getText());
//        entity.setGroupSchedule(groupPcessField.getText());
//        entity.setBarCode(barCodeField.getText());
//        entity.setJz(netWeightField.getText());
//        entity.setPz(tareField.getText());
//        entity.setScaleSwitch(getOpenCheckBox());
//        entity.setAutomaticPrinting(getAutoPrintCheckBox());
//        CommonResponse<ActivityVOEntity> response = activityService.tabAction(new CommonRequest<ActivityRequestEntiry>(site.getValue(), SessionUtils.getUser(this).getUserName().toUpperCase(), entity), this);
//        String code = response.getCode();
//        String message = response.getMessage();
//        if ("S".equals(code)) {
//            List<ActivityVOEntity> data = response.getData(new ActivityVOEntity());
//            if (!BussinessUtils.isEmpty(data)) {
//                Platform.runLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        ActivityVOEntity ac = data.get(0);
//                        printNumberField.setText(ac.getL_USER_NUMBER());
//                        orderField.setText(ac.getL_PRODUCTION_ORDER());
//                        carField.setText(ac.getL_CAR_TYPE());
//                        soldOrderField.setText(ac.getL_SHOW_ORDER());
//                        itemDescField.setText(ac.getL_ITEM_DESCRIPTION());
//                        customerField.setText(ac.getL_CUSTOM_NAME());
//                        tagStandField.setText(ac.getL_LABEL_SPEC());
//                        runStandField.setText("");// 执行标准
//                        tagModelField.setText(ac.getL_LABEL_MODEL());
//                        unitField.setText(ac.getL_UNIT());
//                        certiNumberField.setText(ac.getL_HGZH());
//                        boxBatchField.setText(ac.getL_ZXPC());
//                        boxProcessField.setText(ac.getL_ZXJD());
//                        itemBatchField.setText(ac.getL_WLPC());
//                        barCodeField.setText(ac.getL_TTM());
//                        tareField.setText(tareField.getText());
//                        groupPcessField.setText(ac.getL_ZTJD());
//                        netWeightField.setText("");
//                        // 清空车间作业控制TextField,写入隐藏hiddenSFCField,焦点控制
//                        hiddenSFCField.setText(sfc);
//                        activity.setText("");
//                        showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//                    }
//
//                });
//
//            }
//        } else {
//
//            showError(message);
//
//        }
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                activity.requestFocus();
//            }
//        });
//
//    }
//
//    /**
//     * @description: 初始化生产日期和财务年份
//     */
//    private void datePickerInit() {
//        LocalDate time = LocalDate.now();
//        datePicker.setValue(time);
//        fanField.setText(String.valueOf(time.getYear()));
//    }
//
    /**
     * @description: 生产日期改变财务年份
     * @param event
     */
    public void dateChange(ActionEvent event) {
//        String date = datePicker.getEditor().getText();
//        String year = "";
//        if (null != date && date.length() > 0) {
//            year = date.substring(0, 4);
//        }
//        fanField.setText(year);
    }

//
//    /**
//     * @description:初始化site下拉框
//     */
//    private void siteInit() {
//        ObservableList<String> value = FXCollections.observableArrayList();
//        List<String> items = Arrays.asList("9150");
//        value.addAll(items);
//        site.setItems(value);
//        site.setValue("9150");
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description: 初始化标签种类
//     */
//    private void tagChoiceBoxInit() {
//        tagChoiceBox.setItems(tagChoiceValue);
//        tagChoiceBox.setConverter(new StringConverter<PackingEntity>() {
//
//            @Override
//            public String toString(PackingEntity object) {
//                return object.getDescription();
//            }
//
//            @Override
//            public PackingEntity fromString(String string) {
//                return null;
//            }
//        });
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description: 作业班组
//     */
//    private void shiftChoiceBoxInit() {
//        shiftChoiceBox.setItems(shiftChoiceValue);
//        shiftChoiceBox.setConverter(new StringConverter<PackingEntity>() {
//
//            @Override
//            public String toString(PackingEntity object) {
//                return object.getDescription();
//            }
//
//            @Override
//            public PackingEntity fromString(String string) {
//                return null;
//            }
//        });
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description: 初始化数据
//     */
//    public void initData() throws BasicBOException {
//        logger.info("INIT DATA BEGIN");
//        try {
//            // 设置下方登录信息
//            User user = SessionUtils.getUser((MainController) mainController);
//            String userName = user.getUserName();
//            if (BussinessUtils.isEmpty(userName)) {
//                userMessage.setText(PropertiesAppUtils.getInstance().getProperty("login.none"));
//            } else {
//                userMessage.setText(PropertiesAppUtils.getInstance().getProperty("login.userName") + userName);
//            }
//            // 打包线数据初始化
//            packingLineDataInit();
//            tagDataInit();
//            shiftDataInit();
//        } catch (BasicBOException e) {
//            showError((e.getMessage()));
//            logger.debug(e.getMessage());
//        }
//        logger.info("INIT DATA END");
//    }
//
//    /**
//     * @description: 作业班组数据获取
//     */
//    private void shiftDataInit() {
//        List<PackingEntity> list;
//        try {
//            list = packingService.getWorkGroup("9150", this);
//            shiftChoiceValue.removeAll(shiftChoiceValue);
//            shiftChoiceValue.addAll(list);
//
//            if (list.size() > 0) {
//                shiftChoiceBox.setValue(list.get(0));
//            }
//        } catch (BasicBOException e) {
//            showError(e.getMessage());
//        }
//    }
//
//    /**
//     * @description: 标签种类数据获取
//     */
//    public void tagDataInit() {
//        List<PackingEntity> list;
//        try {
//            list = packingService.getTag("9150", this);
//            tagChoiceValue.removeAll(tagChoiceValue);
//            tagChoiceValue.addAll(list);
//
//            if (list.size() > 0) {
//                tagChoiceBox.setValue(list.get(0));
//            }
//        } catch (BasicBOException e) {
//            showError(e.getMessage());
//        }
//    }
//
//    // 打包线数据初始化
//    private void packingLineDataInit() throws BasicBOException {
//        List<PackingEntity> list = packingService.getPacking("9150", this);
//        packingValue.removeAll(packingValue);
//        packingValue.addAll(list);
//
//        if (list.size() > 0) {
//            packingChoiceBox.setValue(list.get(0));
//        }
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description: 初始化打包线
//     */
//    private void packingChoiceBoxInit() {
//        packingChoiceBox.setItems(packingValue);
//        packingChoiceBox.setConverter(new StringConverter<PackingEntity>() {
//
//            @Override
//            public String toString(PackingEntity object) {
//                return object.getDescription();
//            }
//
//            @Override
//            public PackingEntity fromString(String string) {
//                return null;
//            }
//        });
//
//    }
//
//    /**
//     * @description: setting按钮触发
//     * @param event
//     * @throws IOException
//     */
//    @FXML
//    public void settingAction(ActionEvent event) throws IOException {
//        setStage("/assets/Setting.fxml", "property.title", "/assets/image/icon.jpg");
//
//    }
//
//    /**
//     * @description: 设置组托信息按钮触发
//     * @param event
//     * @throws IOException
//     */
//    public void groupSupportAction(ActionEvent event) throws IOException {
//        setStage("/assets/GroupProcess.fxml", "groupProcess.title", "/assets/image/icon.jpg");
//    }
//
//    /**
//     * @description: 拆箱按钮触发
//     * @param event
//     * @throws IOException
//     */
//    public void devanningAction(ActionEvent event) throws IOException {
//        setStage("/assets/Devanning.fxml", "devanning.title", "/assets/image/icon.jpg");
//    }
//
//    /**
//     * @throws IOException
//     * @description: 修改批次信息按钮触发
//     */
//    @FXML
//    private void modifyBatchAction(Event event) throws IOException {
//        BatchController controller = (BatchController) setStage2("/assets/Batch.fxml", "batch.title", "/assets/image/icon.jpg");
//        controller.initProperty();
//    }
//
//    /**
//     * @description: 重打标签按钮
//     * @param event
//     * @throws IOException
//     */
//    @FXML
//    private void printAction(Event event) throws IOException {
//        PrintController controller = (PrintController) setStage2("/assets/Print.fxml", "print.title", "/assets/image/icon.jpg");
//        controller.initData();
//    }
//
    @FXML
    private void wireAction() {
//        setBrowser("/assets/Browser.fxml", "browser.title", "/assets/image/icon.jpg", new ReelBrowserController());
//        System.out.println("我是线盘");
    }
//
//    @FXML
//    private void recordAction() throws IOException {
//        setBrowser("/assets/Browser.fxml", "browser.title", "/assets/image/icon.jpg", new RemarkBrowserController());
//        System.out.println("我是备注");
//    }
//
//    /**
//     * @description: 清除累计按钮
//     * @param event
//     */
//    @FXML
//    private void totalCleanAction(Event event) {
//        totalNetField.setText("0");
//        totalBoxField.setText("0");
//    }
//
//    /**
//     * @description: 手动打印
//     * @param event
//     */
//    @FXML
//    private void manualPrintAction(Event event) {
//        logger.info("-----manualPrintAction-----");
//        // 打印请求类型：manual手动打印,group手动停止组托,box手动停止装箱
//        manualPrint("manual");
//    }
//
//    /**
//     * @description: 手动打印主方法
//     */
//    public void manualPrint(String type) {
//        MainController main = this;
//        ServiceIndicator service = new ServiceIndicator() {
//
//            @Override
//            public void runTask() {
//
//                try {
//                    CommonRequest<ManualPrintRequestEntity> request = getRequest(type);
//                    CommonResponse<ManualPrintRequestEntity> response = printService.doManualPrint(request, main);
//                    String code = response.getCode();
//                    String message = response.getMessage();
//                    if ("S".equals(code)) {
//
//                        List<ManualPrintRequestEntity> list = response.getData(new ManualPrintRequestEntity());
//                        if (!BussinessUtils.isEmpty(list)) {
//                            ManualPrintRequestEntity data = list.get(0);
//                            // 更新界面值
//                            refreshPrint(data, message);
//                        }
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                activity.requestFocus();
//                            }
//                        });
//                    } else {
//                        logger.debug(message);
//                        showError(message);
//                    }
//                } catch (Exception e) {
//                    logger.debug(e.getMessage());
//                    showError(e.getMessage());
//                }
//                Platform.runLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        activity.requestFocus();
//                    }
//                });
//            }
//
//            @Override
//            public ProgressIndicator getIndicator() {
//                return loadingProcess;
//            }
//
//            @Override
//            public Label getMessageLabel() {
//                return BasicMessage;
//            }
//        };
//        service.start();
//    }
//
//    /**
//     * @description: 手动结束装箱按钮触发
//     * @param event
//     * @throws BasicBOException
//     */
//    @FXML
//    private void manualFinishBoxAction(Event event) throws BasicBOException {
//        logger.info("-----manualFinishBoxAction-----");
//        // 打印请求类型：manual手动打印,group手动停止组托,box手动停止装箱
//        manualPrint("box");
//    }
//
//    /**
//     * @throws BasicBOException
//     * @description: 手动结束组托按钮触发 @param event @throws
//     */
//    @FXML
//    private void manualFinishGroupAction(Event event) throws BasicBOException {
//        logger.info("-----manualFinishGroupAction-----");
//        // 打印请求类型：manual手动打印,group手动停止组托,box手动停止装箱
//        manualPrint("group");
//    }
//
//    /**
//     * @description: 登陆
//     * @param event
//     * @throws IOException
//     */
//    @FXML
//    private void loginAction(Event event) throws IOException {
//        setStage("/assets/Login.fxml", "login.title", "/assets/image/icon.jpg");
//    }
//
//    /**
//     * @description: 打印通用对象
//     * @param type
//     *            打印请求类型：manual手动打印,group手动停止组托,box手动停止装箱
//     * @return
//     * @throws BasicBOException
//     */
//    private CommonRequest<ManualPrintRequestEntity> getRequest(String type) throws BasicBOException {
//        ManualPrintRequestEntity requestEntity = new ManualPrintRequestEntity();
//        requestEntity.setPrintType(type);
//        requestEntity.setHiddenSfc(hiddenSFCField.getText());
//        requestEntity.setSfc(activity.getText());
//        requestEntity.setMz(grossWeightField.getText());
//        requestEntity.setPz(tareField.getText());
//        requestEntity.setCertificateCode(certiNumberField.getText());
//        requestEntity.setJz(netWeightField.getText());
//        requestEntity.setItemBatch(itemBatchField.getText());
//        requestEntity.setWorkingGroup(shiftChoiceBox.getValue().getValue());
//        requestEntity.setLacqueringNumber(printNumberField.getText());
//        requestEntity.setCarType(carField.getText());
//        requestEntity.setProductionDate(datePicker.getValue().toString());
//        requestEntity.setLineSpecification(wireField.getText());
//        requestEntity.setUnit(unitField.getText());
//        requestEntity.setGroupBoxProgress(boxProcessField.getText());
//        requestEntity.setLabelSpec(tagStandField.getText());
//        requestEntity.setLabelModel(tagModelField.getText());
//        requestEntity.setCustomName(customerField.getText());
//        requestEntity.setFinancialYear(fanField.getText());
//        requestEntity.setWhetherOrNot(getWhetherValue());
//        requestEntity.setBarCode(barCodeField.getText());
//        requestEntity.setGroupSchedule(groupPcessField.getText());
//        requestEntity.setPackingBatch(boxBatchField.getText());
//        requestEntity.setPackingLine(packingChoiceBox.getValue().getValue());
//        requestEntity.setWeightOfBoxMz(boxWireField.getText());
//        requestEntity.setWeightOfBoxJz(boxNetField.getText());
//        requestEntity.setCumulativeJz(totalNetField.getText());
//        requestEntity.setCumulativeNumber(totalBoxField.getText());
//        requestEntity.setRemark(recordField.getText());
//        requestEntity.setAttchmentType(tagChoiceBox.getValue().getValue());
//        requestEntity.setProductionOrder(orderField.getText());
//        requestEntity.setSalesOrder(soldOrderField.getText());
//        requestEntity.setItemDesc(itemDescField.getText());
//        requestEntity.setExecutiveStandard(runStandField.getText());
//        requestEntity.setElectronicScale(electronField.getText());
//        requestEntity.setSwitchs(getOpenCheckBox());
//        requestEntity.setPrintAutomatically(getAutoPrintCheckBox());
//        requestEntity.setSfcBeingBox(getsfcBeingBOxList());// 正在装箱的sfc
//        requestEntity.setAssemblyCertificate(getCertiBOxList());// 正在组托的合格证
//        requestEntity.setNumberOfBoxes(boxNumberField.getText());// 箱包装数
//        requestEntity.setNumberOfPackages(packingNumberField.getText());// 托包装数
//        List<ManualPrintRequestEntity> entityList = Arrays.asList(requestEntity);
//        CommonRequest<ManualPrintRequestEntity> request = new CommonRequest<ManualPrintRequestEntity>(site.getValue(), SessionUtils.getUser(this).getUserName(), entityList);
//        return request;
//    }
//
//    /**
//     * @description: 获取界面值(自定义毛重净重)
//     * @param type
//     * @param mz
//     * @param jz
//     * @return
//     * @throws BasicBOException
//     */
//    public CommonRequest<ManualPrintRequestEntity> getRequest(String type, String mz, String jz, String autoPrint) throws BasicBOException {
//        ManualPrintRequestEntity requestEntity = new ManualPrintRequestEntity();
//        requestEntity.setPrintType(type);
//        requestEntity.setHiddenSfc(hiddenSFCField.getText());
//        requestEntity.setSfc(activity.getText());
//        if (BussinessUtils.isEmpty(mz)) {
//            requestEntity.setMz(grossWeightField.getText());
//        } else {
//            requestEntity.setMz(mz);
//        }
//        requestEntity.setPz(tareField.getText());
//        requestEntity.setCertificateCode(certiNumberField.getText());
//        if (BussinessUtils.isEmpty(jz)) {
//            requestEntity.setJz(netWeightField.getText());
//        } else {
//            requestEntity.setJz(jz);
//        }
//        requestEntity.setItemBatch(itemBatchField.getText());
//        requestEntity.setWorkingGroup(shiftChoiceBox.getValue().getValue());
//        requestEntity.setLacqueringNumber(printNumberField.getText());
//        requestEntity.setCarType(carField.getText());
//        requestEntity.setProductionDate(datePicker.getValue().toString());
//        requestEntity.setLineSpecification(wireField.getText());
//        requestEntity.setUnit(unitField.getText());
//        requestEntity.setGroupBoxProgress(boxProcessField.getText());
//        requestEntity.setLabelSpec(tagStandField.getText());
//        requestEntity.setLabelModel(tagModelField.getText());
//        requestEntity.setCustomName(customerField.getText());
//        requestEntity.setFinancialYear(fanField.getText());
//        requestEntity.setWhetherOrNot(getWhetherValue());
//        requestEntity.setBarCode(barCodeField.getText());
//        requestEntity.setGroupSchedule(groupPcessField.getText());
//        requestEntity.setPackingBatch(boxBatchField.getText());
//        requestEntity.setPackingLine(packingChoiceBox.getValue().getValue());
//        requestEntity.setWeightOfBoxMz(boxWireField.getText());
//        requestEntity.setWeightOfBoxJz(boxNetField.getText());
//        requestEntity.setCumulativeJz(totalNetField.getText());
//        requestEntity.setCumulativeNumber(totalBoxField.getText());
//        requestEntity.setRemark(recordField.getText());
//        requestEntity.setAttchmentType(tagChoiceBox.getValue().getValue());
//        requestEntity.setProductionOrder(orderField.getText());
//        requestEntity.setSalesOrder(soldOrderField.getText());
//        requestEntity.setItemDesc(itemDescField.getText());
//        requestEntity.setExecutiveStandard(runStandField.getText());
//        requestEntity.setElectronicScale(electronField.getText());
//        requestEntity.setSwitchs(getOpenCheckBox());
//        if (BussinessUtils.isEmpty(autoPrint)) {
//            requestEntity.setPrintAutomatically(getAutoPrintCheckBox());
//        } else {
//            requestEntity.setPrintAutomatically(autoPrint);
//        }
//
//        requestEntity.setSfcBeingBox(getsfcBeingBOxList());// 正在装箱的sfc
//        requestEntity.setAssemblyCertificate(getCertiBOxList());// 正在组托的合格证
//        requestEntity.setNumberOfBoxes(boxNumberField.getText());// 箱包装数
//        requestEntity.setNumberOfPackages(packingNumberField.getText());// 托包装数
//        List<ManualPrintRequestEntity> entityList = Arrays.asList(requestEntity);
//        CommonRequest<ManualPrintRequestEntity> request = new CommonRequest<ManualPrintRequestEntity>(site.getValue(), SessionUtils.getUser(this).getUserName(), entityList);
//        return request;
//    }
//
//    /**
//     * @description: 打印更新界面值
//     * @param data
//     * @param message
//     */
//    public void refreshPrint(ManualPrintRequestEntity data, String message) {
//        MainController main = this;
//        // 更新界面值
//        Platform.runLater(new Runnable() {
//
//            @Override
//            public void run() {
//                // a、更新进度值
//                boxProcessField.setText(data.getGroupBoxProgress());
//                groupPcessField.setText(data.getGroupSchedule());
//                netWeightField.setText(data.getJz());
//
//                // b、其他
//                boxWireField.setText(data.getWeightOfBoxMz());// 箱总毛重
//                boxNetField.setText(data.getWeightOfBoxJz());// 箱总净重
//                totalNetField.setText(data.getCumulativeJz());// 累计净重
//                totalBoxField.setText(data.getCumulativeNumber());// 累计箱数
//                boxNumberField.setText(data.getNumberOfBoxes()); // 箱包装数
//                packingNumberField.setText(data.getNumberOfPackages());// 托包装书
//
//            }
//        });
//
//        // c、更新列表
//        List<String> sfcTableList = data.getSfcBeingBox();
//        List<String> certiTableList = data.getAssemblyCertificate();
//
//        // 先清空
//        sfcTableValue.removeAll(sfcTableValue);
//        if (!BussinessUtils.isEmpty(sfcTableList)) {
//            for (String sfc : sfcTableList) {
//                CustomerButton delBtn = new CustomerButton(PropertiesAppUtils.getInstance().getProperty("sfcTableView.column2"));
//                delBtn.put("sfc", sfc);
//                SfcTableEntity entity = new SfcTableEntity(sfc, delBtn);
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        delBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//                            @Override
//                            public void handle(ActionEvent event) {
//                                ServiceIndicator service = new ServiceIndicator() {
//
//                                    @Override
//                                    public void runTask() {
//                                        PackingDelRequestEntity packEntity = new PackingDelRequestEntity(boxProcessField.getText(), certiNumberField.getText(), sfc);
//
//                                        try {
//                                            CommonRequest<PackingDelRequestEntity> request = new CommonRequest<PackingDelRequestEntity>(site.getValue(), SessionUtils.getUser(main).getUserName(), packEntity);
//                                            CommonResponse<PackingDelResponseEntity> response = activityService.delAction(request, main);
//                                            String code = response.getCode();
//                                            String message = response.getMessage();
//                                            if ("S".equals(code)) {// 成功后删除这行数据并更新装箱进度
//                                                List<PackingDelResponseEntity> data = response.getData(new PackingDelResponseEntity());
//                                                if (!BussinessUtils.isEmpty(data)) {
//                                                    PackingDelResponseEntity entity = data.get(0);
//                                                    boxProcessField.setText(entity.getGroupBoxProgress());
//                                                }
//                                                sfcTableValue.remove(entity);
//                                                showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//                                            } else {
//                                                logger.debug(response.getMessage());
//                                                showError(message);
//
//                                            }
//                                        } catch (Exception e) {
//                                            logger.debug(e.getMessage());
//                                            showError(e.getMessage());
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public ProgressIndicator getIndicator() {
//                                        return loadingProcess;
//                                    }
//
//                                    @Override
//                                    public Label getMessageLabel() {
//                                        return BasicMessage;
//                                    }
//                                };
//                                service.start();
//                            }
//                        });
//
//                    }
//                });
//                sfcTableValue.add(entity);
//            }
//        }
//
//        certiTableValue.removeAll(certiTableValue);
//        if (!BussinessUtils.isEmpty(certiTableList)) {
//            for (String certification : certiTableList) {
//
//                CustomerButton delBtn = new CustomerButton(PropertiesAppUtils.getInstance().getProperty("sfcTableView.column2"));
//                delBtn.put("certification", certification);
//                CertiTableEntity cerEntity = new CertiTableEntity(certification, delBtn);
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        delBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//                            @Override
//                            public void handle(ActionEvent event) {
//                                ServiceIndicator service = new ServiceIndicator() {
//
//                                    @Override
//                                    public void runTask() {
//                                        CertificationDelRequestEntity entity = new CertificationDelRequestEntity(groupPcessField.getText(), barCodeField.getText(), certification);
//                                        try {
//                                            CommonRequest<CertificationDelRequestEntity> request = new CommonRequest<CertificationDelRequestEntity>(site.getValue(), SessionUtils.getUser(main).getUserName(), entity);
//                                            CommonResponse<CertificationDelResponseEntity> response = activityService.delCerAction(request, main);
//                                            String code = response.getCode();
//                                            String message = response.getMessage();
//                                            if ("S".equals(code)) {// 成功后删除这行数据并更新装箱进度
//                                                List<CertificationDelResponseEntity> dataList = response.getData(new CertificationDelResponseEntity());
//                                                if (!BussinessUtils.isEmpty(dataList)) {
//                                                    CertificationDelResponseEntity data = dataList.get(0);
//                                                    groupPcessField.setText(data.getGroupSchedule());
//                                                }
//                                                certiTableValue.remove(cerEntity);
//                                                showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//                                            } else {
//                                                logger.debug(response.getMessage());
//                                                showError(message);
//
//                                            }
//                                        } catch (Exception e) {
//                                            logger.debug(e.getMessage());
//                                            showError(e.getMessage());
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public ProgressIndicator getIndicator() {
//                                        return loadingProcess;
//                                    }
//
//                                    @Override
//                                    public Label getMessageLabel() {
//                                        return BasicMessage;
//                                    }
//                                };
//                                service.start();
//                            }
//                        });
//
//                    }
//                });
//                certiTableValue.add(cerEntity);
//            }
//        }
//
//        sfcTableView.refresh();
//        certiTableView.refresh();
//        showSuccess(PropertiesAppUtils.getInstance().getProperty(message));
//
//    }
//
//    /**
//     * @description:获取正在装箱的sfc列表数据
//     * @return
//     */
//    private List<String> getsfcBeingBOxList() {
//        List<String> list = new ArrayList<String>();
//        for (SfcTableEntity temp : sfcTableValue) {
//            list.add(temp.getSfc());
//        }
//        return list;
//    }
//
//    /**
//     * @description: 获取正在组托的合格证列表数据
//     * @return
//     */
//    private List<String> getCertiBOxList() {
//        List<String> list = new ArrayList<String>();
//        for (CertiTableEntity temp : certiTableValue) {
//            list.add(temp.getCertificateCode());
//        }
//        return list;
//    }

    @Override
    public AnchorPane getRoot() {
        return null;
    }

    @Override
    public Label getMessageEntity() {
        return BasicMessage;
    }

}
