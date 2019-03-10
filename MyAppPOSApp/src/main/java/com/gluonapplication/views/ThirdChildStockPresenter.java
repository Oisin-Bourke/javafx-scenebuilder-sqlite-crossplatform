package com.gluonapplication.views;

import com.gluonapplication.DBMethods;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ThirdChildStockPresenter {

    @FXML
    private View thirdChildStock;

    public void initialize() {

        DBMethods dbMethods = new DBMethods();
        dbMethods.createDB();
        data.addAll(dbMethods.readDB());

        thirdChildStock.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Stock List");
                 appBar.getActionItems().add(MaterialDesignIcon.REFRESH.button(e ->
                 {
                            tableView.getItems().clear();
                            data.addAll(dbMethods.readDB());
                 }));
            }
        });

        idCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("id"));
        date_stampCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("date_stamp"));
        categoryCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("category"));
        typeCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("type"));
        conditionCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("condition"));
        brandCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("brand"));
        sizeCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("size"));
        priceCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("price"));
        codeCol.setCellValueFactory( new PropertyValueFactory<StockData,String>("code"));


        tableView.setItems(data);
    }

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn date_stampCol;
    @FXML
    private TableColumn categoryCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn conditionCol;
    @FXML
    private TableColumn brandCol;
    @FXML
    private TableColumn sizeCol;
    @FXML
    private TableColumn priceCol;
    @FXML
    private TableColumn codeCol;

    private ObservableList<StockData> data = FXCollections.observableArrayList();

    //stock data inner class:
    public static class StockData {

        private final SimpleStringProperty id;
        private final SimpleStringProperty date_stamp;
        private final SimpleStringProperty category;
        private final SimpleStringProperty type;
        private final SimpleStringProperty condition;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty size;
        private final SimpleStringProperty price;
        private final SimpleStringProperty code;

        public StockData(String id, String date_stamp, String category, String type, String condition, String brand, String size, String price,String code) {
            this.id = new SimpleStringProperty(id);
            this.date_stamp = new SimpleStringProperty(date_stamp);
            this.category = new SimpleStringProperty(category);
            this.type = new SimpleStringProperty(type);
            this.condition = new SimpleStringProperty(condition);
            this.brand = new SimpleStringProperty(brand);
            this.size = new SimpleStringProperty(size);
            this.price = new SimpleStringProperty(price);
            this.code = new SimpleStringProperty(code);
        }

        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getDate_stamp() {
            return date_stamp.get();
        }

        public void setDate_stamp(String date_stamp) {
            this.date_stamp.set(date_stamp);
        }

        public String getCategory() {
            return category.get();
        }

        public void setCategory(String category) {
            this.category.set(category);
        }

        public String getType() {
            return type.get();
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public String getCondition() {
            return condition.get();
        }

        public void setCondition(String condition) {
            this.condition.set(condition);
        }

        public String getBrand() {
            return brand.get();
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public String getSize() {
            return size.get();
        }

        public void setSize(String size) {
            this.size.set(size);
        }

        public String getPrice() {
            return price.get();
        }

        public void setPrice(String price) {
            this.price.set(price);
        }

        public String getCode() {
            return code.get();
        }

        public void setCode(String code) {
            this.code.set(code);
        }

    }//end static inner class


}
