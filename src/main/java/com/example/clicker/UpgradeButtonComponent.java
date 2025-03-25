package com.example.clicker;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class UpgradeButtonComponent extends VBox {
    UpgradeButton upgradeButton;


    public UpgradeButtonComponent(int additionAmount, int additionDelay, String text, int cost, String description, Scene sceneReference) {
        super();
        this.upgradeButton = new UpgradeButton(additionAmount, additionDelay, text, cost, description, sceneReference);

        this.setSpacing(2);
        this.setBorder(Border.stroke(Paint.valueOf("black")));
        this.getChildren().add(upgradeButton);
        this.getChildren().add(upgradeButton.getProgressBar());
    }
}
