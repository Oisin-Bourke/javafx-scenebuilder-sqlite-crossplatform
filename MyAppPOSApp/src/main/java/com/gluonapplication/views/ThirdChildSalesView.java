package com.gluonapplication.views;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ThirdChildSalesView {

    public View getView() {
        try {
            View view = FXMLLoader.load(ThirdChildSalesView.class.getResource("thirdChildSales.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
