import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane(); //Creating a Grid Pane

        Label lbSimple = new Label ("Simple GridPane example"); // Creating a label with text
        Label lbHello = new Label ("Hello World"); // Creating a label with text
        Label lbAsdf = new Label ("asdf"); // Creating a label with text


        gridPane.setGridLinesVisible(true);

        gridPane.setPadding(new Insets(10, 10, 10, 10)); //Set the padding of the gridpane

        //Set the vertical and horizontal gaps between the columns
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        gridPane.add(lbSimple, 0, 1); // Adding the label to the grid pane
        gridPane.add(lbAsdf, 0, 2); // Adding the label to the grid pane
        gridPane.add(lbHello, 1, 0); // Adding the label to the grid pane

        gridPane.setAlignment(Pos.CENTER); // Align the grid pane
        GridPane.setHalignment(lbAsdf, HPos.CENTER);

        Scene scene = new Scene(gridPane, 300, 300); //Creating a scene  with necessary size

        stage.setTitle("Grid Alignment Example"); //Setting title to the Application

        stage.setScene(scene); //Adding scene to the stage

        stage.show(); //Displaying the contents of the stage
    }
    public static void main(String args[]){
        launch(args);  // Launching the application
    }
}