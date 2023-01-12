package viewController;

import javafx.application.Platform;
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
    private TextField tfPictureField;

    @FXML
    private ImageView ivImage;

    private InstantViewer model = new InstantViewer(tfPictureField.textProperty());

    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(InstantViewerC.class.getResource("instantViewerV.fxml"));
            Parent root = loader.load();

            //InstantViewerC instantViewerC = loader.getController();

            Scene scene = new Scene(root);
            stage.setTitle("Instant Viewer");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        model.imageNameProperty().bind(tfPictureField.textProperty());
        ivImage.imageProperty().bind(model);
        ivImage.fitHeightProperty().bind(ivImage.getScene().heightProperty());
    }
}
