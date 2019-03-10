package com.gluonapplication.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static com.gluonapplication.MyApp.PRIMARY_CHILD_VIEW;

public class PrimaryPresenter {

    @FXML
    private View primary;

    public void initialize() {

        primary.setShowTransitionFactory(BounceInRightTransition::new);

        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Sales");
                /*appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e ->
                        System.out.println("Search")));*/
            }
        });
    }

    @FXML
    void sceneSwitch() {
        MobileApplication.getInstance().switchView(PRIMARY_CHILD_VIEW);
    }

}
