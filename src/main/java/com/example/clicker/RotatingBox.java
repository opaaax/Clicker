package com.example.clicker;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class RotatingBox extends Box {
    public RotatingBox(Stage primaryStage) {
        super(300,300,300);
        this.setCullFace(CullFace.NONE);
        this.setCursor(Cursor.HAND);
        this.setMaterial(new PhongMaterial(Color.DARKRED));
        this.setDrawMode(DrawMode.LINE);

        Thread.ofVirtual().start(() -> {
            while(true) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Rotate rotateY = new Rotate(0.6, Rotate.Y_AXIS);
                Rotate rotateX = new Rotate(0.7, Rotate.X_AXIS);
                Rotate rotateZ = new Rotate(0.5, Rotate.Z_AXIS);

                final Transform[] transformY = new Transform[1];
                final Transform[] transformX = new Transform[1];
                final Transform[] transformZ = new Transform[1];

                Platform.runLater(() -> {
                    this.setDepth(primaryStage.getHeight()/4);
                    this.setHeight(primaryStage.getHeight()/4);
                    this.setWidth(primaryStage.getWidth()/4);

                    transformY[0] = this.getLocalToParentTransform().createConcatenation(rotateY);
                    this.getTransforms().clear();
                    this.getTransforms().addAll(transformY[0]);

                    transformX[0] = this.getLocalToParentTransform().createConcatenation(rotateX);
                    this.getTransforms().clear();
                    this.getTransforms().addAll(transformX[0]);

                    transformZ[0] = this.getLocalToParentTransform().createConcatenation(rotateZ);
                    this.getTransforms().clear();
                    this.getTransforms().addAll(transformZ[0]);
                });

            }
        });
    }
}
