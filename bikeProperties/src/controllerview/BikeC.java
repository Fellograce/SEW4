package controllerview;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bike;
import model.BikeExecption;

import java.io.IOException;

public class BikeC {
    @FXML
    private TextField tfRahmennr;

    @FXML
    private TextField tfMarkeType;

    @FXML
    private TextField tfFarbe;

    @FXML
    private Button btCancel;

    @FXML
    private Button btSelect;

    @FXML
    private Button btSave;

    private Bike model;

    @FXML
    void btCancelOnAction(ActionEvent event) {
        cancel();
    }

    @FXML
    void btSaveOnAction(ActionEvent event) {
        save();
    }

    @FXML
    void btSelectOnAction(ActionEvent event) {
        select();
    }

    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(BikeC.class.getResource("bikeV.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Bikes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    public void initialize() {
        disableNonkey(true);
    }

    private void cancel() {
        if (model != null) {
            tfRahmennr.textProperty().unbindBidirectional(model.rahmennrProperty());
            tfMarkeType.textProperty().unbindBidirectional(model.markeTypeProperty());
            tfFarbe.textProperty().unbindBidirectional(model.farbeProperty());
        }

        tfRahmennr.setText("");
        tfMarkeType.setText("");
        tfFarbe.setText("");

        disableNonkey(true);
    }

    private void select() {
        model = Bike.select(tfRahmennr.getText());

        tfRahmennr.textProperty().bindBidirectional(model.rahmennrProperty());
        tfMarkeType.textProperty().bindBidirectional(model.markeTypeProperty());
        tfFarbe.textProperty().bindBidirectional(model.farbeProperty());

        disableNonkey(false);
    }

    private void save() {
        try {
            model.save();
            cancel();
            info("Ok, Bike gesichert!");
        } catch (BikeExecption e) {
            error(e.getMessage());
        }
    }

    private void disableNonkey(boolean disable) {
        // Disable Fields
        tfMarkeType.setDisable(disable);
        tfFarbe.setDisable(disable);

        // Dis/Enable Buttons
        btSelect.setDisable(!disable);
        btSave.setDisable(disable);
    }

    private void info(String msg) {
        Alert error = new Alert(Alert.AlertType.INFORMATION, msg);
        error.showAndWait();
    }

    private void error(String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR, msg);
        error.showAndWait();
    }

}
