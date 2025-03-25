package com.example.clicker;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;


public class UpgradeButton extends Button {
    private int upgradeCount;
    private final Popup popup;
    private int counter;
    private int cost;
    private final ProgressBar progressBar;
    private static final String BACKGROUND_COLOR = "white";

    public UpgradeButton(int additionAmount, int additionDelay, String text, int initialCost, String description, Scene sceneReference) {
        super();
        this.progressBar = new ProgressBar(0);
        this.upgradeCount = 0;
        this.cost = initialCost;
        this.setBorder(Border.stroke(Paint.valueOf("black")));
        this.setBackground(Background.fill(Paint.valueOf(BACKGROUND_COLOR)));
        this.setCursor(Cursor.HAND);
        this.setHeight(50);
        this.setWidth(150);
        this.setText(text + "  COST: "+ cost +"\nYou have: 0");

        StackPane content = new StackPane(new Label(description));
        content.setPadding(new Insets(10,5,10,5));
        content.setBackground(new Background(
                new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(10), null)));
        content.setEffect(new DropShadow());

        this.popup = new Popup();
        this.popup.getContent().add(content);

        this.setOnMousePressed(_ -> {
            UpgradeButton.this.setBackground(Background.fill(Paint.valueOf("lightgray")));
            UpgradeButton.this.setCursor(Cursor.CLOSED_HAND);
            if (ClickerApplication.getScore() >= cost) {
                ClickerApplication.setScore(ClickerApplication.getScore() - cost);
                upgradeCount++;
            }
            ClickerApplication.setScoreLabelText();
        });

        this.setOnMouseReleased(_ -> {
            this.setBackground(Background.fill(Paint.valueOf(BACKGROUND_COLOR)));
            this.setCursor(Cursor.HAND);
        });

        this.setOnMouseEntered(e -> popup.show(sceneReference.getWindow(), e.getScreenX(), e.getScreenY()));
        this.setOnMouseExited(_ -> popup.hide());

        beginButtonThread(additionAmount, additionDelay, text, initialCost);
    }

    private void beginButtonThread(int additionAmount, int additionDelay, String text, int initialCost) {
        Thread.ofVirtual().start(() -> {
            while (true) {
                    counter++;

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (counter >= additionDelay * 100) {
                        Platform.runLater(() -> {
                            ClickerApplication.setScore(ClickerApplication.getScore() + upgradeCount * additionAmount);
                            ClickerApplication.setScoreLabelText();
                        });
                        counter = 0;
                    }

                    if(upgradeCount > 0){
                        Platform.runLater(() -> {
                            this.progressBar.setProgress((double) (counter) / (additionDelay * 100));
                            UpgradeButton.this.setText(text + "COST: "+ cost + "\nYou have: " + upgradeCount);
                        });
                        this.cost = initialCost * (upgradeCount + 1);
                    }

                    if(ClickerApplication.getScore() < cost){
                        this.setBackground(Background.fill(Paint.valueOf("darkgrey")));
                    } else if (!this.isPressed()){
                        this.setBackground(Background.fill(Paint.valueOf(BACKGROUND_COLOR)));
                    }
            }
        });
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
