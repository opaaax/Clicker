package com.example.clicker;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    private static int score = 0;
    private static final Label scoreLabel = new Label();

    @Override
    public void start(Stage primaryStage){

        boolean is3DSupported = Platform.isSupported(ConditionalFeature.SCENE3D);
        if(!is3DSupported) {
            return;
        }

        setScoreLabelText();
        RotatingBox box = new RotatingBox(primaryStage);

        HBox labelBox = new HBox(scoreLabel);
        labelBox.setAlignment(Pos.CENTER);

        VBox upgradesButtons = new VBox();
        upgradesButtons.setSpacing(5);
        upgradesButtons.setBackground(Background.fill(Paint.valueOf("grey")));
        upgradesButtons.setBorder(Border.stroke(Paint.valueOf("black")));

        Group boxGroup = new Group();
        boxGroup.getChildren().add(box);
        boxGroup.requestFocus();
        boxGroup.setRotate(40);
        boxGroup.setRotationAxis(Rotate.X_AXIS);
        boxGroup.setOnMouseClicked(_ -> {
            score++;
            setScoreLabelText();
        });

        boolean fixedEyeAtCameraZero = false;
        PerspectiveCamera camera = new PerspectiveCamera(fixedEyeAtCameraZero);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(boxGroup);
        borderPane.getCenter().setCursor(Cursor.HAND);
        borderPane.setTop(labelBox);
        borderPane.setLeft(upgradesButtons);

        Scene scene = new Scene(borderPane, 500, 500);
        scene.setCamera(camera);

        upgradesButtons.getChildren().add(new UpgradeButtonComponent(1, 10, "An auto clicker!", 5,
                """
                Woah!! An auto Clicker!!!
                Why even play this game anymore?
                Everyone of them adds 1 to your score every 10 seconds
                """, scene));

        upgradesButtons.getChildren().add(new UpgradeButtonComponent(3, 4, "A classic cookie clicker Grandma!", 20,
                """
                They even got the grandma that clicks cookies!!!
                But this time this aren't cookies ??!
                This time it is a strange rotating cube ;)))))
                """, scene));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Cool clicker !!!");
        primaryStage.setOnCloseRequest(_ -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        HelloApplication.score = score;
    }

    public static void setScoreLabelText(){
        scoreLabel.setText("Score: " + score);
    }

    public static void main(String[] args) {
        launch();
    }
}