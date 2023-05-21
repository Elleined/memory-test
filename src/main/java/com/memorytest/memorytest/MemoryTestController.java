package com.memorytest.memorytest;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MemoryTestController implements Initializable {
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
    @FXML
    private ComboBox<Integer> cbNumberLevel;
    @FXML
    private ComboBox<Integer> cbDuration;

    @FXML
    private AnchorPane anchorPane00;
    @FXML
    private AnchorPane anchorPane10;
    @FXML
    private AnchorPane anchorPane20;
    @FXML
    private AnchorPane anchorPane30;
    @FXML
    private AnchorPane anchorPane40;
    @FXML
    private AnchorPane anchorPane01;
    @FXML
    private AnchorPane anchorPane11;
    @FXML
    private AnchorPane anchorPane21;
    @FXML
    private AnchorPane anchorPane31;
    @FXML
    private AnchorPane anchorPane41;
    @FXML
    private AnchorPane anchorPane02;
    @FXML
    private AnchorPane anchorPane12;
    @FXML
    private AnchorPane anchorPane22;
    @FXML
    private AnchorPane anchorPane32;
    @FXML
    private AnchorPane anchorPane42;
    @FXML
    private AnchorPane anchorPane03;
    @FXML
    private AnchorPane anchorPane13;
    @FXML
    private AnchorPane anchorPane23;
    @FXML
    private AnchorPane anchorPane33;
    @FXML
    private AnchorPane anchorPane43;
    @FXML
    private AnchorPane anchorPane04;
    @FXML
    private AnchorPane anchorPane14;
    @FXML
    private AnchorPane anchorPane24;
    @FXML
    private AnchorPane anchorPane34;
    @FXML
    private AnchorPane anchorPane44;

    private static final Logger log = Logger.getLogger(MemoryTestController.class.getName());
    private final GameAlgorithm gameAlgorithm = new GameAlgorithm();
    private int nextIndex = 0;
    private int selectedNumberLevel = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLevelSelection();
        initializeDurationSelection();
        this.cbNumberLevel.valueProperty().addListener(this::numberLevelComboBoxItemSelected);
    }

    private void initializeLevelSelection() {
        ObservableList<Integer> numberLevels = cbNumberLevel.getItems();
        for (int i = 5; i < 26; i++) {
            numberLevels.add(i);
        }
        cbNumberLevel.setItems( numberLevels );
    }

    private void initializeDurationSelection() {
        ObservableList<Integer> duration = cbDuration.getItems();
        for (int i = 5; i <= 30; i+=5) {
            duration.add(i);
        }
        cbDuration.setItems( duration );
    }

    private void numberLevelComboBoxItemSelected(Observable observable) {

        this.selectedNumberLevel = cbNumberLevel.getSelectionModel().getSelectedItem();

        List<Integer> gameTiles = gameAlgorithm.generateGameTiles(selectedNumberLevel);

        Iterator<Integer> levelItr = gameTiles.iterator();
        Iterator<Label> labelItr = this.labelList().iterator();
        while (levelItr.hasNext() && labelItr.hasNext()) {
            this.setFont(labelItr.next(), levelItr.next());
        }
        displayDelay();
    }

    private void setFont(Label label, int level) {
        label.setText(String.valueOf(level));
        Font font = Font.font("Arial Black", FontWeight.BOLD, FontPosture.ITALIC, 45);
        label.setFont(font);
    }

    private void displayDelay() {
        Integer duration = cbDuration.getSelectionModel().getSelectedItem();
        if (duration == null) {
            this.labelList().forEach(label -> label.setVisible(false));
            new Alert(Alert.AlertType.WARNING, "Please Select Duration Time").show();
            return;
        }

        this.labelList().forEach(label -> label.setVisible(true));

        // Set the label visible duration based on user selected in duration combo box
        Duration delay = Duration.seconds(duration);
        PauseTransition pause = new PauseTransition( delay );
        pause.setOnFinished(e -> {
            this.labelList().forEach(label -> label.setVisible(false));
            anchorPaneList().forEach(this::anchorPaneClicked);
        });
        pause.play();
    }

    private void anchorPaneClicked(AnchorPane anchorPane) {
        anchorPane.setOnMouseClicked(clicked -> {
            int anchorPaneIndex = anchorPaneList().indexOf(anchorPane);
            int mustIndexToOpen = gameAlgorithm.getIndexLocations().get(nextIndex);
            log.info("Anchor pane index " + anchorPaneIndex);
            log.info("Must index to open " + mustIndexToOpen);

            if (anchorPaneIndex != mustIndexToOpen) {
                nextIndex = 0;
                log.severe("You Lose!");
                // Losing logic here
                labelList().forEach(label -> label.setVisible(true));
                anchorPaneList().forEach(pane -> pane.setOnMouseClicked(null));

                AlertFactory.showConfirmAlert("You didn't guessed it all correctly. Its okay, you can try again!", ButtonType.OK);

                ButtonType yesOrNo = AlertFactory.showConfirmAlert("Do you want to play again? ");
                if (yesOrNo != ButtonType.OK) {
                    AlertFactory.showConfirmAlert("Thanks for Playing", ButtonType.OK);
                    Platform.exit();
                }

                labelList().forEach(label -> label.setVisible(false));
                return;
            }
            nextIndex++;
            // Open the associated label of anchor pane
            labelList().get(anchorPaneIndex).setVisible(true);

            if (!isPlayerWin()) return;

            log.info("You Win!");
            // WIning logic here
            labelList().forEach(label -> label.setVisible(true));
            anchorPaneList().forEach(pane -> pane.setOnMouseClicked(null));

            AlertFactory.showConfirmAlert("Wow you guessed it all correctly. You're a genius!", ButtonType.OK);
            ButtonType yesOrNo = AlertFactory.showConfirmAlert("Do you want to play again?");
            if (yesOrNo != ButtonType.OK) {
                AlertFactory.showConfirmAlert("Thanks for Playing", ButtonType.OK);
                Platform.exit();
            }

            labelList().forEach(label -> label.setVisible(false));
        });
    }

    private boolean isPlayerWin() {
        return nextIndex == selectedNumberLevel;
    }
    private List<Label> labelList() {
        return List.of(
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
    private List<AnchorPane> anchorPaneList() {
        return List.of(
                // Row 1
                anchorPane00,
                anchorPane10,
                anchorPane20,
                anchorPane30,
                anchorPane40,
                // Row 2
                anchorPane01,
                anchorPane11,
                anchorPane21,
                anchorPane31,
                anchorPane41,
                // Row 3
                anchorPane02,
                anchorPane12,
                anchorPane22,
                anchorPane32,
                anchorPane42,
                // Row 4
                anchorPane03,
                anchorPane13,
                anchorPane23,
                anchorPane33,
                anchorPane43,
                // Row 5
                anchorPane04,
                anchorPane14,
                anchorPane24,
                anchorPane34,
                anchorPane44
        );
    }
}