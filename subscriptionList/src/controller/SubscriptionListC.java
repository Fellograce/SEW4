package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.TheMain;
import model.Subscription;

import java.io.IOException;

public class SubscriptionListC {

    @FXML
    private ListView<Subscription> lvSubscriptions;

    @FXML
    private Button btNew;

    private Subscription subscription = new Subscription();

    @FXML
    private void btNewOnAction(ActionEvent event) {
        SubscriptionC.show(new Stage(), null);
    }

    public static void show(Stage stage) {
        try {
            Parent root = FXMLLoader.load(TheMain.class.getResource("../view/subscriptionListV.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    @FXML
    private void initialize() {
        lvSubscriptions.itemsProperty().bindBidirectional(subscription.listSubscriptionProperty());

        // Bind Model
        lvSubscriptions.getSelectionModel().selectedItemProperty().addListener(((dummy1, unselectedObject, selectedObject) -> {
            if (selectedObject != null) {
                SubscriptionC.show(new Stage(), selectedObject);
            }
        }));
    }
}
