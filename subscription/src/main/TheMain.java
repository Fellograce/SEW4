package main;

import controller.SubscriptionC;
import javafx.application.Application;
import javafx.stage.Stage;

public class TheMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SubscriptionC.show(primaryStage);
    }
}
