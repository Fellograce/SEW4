package main;

import controller.InstantViewerC;
import javafx.application.Application;
import javafx.stage.Stage;

public class TheMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        InstantViewerC.show(primaryStage);
    }
}