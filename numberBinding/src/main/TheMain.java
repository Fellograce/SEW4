package main;

import controller.CylinderC;
import javafx.application.Application;
import javafx.stage.Stage;

public class TheMain extends Application {

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)  {
      CylinderC.show(primaryStage);
   }
}
