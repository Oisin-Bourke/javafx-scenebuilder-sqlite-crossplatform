package com.gluonapplication;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.gluonapplication.MyApp.PRIMARY_VIEW;
import static com.gluonapplication.MyApp.SECONDARY_VIEW;
import static com.gluonapplication.MyApp.THIRD_VIEW;

import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;

public class DrawerManager {

    public static void buildDrawer(MobileApplication app) {
        NavigationDrawer drawer = app.getDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("Charity Shop App",
                "\n", /*MaterialDesignIcon.SHOP_TWO.graphic()*/
               new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/shop_icon.png"))));

        drawer.setHeader(header);

        final Item primaryItem = new ViewItem("Sales", MaterialDesignIcon.SHOPPING_CART.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("Stock", MaterialDesignIcon.INPUT.graphic(), SECONDARY_VIEW);
        final Item thirdItem = new ViewItem("Reports",MaterialDesignIcon.INSERT_CHART.graphic(),THIRD_VIEW);
        drawer.getItems().addAll(primaryItem, secondaryItem, thirdItem);
        
        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }
    }
}