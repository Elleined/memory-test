package com.memorytest.memorytest;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public interface AlertFactory {

    static void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.show();
    }

    static void showAlert(Alert.AlertType alertType, String message, ButtonType... buttonTypes) {
        new Alert(alertType, message, buttonTypes).show();
    }

    static ButtonType showConfirmAlert(String message, ButtonType... buttonTypes) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, buttonTypes);
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }
}
