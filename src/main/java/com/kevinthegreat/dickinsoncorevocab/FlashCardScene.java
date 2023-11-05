package com.kevinthegreat.dickinsoncorevocab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlashCardScene {
    private final Scene scene;

    public FlashCardScene(DickinsonCoreVocab parent, int index, WebElement row) {
        Text word = new Text(row.findElement(By.xpath("./td[1]")).getText());
        word.setStyle("-fx-font-size: 64;");
        Text definition = new Text(row.findElement(By.xpath("./td[2]")).getText());
        definition.setStyle("-fx-font-size: 48;");
        Text partOfSpeech = new Text(row.findElement(By.xpath("./td[3]")).getText());
        partOfSpeech.setStyle("-fx-font-size: 32;");
        Text rank = new Text(row.findElement(By.xpath("./td[5]")).getText());
        rank.setStyle("-fx-font-size: 32;");
        Pane rankRow = new HBox();
        rankRow.getChildren().addAll(createSpacer(), rank);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(64));
        root.getChildren().addAll(createSpacer(), word, createSpacer(), definition, createSpacer(), partOfSpeech, createSpacer(), rankRow);
        scene = new Scene(root);
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case RIGHT -> parent.setFlashCardScene(index + getIncrement(event));
                case LEFT -> parent.setFlashCardScene(index - getIncrement(event));
                case DIGIT0 -> parent.setFlashCardScene(0);
                case DIGIT1 -> parent.setFlashCardScene(100);
                case DIGIT2 -> parent.setFlashCardScene(200);
                case DIGIT3 -> parent.setFlashCardScene(300);
                case DIGIT4 -> parent.setFlashCardScene(400);
                case DIGIT5 -> parent.setFlashCardScene(500);
                case DIGIT6 -> parent.setFlashCardScene(600);
                case DIGIT7 -> parent.setFlashCardScene(700);
                case DIGIT8 -> parent.setFlashCardScene(800);
                case DIGIT9 -> parent.setFlashCardScene(900);
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    private Node createSpacer() {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private int getIncrement(KeyEvent event) {
        if (event.isShortcutDown()) {
            return 50;
        }
        if (event.isAltDown()) {
            return 10;
        }
        return 1;
    }
}
