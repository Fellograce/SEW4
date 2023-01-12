package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.InstantViewer;

import java.io.IOException;

public class InstantViewerC {
    @FXML
    private TextField tfPictureField = new TextField();

    @FXML
    private ImageView ivImage;

    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(InstantViewerC.class.getResource("/view/instantViewerV.fxml"));
            Parent root = loader.load();

            InstantViewerC instantViewerC = loader.getController();
            instantViewerC.initialize(stage);

            Scene scene = new Scene(root);
            stage.setTitle("Instant Viewer");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize(Stage stage) {
        ivImage.setPreserveRatio(true);
        ivImage.imageProperty().bind(new InstantViewer(tfPictureField.textProperty()));
        ivImage.fitWidthProperty().bind(stage.widthProperty());
        ivImage.fitHeightProperty().bind(stage.heightProperty());
    }
}
