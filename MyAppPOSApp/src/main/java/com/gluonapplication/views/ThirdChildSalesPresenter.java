package com.gluonapplication.views;

import com.gluonapplication.sales.SalesTransaction;
import com.gluonapplication.stock.Item;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ThirdChildSalesPresenter {

    @FXML
    private View thirdChildSales;

    public void initialize() {
        thirdChildSales.setShowTransitionFactory(BounceInRightTransition::new);

        thirdChildSales.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("End of Day Sales");
            }
        });
    }

    @FXML
    private ListView <String> endOfDaySalesListView;

    @FXML
    private Label endOfDayTotalLabel;

    private int endOfDayTotal;

    public static List <SalesTransaction> dailySalesList = new ArrayList<>();//holds daily sales transactions



    public void processEndOfDay(){

        System.out.println("process end of day...");

        for (SalesTransaction transaction : dailySalesList){

            endOfDaySalesListView.getItems().add(transaction.toString());

            for (Item item : transaction.getSoldItemsList()){
                endOfDaySalesListView.getItems().add(item.toString());
            }

        }

     //endOfDaySalesListView.getItems().addAll(dailySalesList);

     for(SalesTransaction transaction : dailySalesList){
         int transactionTotal = transaction.getSalesTotal();
         endOfDayTotal += transactionTotal;

     }

     endOfDayTotalLabel.setText(formatPrice(endOfDayTotal));

    }

    private String formatPrice(int price) {

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(price / 100.0);
    }

}
