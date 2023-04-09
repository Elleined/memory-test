package com.memorytest.memorytest;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class MemoryTestController implements Initializable {
    @FXML
    private ComboBox<Integer> cbBombPicker;
    @FXML
    private GridPane mainFrame;
    @FXML
    private Label lbl00;
    @FXML
    private Label lbl10;
    @FXML
    private Label lbl20;
    @FXML
    private Label lbl30;
    @FXML
    private Label lbl40;
    @FXML
    private Label lbl01;
    @FXML
    private Label lbl11;
    @FXML
    private Label lbl21;
    @FXML
    private Label lbl31;
    @FXML
    private Label lbl41;
    @FXML
    private Label lbl02;
    @FXML
    private Label lbl12;
    @FXML
    private Label lbl22;
    @FXML
    private Label lbl32;
    @FXML
    private Label lbl42;
    @FXML
    private Label lbl03;
    @FXML
    private Label lbl13;
    @FXML
    private Label lbl23;
    @FXML
    private Label lbl33;
    @FXML
    private Label lbl43;
    @FXML
    private Label lbl04;
    @FXML
    private Label lbl14;
    @FXML
    private Label lbl24;
    @FXML
    private Label lbl34;
    @FXML
    private Label lbl44;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNumberLevels();
        this.cbBombPicker.valueProperty().addListener(this::comboBoxItemPressed);

    }

    private void comboBoxItemPressed(Observable observable) {
        int selectedNumberLevel = cbBombPicker.getSelectionModel().getSelectedItem();

        GameAlgorithm gameAlgorithm = new GameAlgorithm();
        List<Integer> gameTiles = gameAlgorithm.generateGameTiles(selectedNumberLevel);

        Iterator<Integer> levelItr = gameTiles.iterator();
        Iterator<Label> labelItr = this.labelList().iterator();
        while (levelItr.hasNext() && labelItr.hasNext()) {
            this.setFont(labelItr.next(), levelItr.next());
        }
    }

    private void setFont(Label label, int level) {
        label.setText(String.valueOf(level));
        Font font = Font.font("Arial Black", FontWeight.BOLD, FontPosture.ITALIC, 45);
        label.setFont(font);
        label.setTextAlignment(TextAlignment.CENTER);
    }
    private void initializeNumberLevels() {
        ObservableList<Integer> numberLevels = cbBombPicker.getItems();
        for (int i = 5; i < 26; i++) {
            numberLevels.add(i);
        }
        cbBombPicker.setItems( numberLevels );
    }

    private List<Label> labelList() {
        return Arrays.asList(
                // Row 1
                lbl00,
                lbl10,
                lbl20,
                lbl30,
                lbl40,
                // Row 2
                lbl01,
                lbl11,
                lbl21,
                lbl31,
                lbl41,
                // Row 3
                lbl02,
                lbl12,
                lbl22,
                lbl32,
                lbl42,
                // Row 4
                lbl03,
                lbl13,
                lbl23,
                lbl33,
                lbl43,
                // Row 5
                lbl04,
                lbl14,
                lbl24,
                lbl34,
                lbl44
        );
    }

    @FXML
    public void anchorPane00Clicked() {
        lbl00.setVisible(true);
    }

    @FXML
    public void anchorPane10Clicked() {
        lbl10.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane20Clicked() {
        lbl20.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane30Clicked() {
        lbl30.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane40Clicked() {
        lbl40.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane01Clicked() {
        lbl01.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane11Clicked() {
        lbl11.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane21Clicked() {
        lbl21.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane31Clicked() {
        lbl31.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane41Clicked() {
        lbl41.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane02Clicked() {
        lbl02.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane12Clicked() {
        lbl12.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane22Clicked() {
        lbl22.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane32Clicked() {
        lbl32.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane42Clicked() {
        lbl42.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane03Clicked() {
        lbl03.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane13Clicked() {
        lbl13.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane23Clicked() {
        lbl23.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane33Clicked() {
        lbl33.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane43Clicked() {
        lbl43.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane04Clicked() {
        lbl04.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane14Clicked() {
        lbl14.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane24Clicked() {
        lbl24.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane34Clicked() {
        lbl34.setVisible(true);
        System.out.println("Clicked");
    }

    @FXML
    public void anchorPane44Clicked() {
        lbl44.setVisible(true);
        System.out.println("Clicked");
    }
}