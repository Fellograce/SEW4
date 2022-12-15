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
import model.Subscription;

import java.io.IOException;

public class SubscriptionC {
    @FXML
    private TextField tfFName;

    @FXML
    private TextField tfLName;

    @FXML
    private CheckBox cbProperties;

    @FXML
    private CheckBox cbConcurrency;

    @FXML
    private CheckBox cbMaster;

    @FXML
    private Button btSubmit;

    @FXML
    void btSubmitOnAction(ActionEvent event) {
        submit();
    }

    private Subscription subscription = new Subscription();

    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SubscriptionC.class.getResource("/view/SubscriptionV.fxml"));
            Parent root = loader.load();

            SubscriptionC subscriptionC = loader.getController();

            // View anzeigen
            Scene scene = new Scene(root);
            stage.setTitle("Subscription");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    private void initialize() {
        subscription.firstNameProperty().bind(tfFName.textProperty());
        subscription.lastNameProperty().bind(tfLName.textProperty());

        subscription.concurrencyProperty().bind(cbConcurrency.selectedProperty());
        subscription.propertyBindingProperty().bind(cbProperties.selectedProperty());
        subscription.masterProperty().bind(cbMaster.selectedProperty());

        btSubmit.disableProperty().bind((subscription.firstNameProperty().isNotEmpty().and(subscription.lastNameProperty().isNotEmpty().and(subscription.masterProperty().or(subscription.propertyBindingProperty().or(subscription.concurrencyProperty()))))).not());
    }

    private void submit() {
        System.out.println(subscription);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Meldung");
        alert.setHeaderText("Meldung");
        alert.setContentText(subscription.toString());

        alert.showAndWait();

        subscription = new Subscription();

        subscription.firstNameProperty().unbind();
        subscription.lastNameProperty().unbind();
        subscription.concurrencyProperty().unbind();
        subscription.propertyBindingProperty().unbind();
        subscription.masterProperty().unbind();
        btSubmit.disableProperty().unbind();

        initialize();

        resetWindow();
    }

    private void resetWindow() {
        tfFName.setText("");
        tfLName.setText("");
        cbConcurrency.setSelected(false);
        cbMaster.setSelected(false);
        cbProperties.setSelected(false);
    }
}
