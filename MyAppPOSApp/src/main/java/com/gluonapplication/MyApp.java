package com.gluonapplication;

import com.gluonapplication.views.*;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.license.License;


@License(key="53afe535-909b-4ad9-86f9-76f538efc1d7")
public class MyApp extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";
    public static final String THIRD_VIEW = "Third View";

    public static final String PRIMARY_CHILD_VIEW = "Primary Child View";
    public static final String SECONDARY_CHILD_VIEW = "Secondary Child View";
    public static final String THIRD_CHILD_SALES_VIEW = "Third Child Sales View";
    public static final String THIRD_CHILD_STOCK_VIEW = "Third Child Stock View";

    @Override
    public void init() {
        addViewFactory(PRIMARY_VIEW, () -> new PrimaryView().getView());
        addViewFactory(SECONDARY_VIEW, () -> new SecondaryView().getView());
        addViewFactory(THIRD_VIEW, () -> new ThirdView().getView());

        addViewFactory(PRIMARY_CHILD_VIEW, ()-> new PrimaryChildView().getView());
        addViewFactory(SECONDARY_CHILD_VIEW, ()-> new SecondaryChildView().getView());
        addViewFactory(THIRD_CHILD_SALES_VIEW, ()-> new ThirdChildSalesView().getView());
        addViewFactory(THIRD_CHILD_STOCK_VIEW, ()-> new ThirdChildStockView().getView());

        DrawerManager.buildDrawer(this);

        //Tester.test1();

        DBMethods dbMethods = new DBMethods();
        dbMethods.createDB();
        dbMethods.createTables();
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.TEAL.assignTo(scene);


        /*
        if(Platform.isDesktop()){
            scene.getWindow().setWidth(650);
            scene.getWindow().setHeight(500);
        }
        */

        scene.getStylesheets().add(MyApp.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(MyApp.class.getResourceAsStream("/shop_icon.png")));
    }
}
