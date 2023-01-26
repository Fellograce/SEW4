package main;

import controllerView.MandelbrotC;
import javafx.application.Application;
import javafx.stage.Stage;

public class TheMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        MandelbrotC.show(primaryStage);
    }
}
