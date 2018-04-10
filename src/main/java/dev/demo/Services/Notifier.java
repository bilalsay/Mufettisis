package dev.demo.Services;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Notifier {
	
	public static Optional<ButtonType> passMessage(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().clear();
        if (alertType.equals(Alert.AlertType.CONFIRMATION)) {
            alert.getButtonTypes().addAll(
                    new ButtonType("Evet", ButtonBar.ButtonData.YES),
                    new ButtonType("Hayır", ButtonBar.ButtonData.CANCEL_CLOSE));
        } else {
            alert.getButtonTypes().add(new ButtonType("Tamam", ButtonBar.ButtonData.OK_DONE));
        }
        return alert.showAndWait();
    }

}
