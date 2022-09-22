import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane anchorPane = new AnchorPane();

            Label label = new Label("this is AnchorPane example");

            // anchor to the label
            AnchorPane.setTopAnchor(label, 120.0);
            AnchorPane.setLeftAnchor(label, 10.0);
            AnchorPane.setRightAnchor(label, 230.0);
            AnchorPane.setBottomAnchor(label, 120.0);

            anchorPane.getChildren().add(label);

            Button button = new Button("button");

            // anchor to the button
            AnchorPane.setTopAnchor(button, 125.0);
            AnchorPane.setLeftAnchor(button, 220.0);
            AnchorPane.setRightAnchor(button, 110.0);
            AnchorPane.setBottomAnchor(button, 125.0);

            anchorPane.getChildren().add(button);
            anchorPane.setMinHeight(400);
            anchorPane.setMinWidth(400);

            Scene scene = new Scene(anchorPane, 400, 300);

            stage.setTitle("AnchorPane");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
