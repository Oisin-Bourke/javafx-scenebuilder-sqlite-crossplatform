package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import static com.gluonapplication.MyApp.THIRD_CHILD_SALES_VIEW;
import static com.gluonapplication.MyApp.THIRD_CHILD_STOCK_VIEW;

public class ThirdPresenter {

    @FXML
    private View third;

    public void initialize() {
        third.setShowTransitionFactory(BounceInRightTransition::new);

        third.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Reports");
            }
        });
    }

    public void sceneSwitchSalesReports() {
        MobileApplication.getInstance().switchView(THIRD_CHILD_SALES_VIEW);
    }

    public void sceneSwitchStockReports() {
        MobileApplication.getInstance().switchView(THIRD_CHILD_STOCK_VIEW);
    }
}
