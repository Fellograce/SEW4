package vC;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AutomatedText;

import java.io.IOException;

public class SimpleC {
    @FXML
    private TextField tfText;

    @FXML
    private Label lbText;

    private AutomatedText automatedText = new AutomatedText();

    public static void show(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(SimpleC.class.getResource("SimpleV.fxml"));
        Parent root = (Parent) loader.load();

        SimpleC simpleC = loader.getController();

        // View anzeigen
        Scene scene = new Scene(root);
        stage.setTitle("Personenwartung");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() {
        automatedText.textProperty().bind(tfText.textProperty());
        lbText.textProperty().bind(automatedText.textProperty());
    }
}
