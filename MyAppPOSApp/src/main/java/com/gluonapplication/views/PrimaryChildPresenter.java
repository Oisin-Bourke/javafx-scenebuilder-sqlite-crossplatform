package com.gluonapplication.views;

import com.gluonapplication.DBMethods;
import com.gluonapplication.sales.SalesTransaction;
import com.gluonapplication.stock.*;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrimaryChildPresenter {

    @FXML
    private View primaryChild;

    public void initialize() {

        primaryChild.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Point of Sale");
                /*appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e ->
                        System.out.println("Favorite")));*/
            }
        });

        //disable buttons:

        initializeNodesToLists();
        setDigitButtonStatus(true);//disable
        setQuickCashButtonStatus(true);
        chargeButton.setDisable(true);

        //set amount tendered digits:
        array[0] = '0';
        array[1] = '0';
        array[2] = '0';
        array[3] = '.';
        array[4] = '0';
        array[5] = '0';

        priceDigits = new String(array);
        amountTenderedDigits.setText(priceDigits);

        voidButton.disableProperty().bind(Bindings.isEmpty(currentSalesListView.getSelectionModel().getSelectedItems()));
    }//end init()

    //FXML related variables:

    @FXML
    private Button chargeButton = new Button();

    @FXML
    private Button voidButton = new Button();

    @FXML
    private ListView <Item> currentSalesListView;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private TextField readProductCode;

    //amount tendered:

    @FXML
    private Label amountTenderedDigits;

    private char[] array = new char[6];

    private String priceDigits;

    @FXML
    private Label amountTenderedLabel;

    //char category toggles:

    private List <ToggleButton> categoryCharList = new ArrayList<>();

    @FXML
    private ToggleGroup categoryCharGroup;

    @FXML
    private ToggleButton M;
    @FXML
    private ToggleButton W;
    @FXML
    private ToggleButton K;
    @FXML
    private ToggleButton O;

    //digits buttons:

    private List <Button> digitList = new ArrayList<>();

    @FXML
    private Button digit1;
    @FXML
    private Button digit2;
    @FXML
    private Button digit3;
    @FXML
    private Button digit4;
    @FXML
    private Button digit5;
    @FXML
    private Button digit6;
    @FXML
    private Button digit7;
    @FXML
    private Button digit8;
    @FXML
    private Button digit9;
    @FXML
    private Button digit0;

    //quick cash buttons:

    private List <Button> quickCashList = new ArrayList<>();

    @FXML
    private Button fiveCash;
    @FXML
    private Button tenCash;
    @FXML
    private Button twentyCash;
    @FXML
    private Button fiftyCash;

    //sales variables:

    private int totalSales;

    private List <Item> currentSalesList = new ArrayList<>();


    public void enterItemButton(){

        Item itemFound;//?

        DBMethods dbMethods = new DBMethods();
        dbMethods.createDB();
        itemFound = dbMethods.searchStockItem(readProductCode.getText());//search for item in db

        if(itemFound == null){
            System.out.println("Item not found");

            setCharCategoryStatus(false);

            for(Toggle toggle:categoryCharGroup.getToggles()){
                toggle.setSelected(false);
            }

            if (!currentSalesList.isEmpty()) setDigitButtonStatus(false);
            if (!currentSalesList.isEmpty()) setQuickCashButtonStatus(false);

            readProductCode.setText("");
            readProductCode.setPromptText("Enter valid item code");
        } else {

            dbMethods.deleteStockItem(readProductCode.getText());

            itemFound.generatePrice(itemFound);
            itemFound.generateProductCode();

            currentSalesList.add(itemFound);
            currentSalesListView.getItems().add(itemFound);

            totalSales += itemFound.getPrice();
            totalSalesLabel.setText(formatPrice(totalSales));

            if (!currentSalesList.isEmpty()) setDigitButtonStatus(false);
            if (!currentSalesList.isEmpty()) setQuickCashButtonStatus(false);

            setCharCategoryStatus(false);

            for(Toggle toggle:categoryCharGroup.getToggles()){
                toggle.setSelected(false);
            }

            readProductCode.setText("");
            readProductCode.setPromptText("Enter item code");
        }
    }

    public void voidItemButton(){
        //repopulate db:
        Item itemBackToDB = currentSalesListView.getSelectionModel().getSelectedItem();
        DBMethods dbMethods = new DBMethods();
        dbMethods.createDB();
        dbMethods.createItem(itemBackToDB);
        System.out.println("Item updated to stock :"+itemBackToDB.toString());

        //remove from list view:
        ObservableList<Item> observableList;
        observableList = currentSalesListView.getSelectionModel().getSelectedItems();
        currentSalesList.removeAll(observableList);

        //repopulate list view and sales totals:
        currentSalesListView.getItems().clear();
        for (Item item : currentSalesList) {
            currentSalesListView.getItems().add(item);
        }

        totalSales = 0;
        for (Item item : currentSalesList) {
            totalSales += item.getPrice();
        }
        totalSalesLabel.setText(formatPrice(totalSales));
    }

    public void processCategoryChar(ActionEvent event){

        String charInput = ((ToggleButton) event.getSource()).getText();
        readProductCode.setText(charInput);

        for (Toggle toggle : categoryCharGroup.getToggles()) {
            ToggleButton button = (ToggleButton) toggle;
            button.setDisable(true);
        }
        setDigitButtonStatus(false);
    }

    private int btnCount = 5;//tracks button clicks

    public void processDigits(ActionEvent event){

        if(readProductCode.getText().startsWith("M")||readProductCode.getText().startsWith("W")||readProductCode.getText().startsWith("O")||readProductCode.getText().startsWith("K")){

            String number = ((Button)event.getSource()).getText();
            readProductCode.setText(readProductCode.getText()+number);

        } else {

            char digit;

            if (btnCount == 5) {
                digit = ((Button) event.getSource()).getText().charAt(0);
                array[5] = digit;
                amountTenderedDigits.setText(new String(array));
                btnCount--;
            } else if (btnCount == 4) {
                digit = ((Button) event.getSource()).getText().charAt(0);
                array[4] = array[5];
                array[5] = digit;
                amountTenderedDigits.setText(new String(array));
                btnCount--;
            } else if (btnCount == 3) {
                digit = ((Button) event.getSource()).getText().charAt(0);
                array[2] = array[4];
                array[4] = array[5];
                array[5] = digit;
                amountTenderedDigits.setText(new String(array));
                btnCount--;
            } else if (btnCount == 2) {
                digit = ((Button) event.getSource()).getText().charAt(0);
                array[1] = array[2];
                array[2] = array[4];
                array[4] = array[5];
                array[5] = digit;
                amountTenderedDigits.setText(new String(array));
                btnCount--;
            } else if (btnCount == 1) {
                digit = ((Button) event.getSource()).getText().charAt(0);
                array[0] = array[1];
                array[1] = array[2];
                array[2] = array[4];
                array[4] = array[5];
                array[5] = digit;
                amountTenderedDigits.setText(new String(array));
                btnCount--;
            } else System.out.println("Max button count");

            //enable charge button if greater than tendered:
            if (Integer.valueOf(amountTenderedDigits.getText().replace(".", "")) >= totalSales)
                chargeButton.setDisable(false);
        }
    }

    public void clearDigitsButton(){
        btnCount = 5;

        array[0] = '0';
        array[1] = '0';
        array[2] = '0';
        array[3] = '.';
        array[4] = '0';
        array[5] = '0';

        priceDigits = new String(array);
        amountTenderedDigits.setText(priceDigits);
        readProductCode.setText("");
        readProductCode.setPromptText("Enter item code");

        for(Toggle toggle:categoryCharGroup.getToggles()){
            toggle.setSelected(false);
        }

        if(currentSalesList.isEmpty()){
            setDigitButtonStatus(true);
            setQuickCashButtonStatus(true);
        }

        setCharCategoryStatus(false);
    }

    //quick cash buttons:
    public void quickCashFive(){

        amountTenderedDigits.setText("5.00");
        if (Integer.valueOf(amountTenderedDigits.getText().replace(".", "")) >= totalSales)
            chargeButton.setDisable(false);
    }

    public void quickCashTen(){

        amountTenderedDigits.setText("10.00");
        if (Integer.valueOf(amountTenderedDigits.getText().replace(".", "")) >= totalSales)
            chargeButton.setDisable(false);
    }

    public void quickCashTwenty(){

        amountTenderedDigits.setText("20.00");
        if (Integer.valueOf(amountTenderedDigits.getText().replace(".", "")) >= totalSales)
            chargeButton.setDisable(false);
    }

    public void quickCashFifty(){

        amountTenderedDigits.setText("50.00");
        if (Integer.valueOf(amountTenderedDigits.getText().replace(".", "")) >= totalSales)
            chargeButton.setDisable(false);
    }

    public void chargeSalesButton(){

        if(chargeButton.getText().equals("Charge")) {

            int changeDue = Integer.valueOf(amountTenderedDigits.getText().replace(".", ""))
                    - Integer.valueOf(totalSalesLabel.getText().replace(".", ""));

            amountTenderedDigits.setText(formatPrice(changeDue));
            amountTenderedLabel.setText("Change Due");

            ThirdChildSalesPresenter.dailySalesList.add( new SalesTransaction(currentSalesList,totalSales));

            for (SalesTransaction item : ThirdChildSalesPresenter.dailySalesList) {
                System.out.println("printing contents of daily sales list in POS");
                System.out.println(item.toString());
            }

            setCharCategoryStatus(true);
            setQuickCashButtonStatus(true);
            setDigitButtonStatus(true);

            chargeButton.setText("New Sale");
        } else {
            //if button status is "New Sale":
            chargeButton.setText("Charge");
            chargeButton.setDisable(true);

            totalSales = 0;
            currentSalesList.clear();
            currentSalesListView.getItems().clear();
            clearDigitsButton();
            totalSalesLabel.setText("");
            amountTenderedLabel.setText("Amount Tendered");
        }
    }

    //auxiliary methods:

    private String formatPrice(int price) {

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(price / 100.0);
    }

    //buttons lists disable status:

    private void setDigitButtonStatus(boolean status){

        for(Button button:digitList){
            button.setDisable(status);
        }
    }

    private void setQuickCashButtonStatus(boolean status){

        for(Button button: quickCashList){
            button.setDisable(status);

        }
    }

    private void setCharCategoryStatus(boolean status){

        for (ToggleButton toggle :categoryCharList){
            toggle.setDisable(status);
        }
    }

    private void initializeNodesToLists(){
        digitList.add(digit1);
        digitList.add(digit2);
        digitList.add(digit3);
        digitList.add(digit4);
        digitList.add(digit5);
        digitList.add(digit6);
        digitList.add(digit7);
        digitList.add(digit8);
        digitList.add(digit9);
        digitList.add(digit0);

        quickCashList.add(fiveCash);
        quickCashList.add(tenCash);
        quickCashList.add(twentyCash);
        quickCashList.add(fiftyCash);

        categoryCharList.add(M);
        categoryCharList.add(W);
        categoryCharList.add(K);
        categoryCharList.add(O);
    }




}
