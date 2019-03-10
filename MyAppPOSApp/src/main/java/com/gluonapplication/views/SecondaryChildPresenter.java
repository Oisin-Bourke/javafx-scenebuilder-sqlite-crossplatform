package com.gluonapplication.views;

import com.gluonapplication.DBMethods;
import com.gluonapplication.stock.*;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.text.DecimalFormat;


public class SecondaryChildPresenter {

    @FXML
    private View secondaryChild;

    public void initialize() {
        secondaryChild.setShowTransitionFactory(BounceInRightTransition::new);

        secondaryChild.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Stock Intake");
            }
        });

       setUpdateButtonStatus(true);

    }

    //Item object and variable attributes:

    private Item tempItem;

    private Category category;
    private Type type;
    private Condition condition;
    private Brand brand;
    private Size size;

    private LinkStringEnum link = new LinkStringEnum();//object used to call link methods

    //FXML variables:

    @FXML
    private AnchorPane stockPane;

    @FXML
    private TabPane stockTabPane;

    //grid parents:

    @FXML
    private GridPane menGridPane;

    @FXML
    private GridPane womenGridPane;

    @FXML
    private GridPane kidGridPane;

    @FXML
    private GridPane otherGridPane;

    //tabs:

    @FXML
    private Tab menTab;

    @FXML
    private Tab womenTab;

    @FXML
    private Tab kidTab;

    @FXML
    private Tab otherTab;

    //toggle button groups:

    @FXML
    private ToggleGroup menTypeGroup;

    @FXML
    private ToggleGroup menSizeGroup;

    @FXML
    private ToggleGroup menBrandGroup;

    @FXML
    private ToggleGroup menConditionGroup;

    @FXML
    private ToggleGroup womenTypeGroup;

    @FXML
    private ToggleGroup womenSizeGroup;

    @FXML
    private ToggleGroup womenBrandGroup;

    @FXML
    private ToggleGroup womenConditionGroup;

    @FXML
    private ToggleGroup kidTypeGroup;

    @FXML
    private ToggleGroup kidSizeGroup;

    @FXML
    private ToggleGroup kidBrandGroup;

    @FXML
    private ToggleGroup kidConditionGroup;

    @FXML
    private ToggleGroup otherTypeGroup;

    @FXML
    private ToggleGroup otherConditionGroup;

    //labels:

    @FXML
    private Label categoryLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label conditionLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label printingLabel;

    //buttons:

    @FXML
    private Button updateButtonM;

    @FXML
    private Button updateButtonW;

    @FXML
    private Button updateButtonK;

    @FXML
    private Button updateButtonO;

    //if tab selected methods:

    public void selectMenTab(){

        if(menTab.isSelected()){
            category = Category.MEN;
            try {
                categoryLabel.setText("Men Item");
                clearAllSelections();
                setUpdateButtonStatus(true);
            } catch (NullPointerException e) {
                //e.printStackTrace();
                e.getMessage();
                System.out.println("* bug identified *");
            }

        }

    }

    public void selectWomenTab(){

        if(womenTab.isSelected()){
            category = Category.WOMEN;
            categoryLabel.setText("Women Item");
            clearAllSelections();
            setUpdateButtonStatus(true);
        }
    }

    public void selectKidTab(){

        if(kidTab.isSelected()){
            category = Category.KID;
            categoryLabel.setText("Kid Item");
            clearAllSelections();
            setUpdateButtonStatus(true);
        }
    }

    public void selectOtherTab(){

        if(otherTab.isSelected()){
            category = Category.OTHER;
            categoryLabel.setText("Other Item");
            clearAllSelections();
            setUpdateButtonStatus(true);
            sizeLabel.setText("n/a");
            brandLabel.setText("n/a");
        }
    }

    //process button methods:

    public void processTypeButton(ActionEvent event){
        String typeInput = ((ToggleButton) event.getSource()).getText();
        type = link.linkType(typeInput);
        typeLabel.setText(typeInput);
    }

    public void processConditionButton(ActionEvent event){
        String conditionInput = ((ToggleButton) event.getSource()).getText();
        condition = link.linkCondition(conditionInput);
        conditionLabel.setText(conditionInput);
    }

    public void processBrandButton(ActionEvent event){
        String brandInput = ((ToggleButton) event.getSource()).getText();
        brand = link.linkBrand(brandInput);
        brandLabel.setText(brandInput);
    }

    public void processSizeButton(ActionEvent event){
        String sizeInput = ((ToggleButton) event.getSource()).getText();
        size = link.linkSize(sizeInput);
        sizeLabel.setText(sizeInput);
    }

    public void clearAllSelections(){

        type = null;
        condition = null;
        brand = null;
        size = null;

        clearLabels();

        setUpdateButtonStatus(true);

        clearSelectedButtons(menGridPane);
        clearSelectedButtons(womenGridPane);
        clearSelectedButtons(kidGridPane);
        clearSelectedButtons(otherGridPane);

    }

    public void reviewItemButton(){

        if (otherTab.isSelected()){

            if(type == null) {
                typeLabel.setText("Enter type");
                highlightErrorAnimation(otherTypeGroup);
                highlightLabelAnimation(typeLabel);
            }
            if(condition == null) {
                conditionLabel.setText("Enter condition");
                highlightErrorAnimation(otherConditionGroup);
                highlightLabelAnimation(conditionLabel);
            }

            if(type !=null && condition!= null) {

                tempItem = new Item(category,type,condition);
            }
        }
        else {

            if(type == null) {
                typeLabel.setText("Enter type");
                highlightLabelAnimation(typeLabel);
                if(menTab.isSelected())highlightErrorAnimation(menTypeGroup);
                if(womenTab.isSelected()) highlightErrorAnimation(womenTypeGroup);
                if(kidTab.isSelected())highlightErrorAnimation(kidTypeGroup);
            }
            if(brand == null) {
                brandLabel.setText("Enter brand");
                highlightLabelAnimation(brandLabel);
                if(menTab.isSelected())highlightErrorAnimation(menBrandGroup);
                if(womenTab.isSelected()) highlightErrorAnimation(womenBrandGroup);
                if(kidTab.isSelected())highlightErrorAnimation(kidBrandGroup);
            }
            if(size == null) {
                sizeLabel.setText("Enter size");
                highlightLabelAnimation(sizeLabel);
                if(menTab.isSelected())highlightErrorAnimation(menSizeGroup);
                if(womenTab.isSelected()) highlightErrorAnimation(womenSizeGroup);
                if(kidTab.isSelected())highlightErrorAnimation(kidSizeGroup);
            }
            if(condition == null) {
                conditionLabel.setText("Enter condition");
                highlightLabelAnimation(conditionLabel);
                if(menTab.isSelected())highlightErrorAnimation(menConditionGroup);
                if(womenTab.isSelected()) highlightErrorAnimation(womenConditionGroup);
                if(kidTab.isSelected())highlightErrorAnimation(kidConditionGroup);
            }

            if(type!=null && brand!=null && size!=null && condition!=null) {

                tempItem = new Apparel(category, type, condition, brand, size);
            }

        }

        if((otherTab.isSelected() && type!=null && condition!=null)||(type!=null && brand!=null && size!=null && condition!=null)) {

            tempItem.generatePrice(tempItem);
            tempItem.generateProductCode();

            DecimalFormat df = new DecimalFormat("#.00");
            String price = df.format(tempItem.getPrice() / 100.0);
            priceLabel.setText(price);

            setUpdateButtonStatus(false);

            System.out.println(tempItem.toString());

        }

    }

    public void updateItemButton(){
        DBMethods dbMethods = new DBMethods();
        dbMethods.createDB();
        dbMethods.createItem(tempItem);

        System.out.println("Item updated to stock :"+tempItem.toString());

        clearAllSelections();
        printingLabelAnimation();

        if(otherTab.isSelected()){
            sizeLabel.setText("n/a");
            brandLabel.setText("n/a");
        }
    }

    //Auxiliary methods:
    private void clearLabels(){
        typeLabel.setText("");
        conditionLabel.setText("");
        brandLabel.setText("");
        sizeLabel.setText("");
        priceLabel.setText("");
    }

    private void clearSelectedButtons(GridPane gridPane){
        for (Node child :gridPane.getChildren()){

            if(child instanceof ToggleButton){
                ToggleButton button = (ToggleButton) child;
                button.setSelected(false);
            }
        }
    }

    private void setUpdateButtonStatus(boolean status){
        updateButtonM.setDisable(status);
        updateButtonW.setDisable(status);
        updateButtonK.setDisable(status);
        updateButtonO.setDisable(status);
    }

    //animations:
    private void printingLabelAnimation() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), printingLabel);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }

    private void highlightErrorAnimation(ToggleGroup toggleGroup){
        FadeTransition ft;

        for (Toggle toggle : toggleGroup.getToggles()) {
            ToggleButton button = (ToggleButton) toggle;
            ft = new FadeTransition(Duration.millis(800),button);
            ft.setFromValue(1.0);
            ft.setToValue(0.4);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();
        }

    }

    private void highlightLabelAnimation(Label label){
        FadeTransition ft = new FadeTransition(Duration.millis(800), label);
        ft.setFromValue(1.0);
        ft.setToValue(0.2);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }


}
