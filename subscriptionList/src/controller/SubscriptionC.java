package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.TheMain;
import model.Subscription;

import java.io.IOException;

public class SubscriptionC {
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private CheckBox cbJavaFx;
    @FXML
    private CheckBox cbJavaConcurrency;
    @FXML
    private CheckBox cbJavaMaster;
    @FXML
    private Button btSubmit;

    private static Subscription subscription;

    private static Stage stageOwner;

    @FXML
    private void btSubmitOnAction(ActionEvent event) {
        // Einschreibung sichern
        subscription.save();

        // Bestätigung
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                subscription.getFirstname() +
                        " " +
                        subscription.getLastname() +
                        ", wir freuen uns, sie bei " +
                        subscription.getCoursesString() +
                        " begrüßen zu dürfen!");
        alert.showAndWait();

        // nächste Einschreibung
        subscription.add();

        // Verbindung zu altem Model lösen
        tfFirstName.textProperty().unbindBidirectional(subscription.firstnameProperty());
        tfLastName.textProperty().unbindBidirectional(subscription.lastnameProperty());
        cbJavaFx.selectedProperty().unbindBidirectional(subscription.javaFxProperty());
        cbJavaConcurrency.selectedProperty().unbindBidirectional(subscription.javaConcurencyProperty());
        cbJavaMaster.selectedProperty().unbindBidirectional(subscription.javaMasterProperty());

        btSubmit.disableProperty().unbind();

        stageOwner.close();
    }

    public static void show(Stage stage, Subscription selectedObject) {
        try {
            subscription = selectedObject;
            stageOwner = stage;

            Parent root = FXMLLoader.load(TheMain.class.getResource("../view/subscriptionV.fxml"));
            Scene scene = new Scene(root);
            stageOwner.setScene(scene);
            stageOwner.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    public void initialize() {
        // Bind Model
        bindNewModel();
    }

    private void bindNewModel() {
        if (subscription == null) {
            subscription = new Subscription();
        }

        // Neues Model verbinden
        tfFirstName.textProperty().bindBidirectional(subscription.firstnameProperty());
        tfLastName.textProperty().bindBidirectional(subscription.lastnameProperty());
        cbJavaFx.selectedProperty().bindBidirectional(subscription.javaFxProperty());
        cbJavaConcurrency.selectedProperty().bindBidirectional(subscription.javaConcurencyProperty());
        cbJavaMaster.selectedProperty().bindBidirectional(subscription.javaMasterProperty());

        btSubmit.disableProperty().bind(subscription.validProperty().not());
    }

}