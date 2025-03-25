package com.example.clicker;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MouseUpgradeButton extends Button {
    private int upgradeCount;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private final int cost = 100;

    public MouseUpgradeButton() {
        super();

        this.upgradeCount = 0;
        this.setBorder(Border.stroke(Paint.valueOf("black")));
        this.setBackground(Background.fill(BACKGROUND_COLOR));
        this.setCursor(Cursor.HAND);
        this.setHeight(100);
        this.setWidth(300);
        this.setText("Upgrade your mouse :), it will cost you " + cost);

        this.setOnMousePressed(_ -> {
            this.setBackground(Background.fill(Paint.valueOf("lightgray")));
            this.setCursor(Cursor.CLOSED_HAND);
            if (ClickerApplication.getScore() >= cost) {
                ClickerApplication.setScore(ClickerApplication.getScore() - cost);
                upgradeCount++;
                ClickerApplication.mouseClickAmount += 1;

                this.setText("Upgrade your mouse " + getUpgradeEmote() + ", it will cost you " + cost);
            }
            ClickerApplication.setScoreLabelText();
        });

        this.setOnMouseReleased(_ -> {
            this.setBackground(Background.fill(BACKGROUND_COLOR));
            this.setCursor(Cursor.HAND);
        });
    }

    private String getUpgradeEmote(){
        if(upgradeCount == 0){
            return ":)";
        } else if (upgradeCount <= 5){
            return ";)";
        } else if (upgradeCount <= 10){
            return ":(";
        } else if (upgradeCount <= 20){
            return ";((";
        } else {
            return "⚠";
        }
    }
}
